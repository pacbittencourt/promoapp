package br.com.pacbittencourt.promoapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class LojasItem{

	@SerializedName("IdLoja")
	private int idLoja;

	@SerializedName("Nome")
	private String nome;

	public void setIdLoja(int idLoja){
		this.idLoja = idLoja;
	}

	public int getIdLoja(){
		return idLoja;
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
			"LojasItem{" + 
			"idLoja = '" + idLoja + '\'' + 
			",nome = '" + nome + '\'' + 
			"}";
		}
}