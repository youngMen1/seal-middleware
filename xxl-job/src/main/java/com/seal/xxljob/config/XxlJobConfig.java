package com.seal.xxljob.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/12 18:03
 * @description https://blog.csdn.net/u011706563/article/details/89258029
 * 对比两者配置文件发现 两个版本所创建的JobExecutor不同 当高版本admin调用方 调用低版本job任务时就会出现
 * 当admin和被调用方的版本不一致时会导致 执行器无法注册！
 **/
@Configuration
@ComponentScan(basePackages = "com.seal.xxljob.jobhandler")
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>>xxl-job config init.");

        /**
         * #xxl-job 2.0.0以下版本配置文件
         */
//        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
//        xxlJobExecutor.setAdminAddresses(adminAddresses);
//        xxlJobExecutor.setAppName(appName);
//        xxlJobExecutor.setIp(ip);
//        xxlJobExecutor.setPort(port);
//        xxlJobExecutor.setAccessToken(accessToken);
//        xxlJobExecutor.setLogPath(logPath);
//        xxlJobExecutor.setLogRetentionDays(logRetentionDays);
//        return xxlJobExecutor;

        /**
         * #xxl-job 2.0.0以上版本配置
         */
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobSpringExecutor;

    }

}