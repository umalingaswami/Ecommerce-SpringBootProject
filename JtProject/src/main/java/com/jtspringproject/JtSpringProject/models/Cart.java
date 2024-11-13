package com.jtspringproject.JtSpringProject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Cart implements Shoppable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private User customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public int getPrecio() {
        return cartItems.stream()
                     .mapToInt(item -> item.getProduct().getPrecio())
                     .sum();
    }

    @Override
    public String getName() {
        return "Cart-" + id;
    }

    @Override
    public void add(Shoppable item) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(this);
        cartItem.setProduct((Product)item);
        cartItems.add(cartItem);
    }

    @Override
    public void remove(Shoppable item) {
        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(item));
    }

    @Override
    public List<Shoppable> getChildren() {
        return cartItems.stream()
                     .map(CartItem::getProduct)
                     .collect(Collectors.toList());
    }
}
