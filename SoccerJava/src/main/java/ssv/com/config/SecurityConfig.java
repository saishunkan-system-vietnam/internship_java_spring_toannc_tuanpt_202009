package ssv.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import ssv.com.rest.CustomAccessDeniedHandler;
import ssv.com.rest.JwtAuthenticationTokenFilter;
import ssv.com.rest.RestAuthenticationEntryPoint;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
	    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
	    jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
	    return jwtAuthenticationTokenFilter;
	  }
	  @Bean
	  public RestAuthenticationEntryPoint restServicesEntryPoint() {
	    return new RestAuthenticationEntryPoint();
	  }
	  @Bean
	  public CustomAccessDeniedHandler customAccessDeniedHandler() {
	    return new CustomAccessDeniedHandler();
	  }
	  @Bean
	  @Override
	  protected AuthenticationManager authenticationManager() throws Exception {
	    return super.authenticationManager();
	  }
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {

	    http.csrf().ignoringAntMatchers("/api/v1/**");
	    http.authorizeRequests().antMatchers("/images/**").permitAll();
	    http.authorizeRequests().antMatchers("/api/v1/account/login**").permitAll().antMatchers("/api/v1/account/signup**").permitAll().
	    antMatchers("/api/v1/account/foget").permitAll();

//	    http.antMatcher("/api/v1/account/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
//	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//	        .antMatchers(HttpMethod.GET,"/api/v1/account/**").access("hasRole('ROLE_ADMIN')")
//	        .antMatchers(HttpMethod.DELETE, "/api/v1/account/**").access("hasRole('ROLE_ADMIN')").and()
//
//	        .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
//	        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

	  }

}
