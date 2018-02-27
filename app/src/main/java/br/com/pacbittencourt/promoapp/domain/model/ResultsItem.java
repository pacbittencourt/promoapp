package br.com.pacbittencourt.promoapp.domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import br.com.pacbittencourt.promoapp.data.local.converters.ListLojasItemTypeConverter;
import br.com.pacbittencourt.promoapp.data.local.converters.ListPromocoesItemTypeConverter;

@Entity
public class ResultsItem
        implements Parcelable {

    @PrimaryKey
    private int id;

    @SerializedName("DataTermino")
    private String dataTermino;

    @TypeConverters(ListLojasItemTypeConverter.class)
    @SerializedName("Lojas")
    private List<LojasItem> lojas;

    @SerializedName("DataInicio")
    private String dataInicio;

    @SerializedName("Nome")
    private String nome;

    @TypeConverters(ListPromocoesItemTypeConverter.class)
    @SerializedName("Promocoes")
    private List<PromocoesItem> promocoes;

    public ResultsItem() {
    }

    private ResultsItem(Parcel in) {
        this.dataTermino = in.readString();
        this.dataInicio = in.readString();
        this.nome = in.readString();

        this.lojas = new ArrayList<LojasItem>();
        in.readTypedList(lojas,LojasItem.CREATOR);
        this.promocoes = new ArrayList<PromocoesItem>();
        in.readTypedList(promocoes, PromocoesItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dataTermino);
        parcel.writeString(dataInicio);
        parcel.writeString(nome);

        parcel.writeTypedList(lojas);
        parcel.writeTypedList(promocoes);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }
}