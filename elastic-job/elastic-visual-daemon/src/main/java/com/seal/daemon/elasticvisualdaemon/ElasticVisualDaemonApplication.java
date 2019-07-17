package com.seal.daemon.elasticvisualdaemon;

import com.seal.common.annotation.EnableTplhkJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhiqiang.feng
 * @date 2018/7/24
 * elastic-job服务
 * 注解@SpringCloudApplication包括：
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 * 分别是SpringBoot注解、注册服务中心Eureka注解、
 * 断路器注解。对于SpringCloud来说，
 * 这是每一微服务必须应有的三个注解，
 * 所以才推出了@SpringCloudApplication这一注解集合。
 */
@EnableTplhkJob
@SpringBootApplication
public class ElasticVisualDaemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticVisualDaemonApplication.class, args);
    }

}
