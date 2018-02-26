package br.com.pacbittencourt.promoapp.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PromocoesItem
        implements Parcelable {

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

    private PromocoesItem(Parcel in) {
        this.categoria = in.readParcelable(Categoria.class.getClassLoader());
        this.produto = in.readParcelable(Produto.class.getClassLoader());
        urlImage = in.readString();
        dataTermino = in.readString();
        preco = in.readDouble();
        dataInicio = in.readString();
        titulo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(categoria, i);
        parcel.writeParcelable(produto, i);
        parcel.writeString(urlImage);
        parcel.writeString(dataTermino);
        parcel.writeDouble(preco);
        parcel.writeString(dataInicio);
        parcel.writeString(titulo);
    }

    static final Creator<PromocoesItem> CREATOR = new Creator<PromocoesItem>() {
        @Override
        public PromocoesItem createFromParcel(Parcel in) {
            return new PromocoesItem(in);
        }

        @Override
        public PromocoesItem[] newArray(int size) {
            return new PromocoesItem[size];
        }
    };

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setPrecoAnterior(Object precoAnterior) {
        this.precoAnterior = precoAnterior;
    }

    public Object getPrecoAnterior() {
        return precoAnterior;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public Produto getProduto() {
        return produto;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}