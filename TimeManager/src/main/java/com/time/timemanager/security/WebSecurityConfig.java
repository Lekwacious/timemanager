package com.time.timemanager.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Bean
    public JwtAuthenticationFilter authenticationJwtTokenFilter() {
        return new JwtAuthenticationFilter();
    }
    private static final String[] STATE_WHITELIST = {
            "/api/v1/state/create",
            "/api/v1/state/update/**",
            "/api/v1/state/find/**",
            "/api/v1/state/find/**",
            "/api/v1/state/delete/**",
            "/api/v1/sms/**",
            "/api/v1/billingHistory/**",
            "/api/v1/virtualAccount/**",
            "/api/v1/transaction/**",
            "/api/v1/subscription/**",
            "/api/v1/paystackCustomer/**",
            "/api/v1/email/**",
            "/api/v1/bank/**",
            "/api/v1/ComplianceDocument/*",
            "/api/v1/ComplianceDocument/position/*",
            "/api/bill/**",
            "/api/v1/wallet/*",
            "/api/v1/wallet/balance/*",
            "/api/v1/wallet/checkBalance/**",
            "/api/v1/post/",
            "/api/v1/post/**",
            "/api/v1/endorsement/candidate/**",
            "/api/v1/endorsement/endorse/**",
//            "/api/v1/user/**",
//            "/api/v1/support-group/**"
    };

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().formLogin().usernameParameter("email").and()
                .authorizeRequests()
                .antMatchers( STATE_WHITELIST).authenticated()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**", "citizenmata/api/sms/**", "/api/v1/ComplianceDocument/**",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "configuration/security",
                        "/swagger-ui.html",
                        "/v3/api-docs",
                        "/webjars/**", "/api/auth").permitAll()
                .anyRequest().permitAll();



        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("static/**");
    }



//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webjars/**", "/images/**", "/css/**", "/js/**").addResourceLocations("classpath:/META-INF/resources/webjars/", "classpath:/static/images/", "classpath:/static/css/", "classpath:/static/js/");
//    }
}
