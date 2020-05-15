package com.rain.trace.logtrace.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <pre>功能描述：</br><pre>
 * @ProjectName log-trace
 * @Author rain
 * @date 2020-05-15 23:53
 * @version v1.0
 */
@Service
public class LogTraceService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void logTraceProcess(String content) {
        log.info("logTrace Process Input Param={}", content);
    }
}
