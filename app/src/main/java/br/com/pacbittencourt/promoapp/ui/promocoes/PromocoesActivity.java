package br.com.pacbittencourt.promoapp.ui.promocoes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.Resultados;
import br.com.pacbittencourt.promoapp.injection.component.ActivityComponent;
import br.com.pacbittencourt.promoapp.ui.base.BaseMvpLceActivity;

public final class PromocoesActivity
        extends BaseMvpLceActivity
                        <SwipeRefreshLayout, Resultados, PromocoesView, PromocoesPresenter>
        implements PromocoesView {

    @Inject
    PromocoesPresenter mainPresenter;

    @Inject
    PromocoesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        presenter.onCreate();
    }

    private void setupRecyclerView() {
        RecyclerView rvPromocoes = findViewById(R.id.rv_all);
        rvPromocoes.setAdapter(adapter);
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
    public void setData(Resultados data) {
        adapter.setData(data);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
