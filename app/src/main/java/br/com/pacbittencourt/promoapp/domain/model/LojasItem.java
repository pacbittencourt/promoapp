package br.com.pacbittencourt.promoapp.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LojasItem
        implements Parcelable {

    @SerializedName("IdLoja")
    private int idLoja;

    @SerializedName("Nome")
    private String nome;

    private LojasItem(Parcel in) {
        idLoja = in.readInt();
        nome = in.readString();
    }

    static final Creator<LojasItem> CREATOR = new Creator<LojasItem>() {
        @Override
        public LojasItem createFromParcel(Parcel in) {
            return new LojasItem(in);
        }

        @Override
        public LojasItem[] newArray(int size) {
            return new LojasItem[size];
        }
    };

    public void setIdLoja(int idLoja) {
        this.idLoja = idLoja;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idLoja);
        parcel.writeString(nome);
    }
}