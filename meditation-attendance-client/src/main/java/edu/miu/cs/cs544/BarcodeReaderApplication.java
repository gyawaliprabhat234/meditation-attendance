package edu.miu.cs.cs544;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BarcodeReaderApplication {
	public static void main(String[] args) {
		SpringApplication.run(BarcodeReaderApplication.class, args);
	}
}
