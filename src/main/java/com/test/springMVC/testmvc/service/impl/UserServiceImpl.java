package com.test.springMVC.testmvc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.springMVC.testmvc.model.User;
import com.test.springMVC.testmvc.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static AtomicLong counter = new AtomicLong();
	private static List<User> users ;

	static {
		users = populateDummyUsers();
	}

	@Override
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id)
			{
				return user ;
			}
		}

		return null;
	}

	@Override
	public User findByName(String name) {
		for(User user : users){
			if(user.getUserName() == name)
			{
				return user ;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());	
		users.add(user);
	}

	@Override
	public void updateUser(User user) {
		users.add(users.indexOf(user),user);
	}

	@Override
	public void deleteUserById(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
	}

	@Override
	public List<User> getAllUsers() {
		return users;
	}

	@Override
	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public boolean isUserExist(User user) {
		
		return findById(user.getId())!=null;
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Sam", "NY", "sam@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}


}
