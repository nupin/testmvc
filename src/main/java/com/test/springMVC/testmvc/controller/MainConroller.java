package com.test.springMVC.testmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainConroller {

	public String getIndexString(){
		return "UserManagement";
	}
}
