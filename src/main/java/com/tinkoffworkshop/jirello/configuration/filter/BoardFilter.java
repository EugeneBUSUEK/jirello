package com.tinkoffworkshop.jirello.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class BoardFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
    }

    @Bean
    public FilterRegistrationBean<BoardFilter> boardFilterFilterRegistrationBean() {
        FilterRegistrationBean<BoardFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new BoardFilter());
        registrationBean.addUrlPatterns("/boards/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }
}
