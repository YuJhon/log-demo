package com.rain.trace.logtrace.request;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>功能描述</br>通过线程传递参数</p>
 *
 * @author rain
 * @version v1.0
 * @projectName log-trace
 * @date 2019/12/16 13:39
 */
public class TraceThreadLocalHelper {

    private static ThreadLocal<Long> accountInfo = new TransmittableThreadLocal<>();

    private static ThreadLocal<String> accountNameInfo = new TransmittableThreadLocal<>();

    private static ThreadLocal<String> langInfo = new TransmittableThreadLocal<>();

    private static ThreadLocal<String> reqIdInfo = new TransmittableThreadLocal<>();

    /**
     * <pre>获取当前用户ID</pre>
     *
     * @return
     */
    public static Long getAccountId() {
        return accountInfo.get() == null ? 0L : accountInfo.get();
    }

    /**
     * <pre>获取当前用户姓名</pre>
     *
     * @return
     */
    public static String getAccountName() {
        return StringUtils.isEmpty(accountNameInfo.get()) ? "anno" : accountNameInfo.get();
    }

    /**
     * <pre>获取当前环境的语言种类</pre>
     *
     * @return
     */
    public static String getLangInfo() {
        return langInfo.get();
    }

    /**
     * <pre>获取本次请求的请求ID</pre>
     *
     * @return
     */
    public static String getReqId() {
        return reqIdInfo.get();
    }

    /**
     * <pre>设置AccountId</pre>
     */
    public static void putAccountId(Long accountId) {
        accountInfo.set(accountId);
    }

    /**
     * <pre>设置AccountId</pre>
     */
    public static void putAccountName(String accountName) {
        accountNameInfo.set(accountName);
    }

    /**
     * <pre>设置语言类型</pre>
     */
    public static void putLangInfo(String lang) {
        langInfo.set(lang);
    }

    /**
     * <pre>设置请求ID</pre>
     */
    public static void putReqId(String reqId) {
        reqIdInfo.set(reqId);
    }

    /**
     * <pre>清空账号ID</pre>
     */
    public static void clearAccountId() {
        accountInfo.remove();
    }

    /**
     * <pre>清空账号ID</pre>
     */
    public static void clearAccountName() {
        accountNameInfo.remove();
    }

    /**
     * <pre>清空请求ID</pre>
     */
    public static void clearReqId() {
        reqIdInfo.remove();
    }

    /**
     * <pre>清空语言</pre>
     */
    public static void clearLang() {
        langInfo.remove();
    }
}
