package com.iokays.dispatch.core.adapter.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class Oauth2Job implements Job {

    // 编写任务内容
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 获取JobDetail
        JobDetail jobDetail = context.getJobDetail();

        // 可以输出任务的一些详情信息
        log.info("任务名称：{}", jobDetail.getKey().getName());
        log.info("任务分组名称：{}", jobDetail.getKey().getGroup());
        log.info("任务类名字：{}", jobDetail.getJobClass().getName());
        log.info("本次任务执行时间：{}", context.getFireTime());
        log.info("下次任务执行时间：{}", context.getNextFireTime());

        // 还可以通过JobDataMap存储数据
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        int count = jobDataMap.getInt("count");
        // 或者：Integer count1 = (Integer) jobDataMap.get("count");
        log.info("第{}次执行", count);
        // 更新共享数据
        jobDataMap.put("count", ++count);
    }
}
