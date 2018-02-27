package br.com.pacbittencourt.promoapp.data.local.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import br.com.pacbittencourt.promoapp.domain.model.Produto;

public class ProdutoTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static Produto stringToObjectList(String data) {
        if (data == null) return new Produto();
        return gson.fromJson(data, Produto.class);
    }

    @TypeConverter
    public static String objectListToString(Produto objects) {
        return gson.toJson(objects);
    }
}
