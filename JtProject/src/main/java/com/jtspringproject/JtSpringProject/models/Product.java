package com.jtspringproject.JtSpringProject.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {

	public Product(int id, String name, String image, Category category, int quantity, int price, int weight,
			String description, User customer) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.customer = customer;
	}
	
	public Product() {
		super();
	}
	
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String image;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id",referencedColumnName = "category_id")
	private Category category;
	
	private int quantity;
	
	private int price;
	
	private int weight;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
	
}
