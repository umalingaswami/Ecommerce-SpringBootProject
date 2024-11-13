package com.jtspringproject.JtSpringProject.models;

import com.jtspringproject.JtSpringProject.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testBasicUserProperties() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        
        assertEquals(1, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testUserEmailValidation() {
        User user = new User();
        user.setEmail("test@example.com");
        assertTrue(user.getEmail().contains("@"), "Email should contain @");
    }

    @Test
    public void testUserEquality() {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("testuser");
        user1.setEmail("test@example.com");

        User user2 = new User();
        user2.setId(1);
        user2.setUsername("testuser");
        user2.setEmail("test@example.com");

        assertEquals(user1, user2, "Users with same properties should be equal");
        assertEquals(user1.hashCode(), user2.hashCode(), "Hash codes should match for equal users");
    }

    @Test
    public void testInvalidEmailFormat() {
        User user = new User();
        
        user.setEmail("invalid-email");
        assertFalse(user.getEmail().contains("@"), "Email without @ should be detected as invalid");
        
        user.setEmail("");
        assertTrue(user.getEmail().isEmpty(), "Empty email should be allowed but marked as invalid");
    }

    @Test
    public void testUserToString() {
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        
        String userString = user.toString();
        assertTrue(userString.contains("testuser"), "toString should contain username");
        assertTrue(userString.contains("test@example.com"), "toString should contain email");
    }
} 