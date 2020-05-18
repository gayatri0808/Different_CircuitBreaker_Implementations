package com.sentineldemo.plangroup.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableAdminServer
@ComponentScan(basePackages={"com.hcl.acementoring.plangrp.application.*"})
public class SentinelDashboard {

	public static void main(String[] args) {
		SpringApplication.run(SentinelDashboard.class, args);
	}
}
