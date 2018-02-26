package br.com.pacbittencourt.promoapp.ui.produtos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.R;
import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.injection.component.ActivityComponent;
import br.com.pacbittencourt.promoapp.ui.base.BaseMvpActivity;

import static br.com.pacbittencourt.promoapp.ui.utils.Navigator.KEY_RESULTS_ITEM;
import static br.com.pacbittencourt.promoapp.ui.utils.Navigator.KEY_RESULTS_ITEM_BUNDLE;

public final class ProdutosActivity
        extends BaseMvpActivity<ProdutosView, ProdutosPresenter>
        implements ProdutosView, ProdutosAdapter.OnProdutoClickListener {

    @Inject
    ProdutosPresenter produtosPresenter;

    @Inject
    ProdutosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        Bundle bundle = getIntent().getBundleExtra(KEY_RESULTS_ITEM);
        ResultsItem promocao = bundle.getParcelable(KEY_RESULTS_ITEM_BUNDLE);
        adapter.setPromocao(promocao);

        setupRecyclerView();
        setupListener();
    }

    private void setupListener() {
        adapter.setListener(this);
    }

    private void setupRecyclerView() {
        RecyclerView rvProdutos = findViewById(R.id.rv_all);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        rvProdutos.setAdapter(adapter);
    }

    @NonNull
    @Override
    public ProdutosPresenter createPresenter() {
        return produtosPresenter;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void onPordutoClick(PromocoesItem promocoesItem) {
        presenter.onProdutoClicked(promocoesItem);
    }
}
