package morti.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").hasAuthority("USER")
                .antMatchers("/user").permitAll()
                .antMatchers("/release/*").hasAuthority("USER")
                .antMatchers("/release").hasAuthority("ADMIN")
                .antMatchers("/issues/*").hasAuthority("USER")
                .antMatchers("/issues").hasAuthority("ADMIN")
                .antMatchers("/project/*").hasAuthority("USER")
                .antMatchers("/project").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .csrf()
                .disable()
            .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, CustomUserDetailsService customUserDetailsService,
                                DaoAuthenticationProvider authenticationProvider) throws Exception {
        auth
                .userDetailsService(customUserDetailsService).
                    and()
                .authenticationProvider(authenticationProvider);
    }


}
