package com.iokays.dispatch.core.adapter.job;

import com.iokays.dispatch.core.adapter.job.webclient.WebClientData;
import com.iokays.dispatch.core.adapter.job.webclient.WebClientJob;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest
class WebClientJobTest {

    @Resource
    private WebClientJob webClientJob;

    @Test
    void test() {
        final var jobExecutionContext = create();
        webClientJob.execute(jobExecutionContext);
    }

    private JobExecutionContext create() {

        return new JobExecutionContext() {
            @Override
            public Scheduler getScheduler() {
                return null;
            }

            @Override
            public Trigger getTrigger() {
                return null;
            }

            @Override
            public Calendar getCalendar() {
                return null;
            }

            @Override
            public boolean isRecovering() {
                return false;
            }

            @Override
            public TriggerKey getRecoveringTriggerKey() throws IllegalStateException {
                return null;
            }

            @Override
            public int getRefireCount() {
                return 0;
            }

            @Override
            public JobDataMap getMergedJobDataMap() {
                return null;
            }

            @Override
            public JobDetail getJobDetail() {
                return new JobDetail() {
                    @Override
                    public JobKey getKey() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "";
                    }

                    @Override
                    public Class<? extends Job> getJobClass() {
                        return null;
                    }

                    @Override
                    public JobDataMap getJobDataMap() {
                        final JobDataMap jobDataMap = new JobDataMap();
                        jobDataMap.put("webClient", WebClientData.builder()
                                .url("https://www.iokays.com/api/users")
                                .method("get")
                                .build());
                        return jobDataMap;
                    }

                    @Override
                    public boolean isDurable() {
                        return false;
                    }

                    @Override
                    public boolean isPersistJobDataAfterExecution() {
                        return false;
                    }

                    @Override
                    public boolean isConcurrentExectionDisallowed() {
                        return false;
                    }

                    @Override
                    public boolean requestsRecovery() {
                        return false;
                    }

                    @Override
                    public JobBuilder getJobBuilder() {
                        return null;
                    }

                    @Override
                    public Object clone() {
                        return null;
                    }
                };
            }

            @Override
            public Job getJobInstance() {
                return null;
            }

            @Override
            public Date getFireTime() {
                return null;
            }

            @Override
            public Date getScheduledFireTime() {
                return null;
            }

            @Override
            public Date getPreviousFireTime() {
                return null;
            }

            @Override
            public Date getNextFireTime() {
                return null;
            }

            @Override
            public String getFireInstanceId() {
                return "";
            }

            @Override
            public Object getResult() {
                return null;
            }

            @Override
            public void setResult(Object o) {

            }

            @Override
            public long getJobRunTime() {
                return 0;
            }

            @Override
            public void put(Object o, Object o1) {

            }

            @Override
            public Object get(Object o) {
                return null;
            }
        };
    }

}