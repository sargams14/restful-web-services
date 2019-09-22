package com.demo1.rest.webservices.restfulwebservices.democontroller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class DemoController {
	
	@Autowired
	private MessageSource messageSource;
	//GET
	//URI- /demo-controller
	//func() method
	@RequestMapping(method=RequestMethod.GET,path="/demo-controller")
	public String func()
	{
		return "Hi whatsup";		
	}

	//demo-controller-bean
	@GetMapping("/demo-controller-bean")
	public DemoControllerBean func1()
	{
		return new DemoControllerBean("Hi whatsup");		
	}
	
	//demo-controller/path-variable/sargam
	@GetMapping(path="/demo-controller/path-variable/{name}")
	public DemoControllerBean func2(@PathVariable String name)
	{
		return new DemoControllerBean(String.format("Hi, %s",name));		
	}
	
	@GetMapping(path="/int11")
	public String demoControllerInternationalized(@RequestHeader(value="Accept-Language",required=false) Locale locale)
	{
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	


}
