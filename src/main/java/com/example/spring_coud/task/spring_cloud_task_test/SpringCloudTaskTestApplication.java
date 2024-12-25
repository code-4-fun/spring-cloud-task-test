package com.example.spring_coud.task.spring_cloud_task_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
public class SpringCloudTaskTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTaskTestApplication.class, args);
	}

}
