package com.rain.trace.logtrace.controller;

import com.rain.trace.logtrace.resp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>功能描述：</br><pre>
 * @ProjectName log-trace
 * @Author rain
 * @date 2020-05-10 15:59
 * @version v1.0
 */
@RestController
@RequestMapping("/logTrace")
public class LogTraceController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/info")
    public Response logTrace(@RequestParam(value = "content") String content) {
        log.info("LogTraceController Logic Process !");
        return Response.success(content);
    }

}
