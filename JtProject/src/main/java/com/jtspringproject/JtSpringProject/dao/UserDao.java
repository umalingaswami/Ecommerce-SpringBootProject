package com.jtspringproject.JtSpringProject.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jtspringproject.JtSpringProject.models.User;

@Repository
@Transactional
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUser() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public User getUser(String username, String password) {
        String hql = "from User u where u.username = :username and u.password = :password";
        return entityManager.createQuery(hql, User.class)
            .setParameter("username", username)
            .setParameter("password", password)
            .getResultList()
            .stream().findFirst().orElse(null);
    }

    public boolean userExists(String username) {
        String hql = "select count(u) from User u where u.username = :username";
        Long count = entityManager.createQuery(hql, Long.class)
            .setParameter("username", username)
            .getSingleResult();
        return count != null && count > 0;
    }

    public User getUserByUsername(String username) {
        String hql = "from User u where u.username = :username";
        return entityManager.createQuery(hql, User.class)
            .setParameter("username", username)
            .getResultList()
            .stream().findFirst().orElse(null);
    }

    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }
}