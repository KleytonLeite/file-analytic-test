package com.example.demo.model;

import java.io.Serializable;

public class Cliente implements Serializable{
	private int id = 0;
	private String nome = "";
	private String cnpj = "";
	private String areaVenda = "";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getAreaVenda() {
		return areaVenda;
	}
	public void setAreaVenda(String areaVenda) {
		this.areaVenda = areaVenda;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", areaVenda=" + areaVenda + "]";
	}
	
	
	
	
}
