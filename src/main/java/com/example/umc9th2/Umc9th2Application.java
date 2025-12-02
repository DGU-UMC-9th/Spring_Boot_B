package com.example.umc9th2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//JPA 엔티티에서 생성일/수정일 같은 감시필드를 자동으로 채워주도록 스프링 부트에 JPA 감사 기능을 활성화하는 어노테이션
public class Umc9th2Application {

	public static void main(String[] args) {
		SpringApplication.run(Umc9th2Application.class, args);
	}

}
