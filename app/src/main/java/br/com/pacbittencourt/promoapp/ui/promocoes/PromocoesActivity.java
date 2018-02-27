package br.com.pacbittencourt.promoapp.ui.promocoes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.injection.component.ActivityComponent;
import br.com.pacbittencourt.promoapp.ui.base.BaseMvpLceActivity;

public final class PromocoesActivity
        extends BaseMvpLceActivity
                        <SwipeRefreshLayout, List<ResultsItem>, PromocoesView, PromocoesPresenter>
        implements PromocoesView, PromocoesAdapter.PromocaoClickListener,
                   SwipeRefreshLayout.OnRefreshListener {

    @Inject
    PromocoesPresenter promocoesPresenter;

    @Inject
    PromocoesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupListeners();
        presenter.onCreate();

    }

    private void setupListeners() {
        adapter.setListener(this);
        contentView.setOnRefreshListener(this);
    }

    private void setupRecyclerView() {
        RecyclerView rvPromocoes = findViewById(R.id.rv_all);
        rvPromocoes.setLayoutManager(new LinearLayoutManager(this));
        rvPromocoes.setAdapter(adapter);
    }

    @NonNull
    @Override
    public PromocoesPresenter createPresenter() {
        return promocoesPresenter;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void setData(List<ResultsItem> data) {
        adapter.setData(data);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.refresh();
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void onPromocaoClick(ResultsItem promocao) {
        presenter.onPromocaoClicked(promocao);
    }
}
