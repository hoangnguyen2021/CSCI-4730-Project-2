package edu.uga.moviereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.uga.moviereview.*"})
public class MovieReviewDB {

	public static void main(String[] args) {
		SpringApplication.run(MovieReviewDB.class, args);
	}

}
