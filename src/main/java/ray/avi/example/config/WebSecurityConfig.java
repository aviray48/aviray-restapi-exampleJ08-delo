package ray.avi.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Collection;
import java.util.Vector;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableConfigurationProperties(SecurityConfig.class)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired private SecurityConfig config;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/swagger-ui*", "/info", "/health").permitAll()
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.httpBasic()
	    	.and()
	    	.csrf()
	    	.disable();
	}
	
	/*
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(config.getUserDetails());
    }
    */
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager inMemoryUserDetailsManager = null;
		Collection<UserDetails> users = new Vector<UserDetails>();
		for(Object username : config.getUserDetails().keySet()) {
			UserAttributeEditor editor = new UserAttributeEditor();
			editor.setAsText(config.getUserDetails().getProperty((String) username));
			UserAttribute attr = (UserAttribute) editor.getValue();
			users.add(User.withUsername((String) username).password(passwordEncoder().encode(attr.getPassword())).roles(attr.getAuthorities().iterator().next().getAuthority()).build());
		}
		inMemoryUserDetailsManager = new InMemoryUserDetailsManager(users);
		return inMemoryUserDetailsManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder(8);
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
