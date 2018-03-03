package br.com.pacbittencourt.promoapp.ui.produtos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Filter;

import java.util.List;

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
        implements ProdutosView, ProdutosAdapter.OnProdutoClickListener,
                   ProdutosCategoriaFiltroDialogFragment.FiltroCategoriaDialogListener,
                   Filter.FilterListener {

    private static final String FILTRO_CATEGORIA_DIALOG_TAG = "categoria_filtro_dialog_tag";
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

        if (promocao != null) {
            presenter.onCreate(promocao);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(promocao.getNome());
            }
        }

        setupRecyclerView();
        setupListener();
    }

    private void setupListener() {
        adapter.setListener(this, this);
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

    @Override
    public void setData(ResultsItem resultsItem) {
        adapter.setPromocao(resultsItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.produtos_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_categorias_filtro:
                presenter.onFiltroClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFiltroCategorias(int filtroSelecionado) {
        List<String> categorias = adapter.getCategorias();
        if (categorias != null) {
            ProdutosCategoriaFiltroDialogFragment fragment =
                    ProdutosCategoriaFiltroDialogFragment.newInstance(categorias,
                            filtroSelecionado);
            fragment.setListener(this);
            fragment.show(getSupportFragmentManager(), FILTRO_CATEGORIA_DIALOG_TAG);
        }
    }

    @Override
    public void setCategorias(List<String> categorias) {
        adapter.setCategorias(categorias);
    }

    @Override
    public void onFilterComplete(int count) {
        presenter.onFilterComplete(count);
    }

    @Override
    public void onFiltroCategoriaDialogConfirmClick(int selecionado) {
        presenter.onCategoriaSelecionada(selecionado);
        adapter.filtrar(selecionado);
    }
}
