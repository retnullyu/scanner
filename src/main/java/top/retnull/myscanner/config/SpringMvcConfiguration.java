package top.retnull.myscanner.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.Formatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.retnull.myscanner.jwt.JwtAuthenticationTokenFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * springmvc 配置
 */
@Slf4j
@Configuration
@AutoConfigureBefore(WebSecurityConfiguration.class)
public class SpringMvcConfiguration implements WebMvcConfigurer {

    private static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    @Value("${web.upload-path}")
    private String path;

    /**
     * 允许跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("跨域已设置");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Bean
    public FilterRegistrationBean registration(JwtAuthenticationTokenFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN)));

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN)));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN)));

        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    /***
     * Date日期类型转换器
     */
    @Bean
    public Formatter<Date> dateFormatter() {
        return new Formatter<Date>() {

            @Override
            public Date parse(String text, Locale locale) {
                Date date = null;
                try {
                    date = DateUtils.parseDate(text, locale, DEFAULT_DATE_TIME_PATTERN);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                return date;
            }

            @Override
            public String print(Date date, Locale locale) {
                return DateFormatUtils.format(date, DEFAULT_DATE_TIME_PATTERN, TimeZone.getDefault(), locale);
            }
        };
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) {
                return LocalDate.parse(text, DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN, locale));
            }

            @Override
            public String print(LocalDate object, Locale locale) {
                return DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN, locale).format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalTime> localTimeFormatter() {
        return new Formatter<LocalTime>() {
            @Override
            public LocalTime parse(String text, Locale locale) {
                return LocalTime.parse(text, DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN, locale));
            }

            @Override
            public String print(LocalTime object, Locale locale) {
                return DateTimeFormatter.ofPattern(DEFAULT_TIME_PATTERN, locale).format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {

            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                return DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN, locale).format(localDateTime);
            }

            @Override
            public LocalDateTime parse(String text, Locale locale) {
                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN, locale));
            }
        };
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 注意如果filePath是写死在这里，一定不要忘记尾部的/或者\\，这样才能读取其目录下的文件
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/",
                "classpath:/resources/",
                "classpath:/static/",
                "classpath:/public/",
                "classpath:/webapp/");
        registry.addResourceHandler("/img/**").addResourceLocations( "file:" + path);
    }


}
