package br.com.pacbittencourt.promoapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class PromocoesItem{

	@SerializedName("UrlImage")
	private String urlImage;

	@SerializedName("DataTermino")
	private String dataTermino;

	@SerializedName("Preco")
	private double preco;

	@SerializedName("Categoria")
	private Categoria categoria;

	@SerializedName("DataInicio")
	private String dataInicio;

	@SerializedName("Titulo")
	private String titulo;

	@SerializedName("PrecoAnterior")
	private Object precoAnterior;

	@SerializedName("Produto")
	private Produto produto;

	public void setUrlImage(String urlImage){
		this.urlImage = urlImage;
	}

	public String getUrlImage(){
		return urlImage;
	}

	public void setDataTermino(String dataTermino){
		this.dataTermino = dataTermino;
	}

	public String getDataTermino(){
		return dataTermino;
	}

	public void setPreco(double preco){
		this.preco = preco;
	}

	public double getPreco(){
		return preco;
	}

	public void setCategoria(Categoria categoria){
		this.categoria = categoria;
	}

	public Categoria getCategoria(){
		return categoria;
	}

	public void setDataInicio(String dataInicio){
		this.dataInicio = dataInicio;
	}

	public String getDataInicio(){
		return dataInicio;
	}

	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public String getTitulo(){
		return titulo;
	}

	public void setPrecoAnterior(Object precoAnterior){
		this.precoAnterior = precoAnterior;
	}

	public Object getPrecoAnterior(){
		return precoAnterior;
	}

	public void setProduto(Produto produto){
		this.produto = produto;
	}

	public Produto getProduto(){
		return produto;
	}

	@Override
 	public String toString(){
		return 
			"PromocoesItem{" + 
			"urlImage = '" + urlImage + '\'' + 
			",dataTermino = '" + dataTermino + '\'' + 
			",preco = '" + preco + '\'' + 
			",categoria = '" + categoria + '\'' + 
			",dataInicio = '" + dataInicio + '\'' + 
			",titulo = '" + titulo + '\'' + 
			",precoAnterior = '" + precoAnterior + '\'' + 
			",produto = '" + produto + '\'' + 
			"}";
		}
}