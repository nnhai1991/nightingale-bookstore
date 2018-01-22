package com.nightingale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nightingale.app.repository.RoleRepository;
import com.nightingale.app.service.UserService;
import com.nightingale.app.util.UtilConstants;
import com.nightingale.web.security.user.CustomAuthenticationFailureHandler;
import com.nightingale.web.security.user.CustomAuthenticationSuccessHandler;
import com.nightingale.web.security.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserService userService;

  @Autowired
  RoleRepository roleRepository;

  @Bean
  CustomUserDetailsService customUserDetailsService() {
      return new CustomUserDetailsService(userService, roleRepository);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(customUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());

  }

  @Bean
  CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
      return new CustomAuthenticationSuccessHandler();
  }

  @Bean
  CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
      return new CustomAuthenticationFailureHandler();
  }


  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManager();
  }
    @Bean
    static PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
          .ignoring()
             .antMatchers("/resources/**");
      }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf();
        http.headers().frameOptions().sameOrigin();

        http.antMatcher("/**")
        
        .userDetailsService(customUserDetailsService())

            .authorizeRequests()
            .antMatchers("/resources/**", "/public/**", "/login", "/logout","/reset-password").permitAll()
//            .antMatchers("/admin/**").hasAnyRole(UtilConstants.Roles.AD)
            .antMatchers("/**").permitAll();
        http.formLogin()
            .loginPage("/login").usernameParameter("email").passwordParameter("password")
            .loginProcessingUrl("/loginProcess")
            .successHandler(customAuthenticationSuccessHandler()).defaultSuccessUrl("/")
            .failureHandler(customAuthenticationFailureHandler()).failureUrl("/login")
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutUrl("/logout")
            .logoutSuccessUrl("/login?msg=logout_success")
            .deleteCookies("JSESSIOINID")
            .and()
            .sessionManagement().maximumSessions(1).expiredUrl("/login?error=session_expired");

    }

}
