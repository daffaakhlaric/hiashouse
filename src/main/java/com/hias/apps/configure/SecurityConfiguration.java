package com.hias.apps.configure;

import com.hias.apps.security.JwtAuthenticationEntryPoint;
import com.hias.apps.security.JwtTokenFilter;
import com.hias.apps.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

//	@Bean
//	public UserDetailsService userDetailsService() {
//	    return super.userDetailsService();
//	}

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

//                auth.inMemoryAuthentication().withUser(u.username).password(u.password).roles("USER", "ADMIN");
    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception{
        return new JwtTokenFilter(jwtTokenProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/register","/product/detail/{id}/color","/inspration/{id}/product","/getAllBanner","/product/newsLatter","/getAllInspiration","/bannerWeb/{id}","/banner","/getAllBanner","/categoryArticle","/product/detail/{id}/related","/product/detail/{id}/variant","/product/{id}/getArticleByCategoryId","/bannerMobile","/imageProduct/**","/product/hotItems","/product/searchName/{name}","/product/{id}","/product/categoryId/{id}","/product/bestSeller","/product/{id}/aToz","/product/{id}/zToa","/product/{id}/last","/product/newest","/product/mainCategory","/product/subCategory","/product/subCategory/{id}","/product/subCategoryByMainCategoryId/{id}","/product/secondSubCategory","/product/secondSubCategory/{id}","/product/secondSubCategoryBySubCategoryId/{id}","/product/thirdSubCategory","/product/thirdSubCategory/{id}","/product/thirdSubCategoryBySecondMainCategoryId/{id}","/product/mainCategory/{id}","/product/{id}/priceHigh","/product/discussion/{id}","/product/{id}/priceLow", "/downloadFile/{fileId}","/validate", "/genre/**", "/resendEmail", "/change", "/authenticate/login", "/forgot", "/authenticate/refreshToken").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
