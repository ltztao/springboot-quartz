package com.example.quartz.service.impl;

import com.example.quartz.service.QuartzTestService;
import com.example.quartz.task.PropertyQuartzJob;
import com.example.quartz.task.TestQuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzTestServiceImpl implements QuartzTestService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob() {

        String jobName = "test-" + System.currentTimeMillis();
        String jobGroup = "group-1";

        try {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("name", jobName);
            JobDetail jobDetail = JobBuilder
//                    .newJob(PropertyQuartzJob.class)  // 属性方式注入JobDataMap
                    .newJob(TestQuartzJob.class)
                    .withIdentity(jobName, jobGroup)
                    .usingJobData(dataMap)// 任务名称和组构成任务key
                    .build();
            // 定义调度触发规则
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)// 触发器key
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                    .startNow()
                    .build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJob() {
        try {
            JobKey jobKey = JobKey.jobKey("test-1614932418934", "group-1");
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
