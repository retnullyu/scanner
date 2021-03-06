package top.retnull.myscanner.service.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.retnull.myscanner.constant.LoginConstant;
import top.retnull.myscanner.constant.PermissionType;
import top.retnull.myscanner.entities.SysPermission;
import top.retnull.myscanner.entities.SysRole;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.jwt.JwtAuthenticationException;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.jwt.JwtTokenUtils;
import top.retnull.myscanner.service.*;
import top.retnull.myscanner.utils.LocalTimeUtils;
import top.retnull.myscanner.utils.SecurityUtils;
import top.retnull.myscanner.vo.ButtonVo;
import top.retnull.myscanner.vo.MenuVo;
import top.retnull.myscanner.vo.TokenValue;
import top.retnull.myscanner.vo.UserVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;


    @Autowired
    @Lazy
    private RedisTemplate<String, Object> redisTemplate;

    private BoundHashOperations<String, String, Object> tokenStorage() {
        return redisTemplate.boundHashOps(jwtTokenUtils.getTokenHeader());
    }

    /**
     * ?????????????????? Token
     *
     * @return
     * @date 2022/1/4
     * @author retnull
     */
    @Override
    public TokenValue login(String username, String password) throws AuthenticationException {
        // ?????????????????? username  password ????????? UsernamePasswordAuthenticationToken???
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtLoginUser userDetails = (JwtLoginUser) authentication.getPrincipal();

        // ??????????????????ID ?????????????????????
        String loginId = jwtTokenUtils.createLoginId();

        String token = jwtTokenUtils.createToken(loginId);
        log.debug("userDetails: {}", userDetails);

        setLoginUser(loginId, userDetails);

        return TokenValue.builder()
                .header(jwtTokenUtils.getTokenHeader())
                .value(token)
                .prefix(jwtTokenUtils.getTokenHead())
                .expiration(jwtTokenUtils.getExpiration())
                .build();
    }

    @Override
    public void logout() {
        JwtLoginUser loginUser = SecurityUtils.getLoginUser();
        // ??????????????????????????????tokenId
        removeLoginUser(loginUser.getLoginId());
    }

    private void removeLoginUser(String loginId) {
        redisTemplate.delete(loginId);
    }

    private JwtLoginUser getLoginUser(String loginId) {
        JwtLoginUser loginUser = (JwtLoginUser) redisTemplate
                .opsForValue().get(LoginConstant.LOGIN_TOKEN_KEY + loginId);
        return loginUser;
    }

    private void setLoginUser(String loginId, JwtLoginUser loginUser) {
        loginUser.setLoginId(loginId);
        String loginKey = LoginConstant.LOGIN_TOKEN_KEY + loginId;
        // ?????????id ??? ??????????????????????????????????????????
        redisTemplate.opsForValue().set(
                loginKey,
                loginUser,
                jwtTokenUtils.getExpiration(),
                TimeUnit.MINUTES
        );
        // ?????????????????? ????????????
        if (jwtTokenUtils.getSso()) {
            String onlineUserKey = LoginConstant.ONLINE_USER_KEY + loginUser.getUsername();

            String oldLoginKey = (String) redisTemplate.opsForValue().get(onlineUserKey);
            // ??????????????????????????????????????????
            if (StringUtils.hasText(oldLoginKey)) {
                // ???????????????????????????
                removeLoginUser(LoginConstant.LOGIN_TOKEN_KEY + oldLoginKey);

                // ??????????????????
                removeLoginUser(onlineUserKey);

                // ??????????????????
                String milli = LocalTimeUtils.currentSecondFormat();
                // ??? ?????????????????????????????????????????????????????? redis??????????????????????????????
                redisTemplate.opsForValue().set(
                        LoginConstant.FORCED_OFFLINE_KEY + oldLoginKey,
                        milli,
                        5,
                        TimeUnit.MINUTES
                );
            }
            redisTemplate.opsForValue().set(
                    onlineUserKey,
                    loginId,
                    jwtTokenUtils.getExpiration(),
                    TimeUnit.MINUTES
            );
        }
    }

    @Override
    public TokenValue refresh() {
        JwtLoginUser loginUser = SecurityUtils.getLoginUser();
        // ??????????????????????????????tokenId
        removeLoginUser(loginUser.getLoginId());

        // ??????????????????ID ?????????????????????
        String loginId = jwtTokenUtils.createLoginId();

        // ????????????token
        String token = jwtTokenUtils.createToken(loginId);

        setLoginUser(loginId, loginUser);

        return TokenValue.builder()
                .header(jwtTokenUtils.getTokenHeader())
                .value(token)
                .prefix(jwtTokenUtils.getTokenHead())
                .expiration(jwtTokenUtils.getExpiration())
                .build();
    }

    @Override
    public JwtLoginUser validateUser(String loginId) throws AuthenticationException {
        JwtLoginUser jwtUser = getLoginUser(loginId);
        if (jwtUser == null || StringUtils.isEmpty(jwtUser.getUsername())) {
            // ??????????????????????????????
            if (jwtTokenUtils.getSso()) {
                String key = LoginConstant.FORCED_OFFLINE_KEY + loginId;
                // ???????????????????????????????????????
                String offlineTime = (String) redisTemplate.opsForValue().get(key);
                if (StringUtils.hasText(offlineTime)) {
                    // ?????? ???????????? ???????????????
                    removeLoginUser(key);
                    String errMsg = String.format("???????????????[ %s ]??????????????????????????????", offlineTime);
                    log.info("errMsg {}", errMsg);
                    throw new JwtAuthenticationException(errMsg);
                }
            }
            throw new JwtAuthenticationException("???????????????????????????");
        }
        jwtUser.setLoginId(loginId);
        return jwtUser;
    }

    @Override
    public UserVo findUserInfo() {
        // ???SecurityContextHolder?????????????????????????????????????????????
        JwtLoginUser userDetails = SecurityUtils.getLoginUser();
        // ????????????Id??????????????????????????????
        SysUser sysUser = sysUserService.findById(userDetails.getUid());
        UserVo result = new UserVo();
        BeanUtils.copyProperties(sysUser, result);
        // ????????????Id????????????????????? ????????????
        Set<SysPermission> permissions = sysPermissionService.findAllByUserId(sysUser.getUid());
        List<ButtonVo> buttonVos = new ArrayList<>();
        List<MenuVo> menuVos = new ArrayList<>();
        if (permissions != null && permissions.size() > 1) {
            permissions.forEach(permission -> {
                if (permission.getType().toLowerCase().equals(PermissionType.BUTTON)) {
                    /*
                     * ????????????????????????????????????????????????
                     * */
                    buttonVos.add(
                            new ButtonVo(
                                    permission.getPid(),
                                    permission.getResources(),
                                    permission.getTitle())
                    );
                }
                if (permission.getType().toLowerCase().equals(PermissionType.MENU)) {
                    /*
                     * ????????????????????????????????????????????????
                     * */
                    menuVos.add(
                            new MenuVo(
                                    permission.getPid(),
                                    permission.getParentId(),
                                    permission.getIcon(),
                                    permission.getResources(),
                                    permission.getTitle(),
                                    null
                            )
                    );
                }
            });
        }
        result.setButtons(buttonVos);
        result.setMenus(findRoots(menuVos));
        Set<SysRole> roles = sysRoleService.findAllByUserId(result.getUid());
        Set<String> rolesName = roles
                .stream()
                .map(r -> r.getDescription())
                .collect(Collectors.toSet());
//        String departmentName = sysDepartmentService.findById(sysUser.getDeptId()).getName();
//        result.setDepartmentName(departmentName);
        result.setRoles(rolesName);
        return result;
    }

    /**
     * ?????????????????????
     *
     * @param allNodes
     * @return Set<MenuVo>
     */
    private List<MenuVo> findRoots(List<MenuVo> allNodes) {
        // ?????????
        List<MenuVo> root = new ArrayList<>();
        allNodes.forEach(node -> {
            if (node.getParentId() == 0) {
                root.add(node);
            }
        });
        root.forEach(node -> {
            findChildren(node, allNodes);
        });
        return root;
    }

    /**
     * ?????????????????????
     *
     * @param treeNode
     * @param treeNodes
     * @return MenuVo
     */
    private MenuVo findChildren(MenuVo treeNode, List<MenuVo> treeNodes) {
        for (MenuVo it : treeNodes) {
            if (treeNode.getPid().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }


}
