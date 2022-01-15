package top.retnull.myscanner.controller;


import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.retnull.myscanner.entities.SysUser;
import top.retnull.myscanner.jwt.JwtLoginUser;
import top.retnull.myscanner.service.SysUserService;
import top.retnull.myscanner.utils.JsonResult;
import top.retnull.myscanner.utils.SecurityUtils;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * All rights Reserved, Designed By retnull.top
 * <p>
 * 文件上传 控制器
 * </p>
 *
 * @author retnull
 * @version 2.0
 * @date 2022/1/4
 * 
 */

@Slf4j
@Api(tags = "[ 七牛云 ]文件上传")
@RestController
@RequestMapping("/upload")
public class UploadController {


    @Autowired
    private SysUserService sysUserService;

    /**
     * 图片上传
     *
     * @param file
     * @return java.io.File
     * @throws IOException
     * @author retnull
     * @date 2022/1/4
     */
    @ApiOperation(value = "图片上传", notes = "普通的图片上传：500px * 500px")
    @PostMapping("/image")
    public JsonResult uploadImages(MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())) {
            JsonResult.fail("图片不能为空");
        }
        String contentType = file.getContentType();
        if (!contentType.contains("")) {
            JsonResult.fail("图片格式不能为空");
        }
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$";
        Matcher matcher = Pattern.compile(reg).matcher(file.getOriginalFilename());
        // 校验 图片的后缀名 是否符合要求
        if (matcher.find()) {
           String imagfile = uploadFile(file);
            return JsonResult.success(imagfile);
        }
        return JsonResult.fail("图片格式不正确,只可以上传[ JPG , JPEG , PNG ]中的一种");
    }


    /**
     * 用户头像上传
     *
     * @param file
     * @return java.io.File
     * @throws IOException
     * @author retnull
     * @date 2022/1/4
     */
    @ApiOperation(value = "头像上传", notes = "普通的图片上传：200px * 200px")
    @PostMapping("/avatar")
    public JsonResult uploadAvatar(MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())) {
            JsonResult.fail("头像不能为空");
        }
        String contentType = file.getContentType();
        if (!contentType.contains("")) {
            JsonResult.fail("头像格式不能为空");
        }
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$";
        Matcher matcher = Pattern.compile(reg).matcher(file.getOriginalFilename());
        // 校验 图片的后缀名 是否符合要求
        if (matcher.find()) {
            String imagefile = uploadFile(file);
            return JsonResult.success(imagefile);
        }
        return JsonResult.fail("头像格式不正确,只可以上传[ JPG , JPEG , PNG ]中的一种");
    }


    /**
     * 图片压缩并且上传 七牛云 OSS
     *

     * @return Map<String, String>
     * @author retnull
     * @date 2022/1/4
     */
    private String uploadFile(MultipartFile file) throws IOException {
        String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/";
        // 对上传的文件重命名，避免文件重名
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID()
                + oldName.substring(oldName.lastIndexOf("."));
        try {
            // 文件保存
            file.transferTo(new File(basePath, newName));
            JwtLoginUser userDetails = SecurityUtils.getLoginUser();
            SysUser sysUser = sysUserService.findById(userDetails.getUid());
            sysUser.setAvatar(newName);
            sysUserService.updateUer(sysUser);
            // 返回上传文件的访问路径
            return newName;
        } catch (IOException e) {
            throw new IOException();
        }

    }



}
