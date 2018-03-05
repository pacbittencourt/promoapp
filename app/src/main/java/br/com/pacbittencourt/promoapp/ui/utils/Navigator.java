package br.com.pacbittencourt.promoapp.ui.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.domain.model.PromocoesItem;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.injection.ActivityContext;
import br.com.pacbittencourt.promoapp.ui.detalhesproduto.DetalhesProdutoActivity;
import br.com.pacbittencourt.promoapp.ui.produtos.ProdutosActivity;

public final class Navigator {

    public final static String KEY_RESULTS_ITEM = "key_results_item";
    public final static String KEY_RESULTS_ITEM_BUNDLE = "key_results_item_bundle";
    public final static String KEY_PROMOCAO_ITEM = "key_promocao_item";
    public final static String KEY_PROMOCAO_ITEM_BUNDLE = "key_promocao_item_bundle";

    private final Context context;

    @Inject
    public Navigator(@ActivityContext Context context) {
        this.context = context;
    }

    public void goToProdutos(ResultsItem promocao) {
        Intent intent = new Intent(context, ProdutosActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_RESULTS_ITEM_BUNDLE, promocao);
        intent.putExtra(KEY_RESULTS_ITEM, bundle);
        context.startActivity(intent);
    }

    public void goToDetalhes(PromocoesItem item) {
        Intent intent = new Intent(context, DetalhesProdutoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PROMOCAO_ITEM_BUNDLE, item);
        intent.putExtra(KEY_PROMOCAO_ITEM, bundle);
        context.startActivity(intent);
    }
}
