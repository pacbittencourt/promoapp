package br.com.pacbittencourt.promoapp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;

@Database(entities = {ResultsItem.class}, version = 1)
public abstract class AppDatabase
        extends RoomDatabase {
    public abstract ResultsItemDao resultsItemDao();
}
