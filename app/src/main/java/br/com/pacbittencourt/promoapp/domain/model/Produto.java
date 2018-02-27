package br.com.pacbittencourt.promoapp.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Produto
        implements Parcelable {

    @SerializedName("IdProduto")
    private int idProduto;

    @SerializedName("CodigoInterno")
    private String codigoInterno;

    @SerializedName("NomeProdutoBase")
    private String nomeProdutoBase;

    public Produto() {
    }

    public Produto(int idProduto, String codigoInterno, String
            nomeProdutoBase) {
        this.idProduto = idProduto;
        this.codigoInterno = codigoInterno;
        this.nomeProdutoBase = nomeProdutoBase;
    }

    private Produto(Parcel in) {
        idProduto = in.readInt();
        codigoInterno = in.readString();
        nomeProdutoBase = in.readString();
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setNomeProdutoBase(String nomeProdutoBase) {
        this.nomeProdutoBase = nomeProdutoBase;
    }

    public String getNomeProdutoBase() {
        return nomeProdutoBase;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idProduto);
        parcel.writeString(codigoInterno);
        parcel.writeString(nomeProdutoBase);
    }
}