package com.test.springMVC.testmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.test.springMVC.testmvc.model.User;
import com.test.springMVC.testmvc.service.UserService;

@RestController
public class SampleRestController {

	@Autowired
	UserService userService;
	
	//Get All Users
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if(!users.isEmpty()){
			return new ResponseEntity<List<User>>(users , HttpStatus.OK) ;
		}
		
		return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
	}
	
	//get Single User
	@RequestMapping(value="/user/{id}" , method=RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id){
		User user = userService.findById(id);
		if(user!=null){
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	//Create a User
	@RequestMapping(value="/user" , method=RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user , UriComponentsBuilder uriBuilder)
	{
		if(userService.isUserExist(user)){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	//Update a user
	@RequestMapping(value="/user/{id}" , method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id , @RequestBody User user)
	{
		User checkUsr = userService.findById(id);
		
		if(checkUsr!=null){
			userService.updateUser(user);
			
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	//Delete a user
	@RequestMapping(value="/user/{id}" , method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id){
		
		User user = userService.findById(id);
		
		if(user!=null){
			userService.deleteUserById(id);
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	//Delete all users
	@RequestMapping(value="/user/" , method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers(){
		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
