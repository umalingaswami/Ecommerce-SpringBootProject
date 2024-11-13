package com.jtspringproject.JtSpringProject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="CATEGORY")
public class Category implements Shoppable {
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
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

	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();
	
	@Override
	public int getPrecio() {
		return products.stream()
				   .mapToInt(Shoppable::getPrecio)
				   .sum();
	}

	@Override
	public void add(Shoppable item) {
		if (item instanceof Product) {
			products.add((Product) item);
		}
	}

	@Override
	public void remove(Shoppable item) {
		if (item instanceof Product) {
			products.remove(item);
		}
	}

	@Override
	public List<Shoppable> getChildren() {
		return new ArrayList<>(products);
	}
}
