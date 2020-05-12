package com.rain.trace.logtrace.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>功能描述：</br><pre>
 * @ProjectName log-trace
 * @Author rain
 * @date 2020-05-10 15:23
 * @version v1.0
 */
@Component
@Order(100)
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestId = httpServletRequest.getHeader("reqid");
        MDC.put("reqid", requestId);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
