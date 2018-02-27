package br.com.pacbittencourt.promoapp.ui.produtos;

import android.os.Bundle;
import android.os.PersistableBundle;
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

    private ResultsItem promocao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        if (savedInstanceState != null &&
                savedInstanceState.getParcelable(KEY_RESULTS_ITEM_BUNDLE) != null) {
            promocao = savedInstanceState.getParcelable(KEY_RESULTS_ITEM_BUNDLE);
            adapter.setPromocao(promocao);
        } else {
            Bundle bundle = getIntent().getBundleExtra(KEY_RESULTS_ITEM);
            promocao = bundle.getParcelable(KEY_RESULTS_ITEM_BUNDLE);
            adapter.setPromocao(promocao);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(promocao.getNome());
        }

        setupRecyclerView();
        setupListener();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable(KEY_RESULTS_ITEM_BUNDLE, promocao);
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
    public void onProdutoClick(PromocoesItem promocoesItem) {
        presenter.onProdutoClicked(promocoesItem);
    }
}
