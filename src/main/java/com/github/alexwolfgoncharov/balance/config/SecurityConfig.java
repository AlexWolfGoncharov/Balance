package com.github.alexwolfgoncharov.balance.config;


import com.github.alexwolfgoncharov.balance.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
 
    // регистрируем нашу реализацию UserDetailsService 
    // а также PasswordEncoder для приведения пароля в формат SHA1
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // включаем защиту от CSRF атак
        http.csrf()
                .disable()
                // указываем правила запросов
                // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                .antMatchers("/edit").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/delete").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/addusers").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/adddep").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/addContagent").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/addContract").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/contracts").authenticated()
                .antMatchers("/contragents").authenticated()
                .antMatchers("/deplist").authenticated()
                .antMatchers("/userlist").authenticated()

                .antMatchers("/resources/**", "/**","/receiptcontract/**").permitAll()
                .anyRequest().permitAll()
                .and();
 
        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                // указываем action с формы логина
                .loginProcessingUrl("/j_spring_security_check")
                // указываем URL при неудачном логине
                
                .failureUrl("/login?error")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();
 
        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutUrl("/logout")
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);

//        запрещаем одновременный вход под одним логином
//        http.sessionManagement()
//                .maximumSessions(1)
//                .expiredUrl("/expired")
//                .maxSessionsPreventsLogin(true)
//                .sessionRegistry(sessionRegistry());

 
    }

    // Work around https://jira.spring.io/browse/SEC-2855
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }


    // Указываем Spring контейнеру, что надо инициализировать <b></b>ShaPasswordEncoder
    // Это можно вынести в WebAppConfig, но для понимаемости оставил тут
    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder(256);
    }
 
}