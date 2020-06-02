package org.cuit.BookSystem.Config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 定制请求的授权规则
		// 首页所有人可以访问
		http.authorizeRequests().antMatchers("/logout").permitAll().antMatchers("/toLogin").permitAll()
				.antMatchers("/login").permitAll();
		http.authorizeRequests().
		antMatchers("/client/**").hasRole("client").
		antMatchers("/client").hasRole("client").
		antMatchers("/background/**").hasRole("background").
		antMatchers("/manager").hasRole("background");

		// iframe 权限
		http.headers().addHeaderWriter((HeaderWriter) new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
		// 登录页面
		http.formLogin();
		// 注销页面
		http.logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/toLogin");

		// could use Get to logout
		http.csrf().disable();
		// remember me
		http.rememberMe().rememberMeParameter("remember");
		// 设置登录页 + 传输页面
		http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login").successHandler(new AuthenticationSuccessHandler() {
            
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                    Authentication authentication) throws IOException, ServletException {
              response.setContentType("application/json;charset=utf-8");
 
              RequestCache cache = new HttpSessionRequestCache();
              SavedRequest savedRequest = cache.getRequest(request, response);
              String url = savedRequest.getRedirectUrl();
              
              response.sendRedirect(url);
              
              
            }
        });
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("root")
				.password(new BCryptPasswordEncoder().encode("123")).roles("client", "background").and()
				.withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("background").and()
				.withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("client");

//        .and()
//        .withUser("root").password("123456").roles("vip1","vip2","vip3")
//        .and()
//        .withUser("guest").password("123456").roles("vip1","vip2");
	}
}
