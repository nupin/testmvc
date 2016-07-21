package com.test.springMVC.testmvc.controller;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.test.springMVC.testmvc.configuration.TestConfiguration;


public class HelloWorldInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { TestConfiguration.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
