//package com.example.mysql.filter;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;
//
// import javax.servlet.*;
// import javax.servlet.http.HttpServletRequest;
// import java.io.IOException;
//
// @Component
// public class Logfilter implements Filter {
//
//     private static final Logger LOG = LoggerFactory.getLogger(Logfilter.class);
//
//     @Override
//     public void init(FilterConfig filterConfig) throws ServletException {
//
//     }
//
//     @Override
//     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//         // 打印请求信息
//         HttpServletRequest request = (HttpServletRequest) servletRequest;
//         LOG.info("------------- LogFilter 开始 -------------");
//         LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
//         LOG.info("远程地址: {}", request.getRemoteAddr());
//
//         long startTime = System.currentTimeMillis();
//         filterChain.doFilter(servletRequest, servletResponse);
//         LOG.info("------------- LogFilter 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
//     }
// }
