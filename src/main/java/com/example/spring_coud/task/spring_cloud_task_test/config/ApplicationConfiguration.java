package com.example.spring_coud.task.spring_cloud_task_test.config;

import com.example.spring_coud.task.spring_cloud_task_test.task.DemoTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Step demoStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, DemoTask demoTask) {
        return new StepBuilder("demoStep", jobRepository)
                .tasklet(demoTask, transactionManager)
                .build();
    }

    @Bean
    public Job testJob(JobRepository jobRepository, JobExecutionListener jobExecutionListener, Step demoStep) {
        return new JobBuilder("testJob", jobRepository)
                .listener(jobExecutionListener)
                .start(demoStep)
                .next(demoStep)
                .next(demoStep)
                .build();
    }

}
