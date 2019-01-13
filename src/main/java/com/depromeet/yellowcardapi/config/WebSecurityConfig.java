package com.depromeet.yellowcardapi.config;

import com.depromeet.yellowcardapi.service.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Configuration
    @Profile("dev")
    public class DevWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().permitAll()
                    .and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().headers().frameOptions().sameOrigin();    // h2 console
        }
    }

    @Configuration
    @Profile("production")
    public class ProductionWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/kakaologin?**", "/v2/api-docs", "/configuration/ui",
                    "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/signin").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                    .and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().headers().frameOptions().sameOrigin()    // h2 console
                    .and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider);
    }
}
