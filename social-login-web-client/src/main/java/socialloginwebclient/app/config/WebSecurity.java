package socialloginwebclient.app.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and().oauth2Login()
		.and().logout()
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.deleteCookies("JSESSIONID");

	}
	
	
//	 private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() { 
//	        OidcClientInitiatedLogoutSuccessHandler successHandler =
//	        		new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//	        successHandler.setPostLogoutRedirectUri("http://localhost:8080/");
//	        return successHandler;
//	    }

}
