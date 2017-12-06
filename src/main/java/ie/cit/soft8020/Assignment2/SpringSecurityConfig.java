package ie.cit.soft8020.Assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	  
	
	
		@Autowired
	    private AccessDeniedHandler accessDeniedHandler;
	
	    // roles admin allow to access /admin/**
	    // roles user allow to access /user/**
	    // custom 403 access denied handler
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	    	
	        http.csrf().disable() //I don't know if we need this 
	                .authorizeRequests()
						.antMatchers("/", "/presetPackage","/cart/**",
								"/customPackage/**","/style.css","/cart/**").permitAll() // /resources/static/** does not work for importing css stuff for some reason
						.antMatchers("/admin/**").hasAnyRole("ADMIN")
						.anyRequest().authenticated()
	                .and()
	                .formLogin()
						.loginPage("/login")
						.permitAll()
						.and()
	                .logout()
						.permitAll()
						.and()
	                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	        
	       // http.authorizeRequests().antMatchers("/resources/static/**").permitAll().anyRequest().permitAll(); // this line makes the css appear but messes up all other security stuff
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	        auth.inMemoryAuthentication()
	                .withUser("Philomena").password("flowerpower").roles("ADMIN");
	    }
	
	
	
}
