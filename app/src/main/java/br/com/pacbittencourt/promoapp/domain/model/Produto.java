package br.com.pacbittencourt.promoapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class Produto{

	@SerializedName("IdProduto")
	private int idProduto;

	@SerializedName("CodigoInterno")
	private String codigoInterno;

	@SerializedName("CodigoBarras")
	private Object codigoBarras;

	@SerializedName("NomeProdutoBase")
	private String nomeProdutoBase;

	public void setIdProduto(int idProduto){
		this.idProduto = idProduto;
	}

	public int getIdProduto(){
		return idProduto;
	}

	public void setCodigoInterno(String codigoInterno){
		this.codigoInterno = codigoInterno;
	}

	public String getCodigoInterno(){
		return codigoInterno;
	}

	public void setCodigoBarras(Object codigoBarras){
		this.codigoBarras = codigoBarras;
	}

	public Object getCodigoBarras(){
		return codigoBarras;
	}

	public void setNomeProdutoBase(String nomeProdutoBase){
		this.nomeProdutoBase = nomeProdutoBase;
	}

	public String getNomeProdutoBase(){
		return nomeProdutoBase;
	}

	@Override
 	public String toString(){
		return 
			"Produto{" + 
			"idProduto = '" + idProduto + '\'' + 
			",codigoInterno = '" + codigoInterno + '\'' + 
			",codigoBarras = '" + codigoBarras + '\'' + 
			",nomeProdutoBase = '" + nomeProdutoBase + '\'' + 
			"}";
		}
}