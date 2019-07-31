package com.seal.quartz.springbootquartz.service;

import com.seal.quartz.springbootquartz.entity.TaskInfo;
import com.seal.quartz.springbootquartz.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/29 11:05
 * @description 任务处理类
 **/
@Slf4j
@Service
public class TaskService {

    @Autowired
    private Scheduler scheduler;

    /**
     * @param @return    参数
     * @return List<TaskInfo>    返回类型
     * @throws
     * @Title: list
     * @Description: 任务列表
     */
    public List<TaskInfo> queryJobList() {
        log.info("TaskService--data-s-->queryJobList()");
        List<TaskInfo> list = new ArrayList<>();
        try {
            for (String groupJob : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(groupJob))) {
                    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggers) {
                        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                        String cronExpression = "";
                        String createTime = "";
                        String milliSeconds = "";
                        String repeatCount = "";
                        String startDate = "";
                        String endDate = "";
                        if (trigger instanceof CronTrigger) {
                            CronTrigger cronTrigger = (CronTrigger) trigger;
                            cronExpression = cronTrigger.getCronExpression();
                            createTime = cronTrigger.getDescription();
                        } else if (trigger instanceof SimpleTrigger) {
                            SimpleTrigger simpleTrigger = (SimpleTrigger) trigger;
                            milliSeconds = simpleTrigger.getRepeatInterval() + "";
                            repeatCount = simpleTrigger.getRepeatCount() + "";
                            startDate = DateUtil.getDateStr(
                                    simpleTrigger.getStartTime());
                            endDate = DateUtil.getDateStr(simpleTrigger.getEndTime());
                        }
                        TaskInfo info = new TaskInfo();
                        info.setJobName(jobKey.getName());
                        info.setJobGroup(jobKey.getGroup());
                        info.setJobDescription(jobDetail.getDescription());
                        info.setJobStatus(triggerState.name());
                        info.setCronExpression(cronExpression);
                        info.setCreateTime(createTime);

                        info.setRepeatCount(repeatCount);
                        info.setStartDate(startDate);
                        info.setMilliSeconds(milliSeconds);
                        info.setEndDate(endDate);
                        list.add(info);
                    }
                }
            }
            log.info("任务的数量为：---------------->" + list.size());
        } catch (SchedulerException e) {
            log.info("查询任务失败，原因是：------------------>" + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param @param        inputMap
     * @param @return    参数
     * @return Boolean    返回类型
     * @throws
     * @Title: setSimpleTrigger
     * @Description: 简单调度
     */
    @SuppressWarnings({"unchecked"})
    public void setSimpleTriggerJob(TaskInfo info) {
        log.info("TaskService--data-s-->setSimpleTriggerJob()" + info);
        String jobName = info.getJobName();
        String jobGroup = info.getJobGroup();
        String jobDescription = info.getJobDescription();
        Long milliSeconds = Long.parseLong(info.getMilliSeconds());
        Integer repeatCount = Integer.parseInt(info.getRepeatCount());
        Date startDate = DateUtil.parseDate(info.getStartDate());
        Date endDate = DateUtil.parseDate(info.getEndDate());
        try {
            // 触发器的key值
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            // job的key值
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            if (checkExists(jobName, jobGroup)) {
                log.info(
                        "===> AddJob fail, job already exist, jobGroup:{}, jobName:{}",
                        jobGroup, jobName);
            }
            // 简单调度
            SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .startAt(startDate)
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInMilliseconds(milliSeconds)
                                    .withRepeatCount(repeatCount))
                    .endAt(endDate).build();
            Class<? extends Job> clazz = (Class<? extends Job>) Class
                    .forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey)
                    .withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            log.info("任务添加失败！--->简单调度" + e.getMessage());
        }
    }

    /**
     * @param @param info    参数
     * @return void    返回类型
     * @throws
     * @Title: addJob
     * @Description: 保存定时任务
     */
    @SuppressWarnings("unchecked")
    public void addJob(TaskInfo info) {
        log.info("TaskService--data-s-->addJob()" + info);
        String jobName = info.getJobName(), jobGroup = info.getJobGroup(), cronExpression = info
                .getCronExpression(), jobDescription = info.getJobDescription(), createTime = DateFormatUtils
                .format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (checkExists(jobName, jobGroup)) {
                log.info(
                        "===> AddJob fail, job already exist, jobGroup:{}, jobName:{}",
                        jobGroup, jobName);
            }

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

            CronScheduleBuilder schedBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey).withDescription(createTime)
                    .withSchedule(schedBuilder).build();

            Class<? extends Job> clazz = (Class<? extends Job>) Class
                    .forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey)
                    .withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException | ClassNotFoundException e) {
            log.info("保存定时任务-->类名不存在或执行表达式错误--->复杂调度" + e.getMessage());
        }
    }

    /**
     * @param @param info    参数
     * @return void    返回类型
     * @throws
     * @Title: edit
     * @Description: 修改定时任务
     */
    public void editJob(TaskInfo info) {
        log.info("TaskService--data-s-->editJob()" + info);
        String jobName = info.getJobName(), jobGroup = info.getJobGroup(), cronExpression = info
                .getCronExpression(), jobDescription = info.getJobDescription(), createTime = DateFormatUtils
                .format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (!checkExists(jobName, jobGroup)) {
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey).withDescription(createTime)
                    .withSchedule(cronScheduleBuilder).build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            log.info("修改定时任务-->类名不存在或执行表达式错误--->复杂调度" + e.getMessage());
        }
    }

    /**
     * @param @param jobName
     * @param @param jobGroup    参数
     * @return void    返回类型
     * @throws
     * @Title: delete
     * @Description: 删除定时任务
     */
    public void deleteJob(String jobName, String jobGroup) {
        log.info("TaskService--data-s-->deleteJob()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                log.info("===> delete, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            log.info("删除定时任务-->复杂调度" + e.getMessage());
        }
    }

    /**
     * @param @param jobName
     * @param @param jobGroup    参数
     * @return void    返回类型
     * @throws
     * @Title: pause
     * @Description: 暂停定时任务
     */
    public void pauseJob(String jobName, String jobGroup) {
        log.info("TaskService--data-s-->pauseJob()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.pauseTrigger(triggerKey);
                log.info("===> Pause success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            log.info("暂停定时任务-->复杂调度" + e.getMessage());
        }
    }

    /**
     * @param @param jobName
     * @param @param jobGroup    参数
     * @return void    返回类型
     * @throws
     * @Title: resume
     * @Description: 恢复暂停任务
     */
    public void resumeJob(String jobName, String jobGroup) {
        log.info("TaskService--data-s-->resumeJob()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        try {
            if (checkExists(jobName, jobGroup)) {
                scheduler.resumeTrigger(triggerKey);
                log.info("===> Resume success, triggerKey:{}", triggerKey);
            }
        } catch (SchedulerException e) {
            log.info("重新开始任务-->复杂调度" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param @param  jobName
     * @param @param  jobGroup
     * @param @return
     * @param @throws SchedulerException    参数
     * @return boolean    返回类型
     * @throws
     * @Title: checkExists
     * @Description: 验证任务是否存在
     */
    private boolean checkExists(String jobName, String jobGroup)
            throws SchedulerException {
        log.info("TaskService--data-s-->checkExists()--jobName:" + jobName
                + "jobGroup:" + jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }

}
