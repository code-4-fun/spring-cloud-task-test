package com.example.spring_coud.task.spring_cloud_task_test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.item.ExecutionContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DemoTaskTest {

    @Mock
    private StepContribution stepContribution;

    @Mock
    private ChunkContext chunkContext;

    @Mock
    private StepContext stepContext;

    @Mock
    private StepExecution stepExecution;

    @Mock
    private JobExecution jobExecution;

    private DemoTask demoTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        demoTask = new DemoTask();
    }

    @Test
    public void testExecute() throws Exception {
        ExecutionContext executionContext = new ExecutionContext();
        when(chunkContext.getStepContext()).thenReturn(stepContext);
        when(stepContext.getStepExecution()).thenReturn(stepExecution);
        when(stepExecution.getJobExecution()).thenReturn(jobExecution);
        when(jobExecution.getExecutionContext()).thenReturn(executionContext);

        demoTask.execute(stepContribution, chunkContext);

        verify(chunkContext, times(1)).getStepContext();
        verify(stepContext, times(1)).getStepExecution();
        verify(stepExecution, times(1)).getJobExecution();
        verify(jobExecution, times(1)).getExecutionContext();
        assertTrue(executionContext.containsKey("data"));
    }
}