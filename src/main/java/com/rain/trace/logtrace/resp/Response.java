package com.rain.trace.logtrace.resp;

import com.rain.trace.logtrace.request.TraceThreadLocalHelper;

import java.util.Date;

/**
 * <pre>功能描述：</br><pre>
 * @ProjectName log-trace
 * @Author rain
 * @date 2020-05-10 16:01
 * @version v1.0
 */
public class Response {

    private String msg;

    private Object data;

    private boolean success;

    private String reqId;

    private Date requestTime = new Date();

    public static Response success(Object data) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(data);
        response.setReqId(TraceThreadLocalHelper.getReqId());
        return response;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
}
