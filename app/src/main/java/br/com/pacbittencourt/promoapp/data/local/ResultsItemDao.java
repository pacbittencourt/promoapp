package br.com.pacbittencourt.promoapp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import io.reactivex.Maybe;

@Dao
public interface ResultsItemDao {

    @Query("SELECT * FROM ResultsItem")
    Maybe<List<ResultsItem>> getTodos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ResultsItem resultsItems);
}
