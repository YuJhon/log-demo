package com.rain.trace.logtrace.filter;

import com.rain.trace.logtrace.request.DefaultHeaderReqIdWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br></p>
 *
 * @author rain
 * @version v1.0
 * @projectName log-trace
 * @date 2019/12/16 16:11
 */
@Configuration
@WebFilter(urlPatterns = "/*")
public class SampleRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        DefaultHeaderReqIdWrapper wrapper = new DefaultHeaderReqIdWrapper(httpServletRequest);
        filterChain.doFilter(wrapper, httpServletResponse);
    }
}
