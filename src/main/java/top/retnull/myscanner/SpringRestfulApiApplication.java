package top.retnull.myscanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: myScanner
 * @description: 启动类
 * @author: luoyu
 * @create: 2022-01-06 15:24
 **/

@EnableScheduling
@EnableTransactionManagement
@MapperScan("top.retnull.myscanner.mapper")
@SpringBootApplication()
public class SpringRestfulApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestfulApiApplication.class, args);
    }
}
