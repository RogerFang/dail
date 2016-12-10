package com.dail;

import com.dail.entity.Department;
import com.dail.entity.People;
import com.dail.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	@Autowired
	private PeopleService peopleService;

	@RequestMapping("/")
	public Department test(){
		return peopleService.findById(1l).getDepartment();
	}

	@RequestMapping("/people")
	public People people(){
		return peopleService.findById(1l);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean openEntityManagerInViewFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new OpenEntityManagerInViewFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}
