package com.stackroute;

import com.stackroute.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringUploaderApplication {


	@Autowired
	UploadService uploadService;

	public static void main(String[] args) {
		SpringApplication.run(SpringUploaderApplication.class, args);
	}


}

