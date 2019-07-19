package com.seal.xxljob.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/12 18:03
 * @description
 **/
@Slf4j
@Component
@JobHandler(value = "InfoJob")
public class InfoJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) {
        System.out.println("111111111111111111111111111");
        System.out.println("222222222222222222222222222");
        System.out.println("333333333333333333333333333");
        System.out.println("444444444444444444444444444");
        return SUCCESS;
    }
}
