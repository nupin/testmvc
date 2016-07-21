package com.test.springMVC.testmvc.configuration;
import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class TestInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{TestConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new CORSFilter() };
	}
}
