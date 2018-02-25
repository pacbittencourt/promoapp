package br.com.pacbittencourt.promoapp.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultsItem
        implements Parcelable {

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

    public ResultsItem(Parcel in) {
        this.dataTermino = in.readString();
        this.dataInicio = in.readString();
        this.nome = in.readString();

        this.promocoes = new ArrayList<PromocoesItem>();
        in.readTypedList(promocoes, PromocoesItem.CREATOR);
    }

    public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
        @Override
        public ResultsItem createFromParcel(Parcel in) {
            return new ResultsItem(in);
        }

        @Override
        public ResultsItem[] newArray(int size) {
            return new ResultsItem[size];
        }
    };

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setLojas(List<LojasItem> lojas) {
        this.lojas = lojas;
    }

    public List<LojasItem> getLojas() {
        return lojas;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPromocoes(List<PromocoesItem> promocoes) {
        this.promocoes = promocoes;
    }

    public List<PromocoesItem> getPromocoes() {
        return promocoes;
    }

    public void setArquivos(List<Object> arquivos) {
        this.arquivos = arquivos;
    }

    public List<Object> getArquivos() {
        return arquivos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dataTermino);
        parcel.writeString(dataInicio);
        parcel.writeString(nome);

        parcel.writeTypedList(promocoes);
    }
}