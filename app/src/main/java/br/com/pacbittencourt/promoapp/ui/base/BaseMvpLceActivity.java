package br.com.pacbittencourt.promoapp.ui.base;

import android.os.Bundle;
import android.view.View;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import br.com.pacbittencourt.promoapp.PromoAppApplication;
import br.com.pacbittencourt.promoapp.injection.component.ActivityComponent;
import br.com.pacbittencourt.promoapp.injection.component.DaggerActivityComponent;
import br.com.pacbittencourt.promoapp.injection.module.ActivityModule;

public abstract class BaseMvpLceActivity<CV extends View, M,
        V extends MvpLceView<M>, P extends BaseRxPresenter<V>>
        extends MvpLceActivity<CV, M, V, P> {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
