package br.com.pacbittencourt.promoapp.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.injection.ActivityContext;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule
        .POST_EXECUTION_THREAD;
import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule.THREAD;

public class LocalCacheManager {

    private static final String TAG = LocalCacheManager.class.getSimpleName();

    private static final String DB_NAME = "database-name";
    private AppDatabase db;
    private Scheduler threadExecutor;
    private Scheduler postThreadExecutor;

    @Inject
    public LocalCacheManager(@ActivityContext Context context,
                             @Named(THREAD) Scheduler threadExecutor,
                             @Named(POST_EXECUTION_THREAD) Scheduler postThreadExecutor) {
        this.db = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration().build();
        this.threadExecutor = threadExecutor;
        this.postThreadExecutor = postThreadExecutor;
    }

    public void insertResultsItem(final ResultsItem resultsItem) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.resultsItemDao().insert(resultsItem);
            }
        }).observeOn(postThreadExecutor)
                .subscribeOn(threadExecutor).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public Maybe<List<ResultsItem>> getAllResultItems() {
        return db.resultsItemDao().getTodos()
                .subscribeOn(threadExecutor)
                .observeOn(postThreadExecutor);
    }

}
