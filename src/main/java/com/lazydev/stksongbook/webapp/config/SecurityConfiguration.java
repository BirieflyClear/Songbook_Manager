package com.lazydev.stksongbook.webapp.config;

import com.lazydev.stksongbook.webapp.security.jwt.JWTConfigurer;
import com.lazydev.stksongbook.webapp.security.jwt.TokenProvider;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final UserDetailsService userDetailsService;
  private final TokenProvider tokenProvider;
  private final CorsFilter corsFilter;

  public SecurityConfiguration(UserDetailsService userDetailsService, AuthenticationManagerBuilder authenticationManagerBuilder,
                               CorsFilter corsFilter, TokenProvider tokenProvider) {
    this.userDetailsService = userDetailsService;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.corsFilter = corsFilter;
    this.tokenProvider = tokenProvider;
  }

  @PostConstruct
  public void init() {
    try {
      authenticationManagerBuilder
          .userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder());
    } catch (Exception e) {
      throw new BeanInitializationException("Security configuration failed", e);
    }
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .antMatchers("/app/**/*.{js,html}")
        .antMatchers("/i18n/**")
        .antMatchers("/content/**")
        .antMatchers("/swagger-ui/index.html")
        .antMatchers("/test/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .and()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        //.antMatchers("/api/version").permitAll()
        .antMatchers("/api/register").permitAll()
        //.antMatchers("/api/activate").permitAll()
        .antMatchers("/api/authenticate").permitAll()
        .antMatchers("/api/user_roles").permitAll()
        .antMatchers("/api/admin/**").permitAll()
        //.antMatchers("/api/account/reset-password/init").permitAll()
        //.antMatchers("/api/account/reset-password/finish").permitAll()
        //.antMatchers("/api/organisations")//.hasAuthority(AuthoritiesConstants.ADMIN)
        //.antMatchers("/api/user-wrappers")//.hasAuthority(AuthoritiesConstants.ADMIN)
        //.antMatchers("/api/user-wrapper-org-assignments")//.hasAuthority(AuthoritiesConstants.ADMIN)
        .antMatchers("/api/**").authenticated()
        .antMatchers("/management/health").permitAll()
        .antMatchers("/management/info").permitAll()
        //.antMatchers("/management/**")//.hasAuthority(AuthoritiesConstants.ADMIN)
        .and()
        .apply(securityConfigurerAdapter());
  }

  private JWTConfigurer securityConfigurerAdapter() {
    return new JWTConfigurer(tokenProvider);
  }
}
