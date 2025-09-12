package com.jtspringproject.JtSpringProject.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.dao.UserDao;
import com.jtspringproject.JtSpringProject.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;
	
	public List<User> getUsers(){
        logger.debug("Fetching all users");
		return this.userDao.getAllUser();
	}
	
	@Transactional
	public User addUser(User user) {
        logger.debug("Adding user: {}", user.getUsername());
		try {
			return this.userDao.saveUser(user);
		} catch (DataIntegrityViolationException e) {
            logger.error("Add user error for '{}': {}", user.getUsername(), e.getMessage());
			throw new RuntimeException("Add user error");
		}
	}
	
	public User checkLogin(String username,String password) {
        logger.debug("Checking login for username: {}", username);
		return this.userDao.getUser(username, password);
	}

	public boolean checkUserExists(String username) {
        logger.debug("Checking if user exists: {}", username);
		return this.userDao.userExists(username);
	}

	public User getUserByUsername(String username) {
        logger.debug("Getting user by username: {}", username);
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            logger.info("User '{}' found with role: {}", username, user.getRole());
        } else {
            logger.warn("User '{}' not found", username);
        }
        return user;
    }
	
	public User getUser(int id) {
        logger.debug("Getting user by id: {}", id);
		return userDao.getUser(id);
	}

	public User updateUser(User user) {
        logger.debug("Updating user: {}", user.getUsername());
		return userDao.updateUser(user);
	}
}