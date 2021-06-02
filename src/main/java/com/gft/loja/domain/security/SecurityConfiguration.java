package com.gft.loja.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gft.loja.domain.repository.UsuarioRepository;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationService autenticacaoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/api/clientes/cadastrar").permitAll()
		.antMatchers(HttpMethod.GET,"/api/produtos/**").hasAnyAuthority("CLIENTE","LOJA")
		.antMatchers(HttpMethod.GET,"/api/produtos/todos").hasAnyAuthority("LOJA")
		.antMatchers(HttpMethod.POST,"/api/vendas").hasAnyAuthority("CLIENTE")
		.antMatchers(HttpMethod.PUT,"/api/vendas/**").hasAnyAuthority("CLIENTE")
		.antMatchers(HttpMethod.POST,"/api/**").hasAnyAuthority("LOJA")
		.antMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority("LOJA")
		.antMatchers(HttpMethod.PUT,"/api/**").hasAnyAuthority("LOJA")
		.antMatchers(HttpMethod.DELETE,"/api/**").hasAnyAuthority("LOJA")
		.antMatchers(HttpMethod.POST,"/login").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
		
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui.html","/swagger-ui/**", "/v3/api-docs/**");
	}
}
