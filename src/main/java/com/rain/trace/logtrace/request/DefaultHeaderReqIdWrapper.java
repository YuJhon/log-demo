package com.rain.trace.logtrace.request;

import com.rain.trace.logtrace.constants.FilterConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.UUID;

/**
 * <p>功能描述</br>修改头部信息</p>
 *
 * @author rain
 * @version v1.0
 * @projectName vk-open-platform
 * @date 2019/12/16 16:15
 */
public class DefaultHeaderReqIdWrapper extends HttpServletRequestWrapper {

    public DefaultHeaderReqIdWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String param) {
        HttpServletRequest request = (HttpServletRequest) getRequest();
        String paramVal = request.getHeader(param);
        if (StringUtils.isEmpty(paramVal) && FilterConstants.REQ_ID.equalsIgnoreCase(param)) {
            return UUID.randomUUID().toString().split("-")[0];
        }
        return paramVal;
    }


}
