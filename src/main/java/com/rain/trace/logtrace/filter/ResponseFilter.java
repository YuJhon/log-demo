package com.rain.trace.logtrace.filter;

import com.rain.trace.logtrace.constants.FilterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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
 * @date 2020-05-10 15:19
 * @version v1.0
 */
//@Component
//@Order(120)
public class ResponseFilter extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestId = httpServletRequest.getHeader("reqid");
        Long requestStartTime = httpServletRequest.getDateHeader("requestStartTime");
        long spendTime = System.currentTimeMillis() - requestStartTime;

        log.info("op=end_req|reqid={}|status={}|body={}|time={}",
                requestId,
                httpServletResponse.getStatus(),
                httpServletResponse,
                spendTime
        );
        MDC.remove(FilterConstants.REQ_ID);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
