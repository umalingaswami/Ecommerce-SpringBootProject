package com.jtspringproject.JtSpringProject.repository;

import com.jtspringproject.JtSpringProject.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
