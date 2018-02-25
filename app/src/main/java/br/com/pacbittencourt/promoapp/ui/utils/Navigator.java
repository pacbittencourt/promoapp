package br.com.pacbittencourt.promoapp.ui.utils;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.injection.ActivityContext;
import br.com.pacbittencourt.promoapp.ui.produtos.ProdutosActivity;

public final class Navigator {

    public final static String KEY_RESULTS_ITEM = "key_results_item";

    private final Context context;

    @Inject
    public Navigator(@ActivityContext Context context) {
        this.context = context;
    }

    public void goToProdutos(ResultsItem promocao, int position) {
        Intent intent = new Intent(context, ProdutosActivity.class);
        intent.putExtra(KEY_RESULTS_ITEM, promocao);
        context.startActivity(intent);
    }
}
