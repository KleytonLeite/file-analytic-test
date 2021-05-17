package com.example.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.Cliente;
import com.example.demo.model.ItemVenda;
import com.example.demo.model.Venda;
import com.example.demo.model.Vendedor;

public class IntegradorArquivo {
	public static final String PASTA = System.getProperty("user.dir")+"/data/";
	public static boolean procesarArquivo(String pathPasta) {
		boolean processando = false;
		if (pathPasta.length() == 0) {
			// CRIAR EXCEÇÃO PASTA NÃO EXISTENTE
			System.out.println("PASTA NÃO EXISTE");
		}
		File pasta = new File(PASTA+"in");
		if(!pasta.exists()) {
			System.out.println("PASTA NÃO EXISTE");
		}
		String[] arquivos = pasta.list();
		Date agora = new Date();
		ArrayList<Venda> listaVendas = null;
		int quantidadeClientes = 0;
		int quantidadeVendedores = 0;
		if (arquivos != null && arquivos.length > 0) {
			for (int i = 0; i < arquivos.length; i++) {
				File arquivo = new File(pasta + "/" + arquivos[i]);
				String linha = "";
				BufferedReader br = null;
				FileReader fr = null;
				try {
					fr = new FileReader(arquivo);
					br = new BufferedReader(fr);
					linha = br.readLine();
					Vendedor vendedor = null;
					Cliente cliente = null;
					Venda venda = null;
					
					listaVendas = new ArrayList<Venda>();
					while (linha != null) {
						String[] campos = linha.trim().split("\\ç");
						if (campos != null && campos.length > 0) {
							// VERIFICANDO IDENTIFICADOR
							if (campos[0].equalsIgnoreCase("001")) {
								vendedor = new Vendedor();
								vendedor.setCpf(campos[1]);
								vendedor.setNome(campos[2]);
								vendedor.setSalario(Double.parseDouble(campos[3].toString()));
								quantidadeVendedores++;
								
								// GRAVAR EM QUALQUER LUGAR BANCO MEMORIA, ARQUIVO
							}
							if (campos[0].equalsIgnoreCase("002")) {
								cliente = new Cliente();
								cliente.setCnpj(campos[1].toString());
								cliente.setNome(campos[2].toString());
								cliente.setAreaVenda(campos[3].toString());
								quantidadeClientes++;
								// GRAVAR EM QUALQUER LUGAR BANCO MEMORIA, ARQUIVO
							}
							if (campos[0].equalsIgnoreCase("003")) {
								venda = new Venda();
								venda.setId(Integer.parseInt(campos[1].toString()));
								String[] camposItens = campos[2].trim().split("\\,");
								if (camposItens.length > 0) {
									
									String[] camposItem = camposItens[0].trim().split("\\-");
									if (camposItem.length > 0) {
										ItemVenda item = new ItemVenda();
										item.setId(Integer.parseInt(camposItem[0].toString()));
										item.setQuantidade(Double.parseDouble(camposItem[1].toString()));
										item.setPrice(Double.parseDouble(camposItem[2].toString()));
										venda.getItens().add(item);
									}
								}
								venda.setVendedor(campos[3].toString());
								listaVendas.add(venda);
								//GRAVAR EM QUALQUER
							}
						}
					}
					processando =  true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				retornoProcessamento(quantidadeClientes,quantidadeVendedores,listaVendas,"");
			}
			
		}else{
			processando= false;
		}
		return processando;
		
	}
	
	
	public static void retornoProcessamento(int quantidadeClientes,int quantidadeVendedores,ArrayList<Venda> lista,String caminhoSaida) {
		File arquivoSaida = new File(PASTA+"out/Resultado.txt");
		PrintStream out;
		try {
			out =  new PrintStream(arquivoSaida);
			arquivoSaida.createNewFile();
			out.append("|");
			out.append(quantidadeClientes+"");
			out.append("|");
			out.append(quantidadeVendedores+"");
			out.append("|");
			out.append(getMaiorVenda(lista)+"");
			out.append("|");
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static int getMaiorVenda(ArrayList<Venda> lista) {
		double maior = 0;
		int id = 0;
		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getTotal()>maior) {
				maior = lista.get(i).getTotal();
				id = lista.get(i).getId();
			}
		}
		return id;
	}
	
	public static void main(String[] args) {
		System.out.println(procesarArquivo(PASTA));
	}
}
