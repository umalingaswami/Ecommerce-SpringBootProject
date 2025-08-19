package com.jtspringproject.JtSpringProject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;

import java.util.List;

/**
 * Utility class to migrate existing plain text passwords to BCrypt hashed passwords.
 * This should be run once after deploying the security updates.
 */
@Component
public class PasswordMigrationUtil {
    
    @Autowired
    private userDao userDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Migrates all existing plain text passwords to BCrypt hashed passwords.
     * This method should be called once after deploying the security updates.
     */
    public void migratePasswords() {
        List<User> users = userDao.getAllUser();
        
        for (User user : users) {
            String currentPassword = user.getPassword();
            
            // Check if password is already hashed (BCrypt hashes start with $2a$)
            if (!currentPassword.startsWith("$2a$")) {
                // Hash the plain text password
                String hashedPassword = passwordEncoder.encode(currentPassword);
                user.setPassword(hashedPassword);
                
                // Update the user in the database
                userDao.saveUser(user);
                System.out.println("Migrated password for user: " + user.getUsername());
            }
        }
    }
} 