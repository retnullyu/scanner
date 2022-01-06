package top.retnull.myscanner.service.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.retnull.myscanner.constant.LoginConstant;
import top.retnull.myscanner.service.VerifyCodeService;
import top.retnull.myscanner.utils.VerifyCodeUtils;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author retnull
 * @version 1.0
 * @createTime 2020/3/5 0005 11:32
 */

@Slf4j
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    @Lazy
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public BufferedImage randomImageVerifyCode(String codeKey) {
        VerifyCodeUtils.ImageVerifyCode image = VerifyCodeUtils.getImage();
        // 将验证码的 codeKey 和 codeText , 保存在 redis 中，有效时间为 10 分钟
        redisTemplate.opsForValue().set(LoginConstant.CAPTCHA_CODE_KEY + codeKey, image.getCodeText().toUpperCase(), 10, TimeUnit.MINUTES);
        return image.getImage();
    }

    @Override
    public void deleteImageVerifyCode(String codeKey) {
        redisTemplate.delete(LoginConstant.CAPTCHA_CODE_KEY + codeKey);
    }

    @Override
    public boolean checkVerifyCode(String codeKey, String userCodeText) {
        // 获取服务器的 CodeText uuid 的作用，是获得对应的图片验证码
        String serverCodeText = redisTemplate.opsForValue().get(LoginConstant.CAPTCHA_CODE_KEY + codeKey);
        // 将 serverCodeText 和 user.codeText 都转换成小写，然后比较
        if (serverCodeText.equals(userCodeText.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }

}
