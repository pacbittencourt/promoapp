package br.com.pacbittencourt.promoapp.domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.com.pacbittencourt.promoapp.data.local.converters.CategoriaTypeConverter;
import br.com.pacbittencourt.promoapp.data.local.converters.ProdutoTypeConverter;
import br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItem;
import br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItemType;

@Entity
public class PromocoesItem
        implements ProdutosAdapterItem, Parcelable {

    @PrimaryKey
    private int id;

    @ForeignKey(
            entity = ResultsItem.class,
            parentColumns = "id",
            childColumns = "idResultsItem")
    private int idResultsItem;

    @SerializedName("UrlImage")
    private String urlImage;

    @SerializedName("DataTermino")
    private String dataTermino;

    @SerializedName("Preco")
    private double preco;

    @TypeConverters(CategoriaTypeConverter.class)
    @SerializedName("Categoria")
    private Categoria categoria;

    @SerializedName("DataInicio")
    private String dataInicio;

    @SerializedName("Titulo")
    private String titulo;

    @TypeConverters(ProdutoTypeConverter.class)
    @SerializedName("Produto")
    private Produto produto;

    public PromocoesItem() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdResultsItem() {
        return idResultsItem;
    }

    public void setIdResultsItem(int idResultsItem) {
        this.idResultsItem = idResultsItem;
    }

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

    @Override
    public int getViewType() {
        return ProdutosAdapterItemType.ITEM;
    }
}