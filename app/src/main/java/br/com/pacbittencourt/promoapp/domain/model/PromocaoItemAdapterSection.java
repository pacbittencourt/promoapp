package br.com.pacbittencourt.promoapp.domain.model;

import br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItem;
import br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItemType;

public final class PromocaoItemAdapterSection
        implements ProdutosAdapterItem {

    private final String nome;

    public PromocaoItemAdapterSection(String nome) {
        this.nome = nome;
    }

    @Override
    public int getViewType() {
        return ProdutosAdapterItemType.CATEGORIA;
    }

    public String getNome() {
        return nome;
    }
}
