package cs.miu.edu.meditationattendance.config;

import cs.miu.edu.meditationattendance.security.CustomUserDetailsService;
import cs.miu.edu.meditationattendance.security.JwtAuthenticationEntryPoint;
import cs.miu.edu.meditationattendance.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		prePostEnabled = true
)
public class SecurityConfig{
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	@Order(1)
	public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {
		@Value("${basicAuthenConfig.urlPattern}")
		private String urlPattern;

		@Value("${basicAuthenConfig.username}")
		private String username;

		@Value("${basicAuthenConfig.password}")
		private String password;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.authorizeRequests().antMatchers(urlPattern)
					.authenticated()
					.and()
					.httpBasic().and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser(username)
					.password(passwordEncoder().encode(password)).roles("MACHINE");
		}
	}

	@Configuration
	@Order(2)
	public class JWTAuthSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private CustomUserDetailsService customUserDetailsService;

		@Autowired
		private JwtAuthenticationEntryPoint unauthorizedHandler;

		@Bean
		public JwtAuthenticationFilter jwtAuthenticationFilter() {
			return new JwtAuthenticationFilter();
		}

		@Override
		public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder
					.userDetailsService(customUserDetailsService)
					.passwordEncoder(passwordEncoder());
		}

		@Bean(BeanIds.AUTHENTICATION_MANAGER)
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf()
					.and()
					.exceptionHandling()
					.authenticationEntryPoint(unauthorizedHandler)
					.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.authorizeRequests()
					.antMatchers("/login")
					.permitAll()
					.and()
					.authorizeRequests()
					.anyRequest()
					.authenticated();
			http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		}
	}
}
