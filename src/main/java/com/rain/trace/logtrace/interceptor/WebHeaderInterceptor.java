package com.rain.trace.logtrace.interceptor;

import com.rain.trace.logtrace.constants.FilterConstants;
import com.rain.trace.logtrace.request.TraceThreadLocalHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>功能描述</br>web头信息拦截器</p>
 *
 * @author rain
 * @version v1.0
 * @projectName log-trace
 * @date 2019/12/16 13:53
 */
@Component
public class WebHeaderInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        /** 传递reqId **/
        String reqId = request.getHeader(FilterConstants.REQ_ID);
        TraceThreadLocalHelper.putReqId(reqId);

        org.slf4j.MDC.put(FilterConstants.REQ_ID, StringUtils.stripToEmpty(reqId));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        TraceThreadLocalHelper.clearReqId();
        org.slf4j.MDC.remove(FilterConstants.REQ_ID);
    }
}
