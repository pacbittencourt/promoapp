package br.com.pacbittencourt.promoapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class Categoria{

	@SerializedName("IdCategoria")
	private int idCategoria;

	@SerializedName("Nome")
	private String nome;

	public void setIdCategoria(int idCategoria){
		this.idCategoria = idCategoria;
	}

	public int getIdCategoria(){
		return idCategoria;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return nome;
	}

	@Override
 	public String toString(){
		return 
			"Categoria{" + 
			"idCategoria = '" + idCategoria + '\'' + 
			",nome = '" + nome + '\'' + 
			"}";
		}
}