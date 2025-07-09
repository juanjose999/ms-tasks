package com.task_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TaskMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMsApplication.class, args);
	}

}
