package br.com.pacbittencourt.promoapp.ui.produtos;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.ui.base.BaseRxPresenter;

class ProdutosPresenter
        extends BaseRxPresenter<ProdutosView> {

    private int filtroSelecionado = 0;

    @Inject
    ProdutosPresenter() {
    }

    void onCreate(final ResultsItem resultsItem) {
        ifViewAttached(new ViewAction<ProdutosView>() {
            @Override
            public void run(@NonNull ProdutosView view) {
                if (resultsItem != null) {
                    view.setCategorias(getCategorias(resultsItem));
                    view.setData(resultsItem);
                }
            }
        });
    }

    private List<String> getCategorias(ResultsItem resultsItem) {
        List<String> categorias = new ArrayList<>();
        for (PromocoesItem promocoesItem : resultsItem.getPromocoes()) {
            if (categorias.indexOf(promocoesItem.getCategoria().getNome()) == -1) {
                categorias.add(promocoesItem.getCategoria().getNome());
            }
        }
        return categorias;
    }

    void onProdutoClicked(PromocoesItem promocoesItem) {
        navigator.goToDetalhes(promocoesItem);
    }

    void onFiltroClick() {
        ifViewAttached(new ViewAction<ProdutosView>() {
            @Override
            public void run(@NonNull ProdutosView view) {
                view.showFiltroCategorias(filtroSelecionado);
            }
        });
    }

    void onFilterComplete(int count) {
    }

    void onCategoriaSelecionada(int selecionado) {
        filtroSelecionado = selecionado;
    }
}
