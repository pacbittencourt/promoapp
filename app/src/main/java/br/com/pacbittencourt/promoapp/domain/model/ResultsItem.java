package br.com.pacbittencourt.promoapp.domain.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("DataTermino")
	private String dataTermino;

	@SerializedName("Lojas")
	private List<LojasItem> lojas;

	@SerializedName("DataInicio")
	private String dataInicio;

	@SerializedName("Nome")
	private String nome;

	@SerializedName("Promocoes")
	private List<PromocoesItem> promocoes;

	@SerializedName("Arquivos")
	private List<Object> arquivos;

	public void setDataTermino(String dataTermino){
		this.dataTermino = dataTermino;
	}

	public String getDataTermino(){
		return dataTermino;
	}

	public void setLojas(List<LojasItem> lojas){
		this.lojas = lojas;
	}

	public List<LojasItem> getLojas(){
		return lojas;
	}

	public void setDataInicio(String dataInicio){
		this.dataInicio = dataInicio;
	}

	public String getDataInicio(){
		return dataInicio;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return nome;
	}

	public void setPromocoes(List<PromocoesItem> promocoes){
		this.promocoes = promocoes;
	}

	public List<PromocoesItem> getPromocoes(){
		return promocoes;
	}

	public void setArquivos(List<Object> arquivos){
		this.arquivos = arquivos;
	}

	public List<Object> getArquivos(){
		return arquivos;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"dataTermino = '" + dataTermino + '\'' + 
			",lojas = '" + lojas + '\'' + 
			",dataInicio = '" + dataInicio + '\'' + 
			",nome = '" + nome + '\'' + 
			",promocoes = '" + promocoes + '\'' + 
			",arquivos = '" + arquivos + '\'' + 
			"}";
		}
}