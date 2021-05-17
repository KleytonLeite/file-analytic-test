package com.example.demo.model;

import java.io.Serializable;

public class ItemVenda implements Serializable {
	
	private int id = 0;
	private String name = "";
	private double quantidade = 0;
	private double price = 0;
	
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
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", name=" + name + ", quantidade=" + quantidade + ", price=" + price + "]";
	}
	
	
	
	
	
}
