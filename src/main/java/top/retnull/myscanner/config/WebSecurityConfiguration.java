package top.retnull.myscanner.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.retnull.myscanner.jwt.JwtAccessDeniedHandler;
import top.retnull.myscanner.jwt.JwtAuthenticationEntryPoint;
import top.retnull.myscanner.jwt.JwtAuthenticationTokenFilter;

/**

 * <p>
 * spring Security 核心配置类
 * </p>

 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
/*spring Security 配置类
 */
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //从数据库中取数据
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    //无权限访问资源控制器
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        configuration.setAllowCredentials(false);//是否支持安全证书(必需参数)
        configuration.addAllowedOrigin(CorsConfiguration.ALL); //允许任何域名
        configuration.addAllowedHeader(CorsConfiguration.ALL); //允许任何请求头
        configuration.addAllowedMethod(CorsConfiguration.ALL); //允许任何请求方法
        source.registerCorsConfiguration("/**", configuration);//访问路径
        return source;
    }

    @Autowired
    //设置自定义的userDetailsService以及密码编码器
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * @describe spring Security访问权限配置
     * @date 2022/1/4
     * @author retnull
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //授权异常处理
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/**", "/actuator/**").permitAll() //所有用户可访问
                .antMatchers("/information/**").permitAll() //暂时测试
                .antMatchers(
//                        "/v2/api-docs",
//                        "/doc.html",
//                        "/configuration/ui",
//                        "/swagger-resources",
//                        "/configuration/security",
//                        "/webjars/**",
//                        "/swagger-resources/configuration/ui",
//                        "/swagger-ui.html",
                        "/*.png",
                        "/*.jpg",
                        "/*.csv"
                )
                .permitAll().anyRequest().authenticated();//需要经过认证
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
        httpSecurity.csrf().disable().cors().configurationSource(corsConfigurationSource()); //配置跨域
    }

    /**
     * spring密码加密器
     *存入数据库的密码均会被加密
     * @date 2022/1/4
     * @author retnull
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
