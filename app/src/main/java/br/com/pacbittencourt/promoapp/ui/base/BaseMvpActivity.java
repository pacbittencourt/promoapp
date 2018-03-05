package br.com.pacbittencourt.promoapp.ui.base;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import br.com.pacbittencourt.promoapp.PromoAppApplication;
import br.com.pacbittencourt.promoapp.injection.component.ActivityComponent;
import br.com.pacbittencourt.promoapp.injection.component.DaggerActivityComponent;
import br.com.pacbittencourt.promoapp.injection.module.ActivityModule;

public abstract class BaseMvpActivity<V extends MvpView, P extends BaseRxPresenter<V>>
        extends MvpActivity<V, P> {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
    }

    private void injectDependencies() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(((PromoAppApplication) getApplication())
                            .getApplicationComponent())
                    .build();
        }
        inject(activityComponent);
    }

    protected abstract void inject(ActivityComponent activityComponent);

}
