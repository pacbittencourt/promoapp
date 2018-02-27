package br.com.pacbittencourt.promoapp.data.local.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import br.com.pacbittencourt.promoapp.domain.model.Categoria;

public class CategoriaTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static Categoria stringToObjectList(String data) {
        if (data == null) return new Categoria();
        return gson.fromJson(data, Categoria.class);
    }

    @TypeConverter
    public static String objectListToString(Categoria objects) {
        return gson.toJson(objects);
    }
}
