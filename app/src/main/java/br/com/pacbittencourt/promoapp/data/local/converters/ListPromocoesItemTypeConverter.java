package br.com.pacbittencourt.promoapp.data.local.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;

public class ListPromocoesItemTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<PromocoesItem> stringToObjectList(String data) {
        if (data == null) return Collections.emptyList();
        Type listType = new TypeToken<List<PromocoesItem>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String objectListToString(List<PromocoesItem> objects) {
        return gson.toJson(objects);
    }
}
