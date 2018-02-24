package br.com.pacbittencourt.promoapp.ui.promocoes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.injection.component.ActivityComponent;
import br.com.pacbittencourt.promoapp.ui.base.BaseMvpLceActivity;

public final class PromocoesActivity
        extends BaseMvpLceActivity
                        <ViewPager, List<ResultsItem>, PromocoesView, PromocoesPresenter> {

    @Inject
    PromocoesPresenter mainPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.onCreate();
    }

    @NonNull
    @Override
    public PromocoesPresenter createPresenter() {
        return mainPresenter;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(List<ResultsItem> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
