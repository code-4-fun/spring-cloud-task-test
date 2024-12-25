package com.example.spring_coud.task.spring_cloud_task_test.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class DemoTask implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("About to execute Demo task from within Spring Batch job context - {}", contribution);
        log.info("Received chunk context as {}", chunkContext);
        final var randomVal = new Random().nextFloat();
        log.info("About to send {} to next step", randomVal);
        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("data", randomVal);
        return RepeatStatus.FINISHED;
    }
}
