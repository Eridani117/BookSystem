package org.cuit.BookSystem.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	 
	public void registerUser(User user) {
		UserDao userDao = new UserDao();
	  userDao.register(user);
	}

	public User loginUser(String username,String password) {
		UserDao userDao = new UserDao();
	  return userDao.login(username, password);
	}

	public User findUser(String id) {
		UserDao userDao = new UserDao();
	  return userDao.findUser(id);
	}
}
