package com.zd.concurrency;

import com.zd.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 核心操作
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter, {}, {}", Thread.currentThread().getId(), request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());
        //需要继续放行
        filterChain.doFilter(servletRequest, servletResponse);
}

    /**
     * 注销
     */
    @Override
    public void destroy() {

    }
}
