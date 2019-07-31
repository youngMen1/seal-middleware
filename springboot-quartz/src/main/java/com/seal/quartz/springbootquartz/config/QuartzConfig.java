package com.seal.quartz.springbootquartz.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/29 10:59
 * @description 配置任务调度中心（服务启动时启动）
 **/
@Configuration
public class QuartzConfig {

    /**
     * 自定义的factory
     */
    @Autowired
    private MyJobFactory myFactory;

    /**
     * 数据源地址
     */
    @Value("${spring.datasource.url}")
    private String url;

    /**
     * 用户名
     */
    @Value("${spring.datasource.username}")
    private String userName;

    /**
     * 密码
     */
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        try {
            schedulerFactoryBean.setQuartzProperties(quartzProperties());
            // 指向自建的调度工厂，用于解决方法类无法注入的问题
            schedulerFactoryBean.setJobFactory(myFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() throws IOException, SchedulerException {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        // 服务启动shi
        scheduler.start();
        return scheduler;
    }

    /**
     * @param @return
     * @param @throws IOException    参数
     * @return Properties    返回类型
     * @throws
     * @Title: quartzProperties
     * @Description: 设置quartz属性
     */
    public Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
        // 调度器的实例名
        prop.put("org.quartz.scheduler.instanceName", "quartzScheduler");
        // 实例的标识
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        // 检查quartz是否有版本更新（true 不检查）
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        // 表名前缀
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        // 集群开关
        prop.put("org.quartz.jobStore.isClustered", "false");
        // 线程池的名字
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        // 指定线程数量
        prop.put("org.quartz.threadPool.threadCount", "10");
        // 线程优先级（1-10）默认为5
        prop.put("org.quartz.threadPool.threadPriority", "5");
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");

        prop.put("org.quartz.dataSource.quartzDataSource.driver", "com.mysql.jdbc.Driver");
        prop.put("org.quartz.dataSource.quartzDataSource.URL", url);
        prop.put("org.quartz.dataSource.quartzDataSource.user", userName);
        prop.put("org.quartz.dataSource.quartzDataSource.password", password);
        prop.put("org.quartz.dataSource.quartzDataSource.maxConnections", "50");
        return prop;
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String currSun = dateFm.format(date);
        System.out.println(currSun);

    }

}
