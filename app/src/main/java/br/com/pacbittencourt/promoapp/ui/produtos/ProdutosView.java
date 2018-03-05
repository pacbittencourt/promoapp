package br.com.pacbittencourt.promoapp.ui.produtos;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;

interface ProdutosView
        extends MvpView {
    void setData(ResultsItem resultsItem);

    void setCategorias(List<String> categorias);

    void showFiltroCategorias(int filtroSelecionado);
}
