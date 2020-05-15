package com.vanke.framework.core.request;

import com.vanke.framework.constant.VkFrameworkConstants;
import com.vanke.framework.constant.VkReqHeaderConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.UUID;

/**
 * <p>功能描述</br>修改头部信息</p>
 *
 * @author jiangy19
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
        if (StringUtils.isEmpty(paramVal) && VkReqHeaderConstants.REQ_ID.equalsIgnoreCase(param)) {
            return UUID.randomUUID().toString().split(VkFrameworkConstants.STRIKE_SPLITTER)[0];
        }
        return paramVal;
    }


}
