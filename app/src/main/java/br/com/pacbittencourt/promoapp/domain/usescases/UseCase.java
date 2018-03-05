package br.com.pacbittencourt.promoapp.domain.usescases;

import javax.inject.Named;

import br.com.pacbittencourt.promoapp.domain.repositories.Repository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule
        .POST_EXECUTION_THREAD;
import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule.THREAD;

abstract class UseCase<RESPONSE_DATA, REPOSITORY extends Repository> {

    final REPOSITORY repository;

    private final Scheduler threadExecutor;

    private final Scheduler postThreadExecutor;

    private CompositeDisposable disposable = new CompositeDisposable();


    protected UseCase(REPOSITORY repository,
                      @Named(THREAD) Scheduler threadExecutor,
                      @Named(POST_EXECUTION_THREAD) Scheduler postThreadExecutor) {
        this.repository = repository;
        this.threadExecutor = threadExecutor;
        this.postThreadExecutor = postThreadExecutor;
    }

    protected abstract Observable<RESPONSE_DATA> buildObservable(Void aVoid);

    public void execute(Observer<RESPONSE_DATA> observer) {
        buildObservable(null)
                .subscribeOn(threadExecutor)
                .observeOn(postThreadExecutor)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        recreateDisposablesIfNeeded();
                        UseCase.this.disposable.add(disposable);
                    }
                })
                .subscribe(observer);
    }

    private void recreateDisposablesIfNeeded() {
        if (disposable.isDisposed()) {
            disposable = new CompositeDisposable();
        }
    }

    public void unsubscribe() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
