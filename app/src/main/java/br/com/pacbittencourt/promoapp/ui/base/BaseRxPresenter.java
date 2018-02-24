package br.com.pacbittencourt.promoapp.ui.base;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseRxPresenter<V extends MvpView>
        extends MvpBasePresenter<V> {

    @NonNull
    protected <T> Observer<T> getObserver(final Consumer<T> onNextAction) {
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(T t) {
                try {
                    onNextAction.accept(t);
                } catch (Exception e) {
                    e.printStackTrace();
                    onError(e);
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
            }
        };
    }

}
