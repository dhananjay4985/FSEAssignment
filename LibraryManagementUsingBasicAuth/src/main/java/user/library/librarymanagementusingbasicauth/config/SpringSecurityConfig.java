package user.library.librarymanagementusingbasicauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import user.library.librarymanagementusingbasicauth.auth.AuthenticationEntryPoint;
import user.library.librarymanagementusingbasicauth.utility.Category;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
	/*	httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
          .authorizeRequests().antMatchers("/console/**").permitAll();*/
		
		httpSecurity
		.csrf().disable()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.authenticationEntryPoint(authenticationEntryPoint);

	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
		/*authBuilder.inMemoryAuthentication()
		.withUser("ADMIN")
		.password("abc@123")
		.roles("ADMIN");*/
		String adminPassword = "abc@1234";
		String customerPassword = "cust@123";
		
		String encrytedAdminPassword = this.passwordEncoder().encode(adminPassword);
		String encrytedMemberPassword = this.passwordEncoder().encode(customerPassword);
		System.out.println("Encoded password of abc@123 =" + encrytedAdminPassword);


		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> mngConfig = authBuilder.inMemoryAuthentication();

		// Defines 2 users, stored in memory.
		// ** Spring BOOT >= 2.x (Spring Security 5.x)
		// Spring auto add ROLE_
		UserDetails adminUser = User.withUsername("admin").password(encrytedAdminPassword).roles(Category.ADMIN.getRole()).build();
		UserDetails memberUser = User.withUsername("member").password(encrytedMemberPassword).roles(Category.MEMBER.getRole()).build();

		mngConfig.withUser(adminUser);
		mngConfig.withUser(memberUser);

	}
}
