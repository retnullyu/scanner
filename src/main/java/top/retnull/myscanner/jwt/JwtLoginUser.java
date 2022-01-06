package top.retnull.myscanner.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.retnull.myscanner.constant.StatusConstant;

import java.util.Collection;

/**
 * <p>
 * spring security 认证 UserDetails 实现类
 * </p>

 */

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtLoginUser implements UserDetails {

    /**
     * 用户唯一ID
     *
     * @author retnull
     * @date 2022/1/4
     */
    private Integer uid;

    /**
     * 根据 redis 中，关联的id
     *
     * @author retnull
     * @date 2022/1/4
     */
    private String loginId;

    /**
     * 用户登录时，使用的用户名
     *
     * @author retnull
     * @date 2022/1/4
     */
    private String username;

    /**
     * 用户登录时，使用的密码
     *
     * @author retnull
     * @date 2022/1/4
     */
    private String password;

    /**
     * 用户状态， [ 0.禁用 1.正常 2.被删除 ]
     *
     * @author retnull
     * @date 2022/1/4
     */
    private Integer status;

    private Collection<? extends GrantedAuthority> authorities;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return status == StatusConstant.NORMAL;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
