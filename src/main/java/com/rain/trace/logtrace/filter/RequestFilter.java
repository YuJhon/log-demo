package com.rain.trace.logtrace.filter;

import com.rain.trace.logtrace.constants.FilterConstants;
import com.rain.trace.logtrace.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * <pre>功能描述：</br><pre>
 * @ProjectName log-trace
 * @Author rain
 * @date 2020-05-10 15:18
 * @version v1.0
 */
//@Component
//@Order(110)
public class RequestFilter extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestId = httpServletRequest.getHeader(FilterConstants.REQ_ID);

        if (StringUtils.isEmpty(requestId)) {
            requestId = UUID.randomUUID().toString().split("-")[0];
        }
        MDC.put(FilterConstants.REQ_ID, requestId);
        httpServletResponse.setHeader(FilterConstants.REQ_ID, requestId);

        String requestType = httpServletRequest.getContentType();
        String requestSubType = Optional.ofNullable(requestType).map(t -> t.split(";")[0]).orElse("");

        log.info("op=start_req|reqid={}|ip={}|content_type={}|url={}{}|body={}|app_ver={}|app_type={}",
                requestId,
                HttpUtils.getLocalIp(httpServletRequest),
                requestType,
                httpServletRequest.getRequestURI(),
                Optional.ofNullable(httpServletRequest.getQueryString()).map(q -> "?" + q).orElse(""),
                FilterConstants.LOG_CONTENT_TYPE.contains(requestSubType) ? HttpUtils.readRequest(httpServletRequest) : "",
                httpServletRequest.getHeader("version"),
                httpServletRequest.getHeader("clientType"));

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
