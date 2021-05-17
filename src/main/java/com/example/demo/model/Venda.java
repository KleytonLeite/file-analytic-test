package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venda implements Serializable{
	private int id = 0;
	private String vendedor = "";
	private ArrayList<ItemVenda> itens;
	private double total = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public ArrayList<ItemVenda> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ItemVenda> itens) {
		this.itens = itens;
	}
	@Override
	public String toString() {
		return "Venda [id=" + id + ", vendedor=" + vendedor + ", itens=" + itens + "]";
	}
	
	public double getTotal() {
		double valorTotal = 0;
		for (int i = 0; i < itens.size(); i++) {
			ItemVenda item = itens.get(i);
			valorTotal+= (item.getQuantidade()* item.getPrice());
		}
		return valorTotal;
	}
	
	
}
