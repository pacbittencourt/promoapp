package br.com.pacbittencourt.promoapp.ui.produtos;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;
import br.com.pacbittencourt.promoapp.ui.base.BaseRxPresenter;

class ProdutosPresenter
        extends BaseRxPresenter<ProdutosView> {

    @Inject
    ProdutosPresenter() {
    }

    void onProdutoClicked(PromocoesItem promocoesItem) {
        navigator.goToDetalhes(promocoesItem);
    }
}
