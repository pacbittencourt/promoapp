package br.com.pacbittencourt.promoapp.ui.produtos;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItemType.CATEGORIA;
import static br.com.pacbittencourt.promoapp.ui.produtos.ProdutosAdapterItemType.ITEM;

@Retention(RetentionPolicy.SOURCE)
@IntDef(flag = true, value = {ITEM, CATEGORIA})
public @interface ProdutosAdapterItemType {
    int ITEM = 0;
    int CATEGORIA = 1;
}
