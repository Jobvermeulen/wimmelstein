package nl.inholland.config;

import nl.inholland.filter.APIKeyAuthFilter;
import nl.inholland.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@EnableWebSecurity
@Order(2)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApiKeyRepository apiKeyRepository;
    @Value("X-AUTHTOKEN")
    private String headerName;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        APIKeyAuthFilter filter = new APIKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();

            if (!apiKeyRepository.findById(principal).isPresent()) {
                throw new BadCredentialsException("API Key was not found on the system");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        httpSecurity
                .antMatcher("/guitars/**")
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }
}
