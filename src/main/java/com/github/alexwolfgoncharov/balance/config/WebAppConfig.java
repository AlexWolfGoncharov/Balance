package com.github.alexwolfgoncharov.balance.config;


import com.github.alexwolfgoncharov.balance.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.github.alexwolfgoncharov")
public class WebAppConfig extends WebMvcConfigurerAdapter {
	// Позволяет видеть все ресурсы в папке pages, такие как картинки, стили и
	// т.п.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pages/**")
				.addResourceLocations("/pages/");
	}

	// а этот бин инициализирует View нашего проекта
	// точно это же мы делали в mvc-dispatcher-servlet.xml
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);

		return resolver;
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}


	@Bean(name = "BalanceService")
	public BalanceService getBalanceService(){
		return new BalanceServiceImpl();
	}

	@Bean (name = "receiptOperContractService")
	public ReceiptOperContractService receiptOperContractService(){
		return new ReceiptOperContractServiceImpl();
	}

	@Bean (name = "receiptOperDeptService")
	public  ReceiptOperDeptService receiptOperDeptService(){
		return new ReceiptOperDeptServiceImpl();
	}

	@Bean (name = "userRolesService")
	public UserRolesService userRolesService(){

		return new UserRolesServiceImpl();
	}

	@Bean (name = "contractsService")
	public ContractsService contractsService() {
		return new ContractsServiceImpl();
	}


	@Bean (name ="contrAgentsService" )
	public ContrAgentsService contrAgentsService(){
		return  new ContrAgentsServiceImpl();
	}

	@Bean (name = "serviceFactory")
	public ServiceFactory serviceFactory(){

		return new ServiceFactory();
	}

//	@Bean
//	public View jsonTemplate() {
//		MappingJackson2JsonView view = new MappingJackson2JsonView();
//		view.setPrettyPrint(true);
//		return view;
//	}
//
//	@Bean
//	public ViewResolver viewResolver() {
//		return new BeanNameViewResolver();
//	}

}
