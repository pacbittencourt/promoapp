package br.com.pacbittencourt.promoapp.ui.promocoes;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import java.util.List;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;

interface PromocoesView
        extends MvpLceView<List<ResultsItem>> {
}
