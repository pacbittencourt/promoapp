package br.com.pacbittencourt.promoapp.ui.promocoes;

import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.domain.usescases.GetPromocoesUseCase;
import br.com.pacbittencourt.promoapp.ui.base.BaseRxPresenter;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

class PromocoesPresenter
        extends BaseRxPresenter<PromocoesView> {

    private final GetPromocoesUseCase getPromocoesUseCase;


    @Inject
    PromocoesPresenter(GetPromocoesUseCase getPromocoesUseCase) {
        this.getPromocoesUseCase = getPromocoesUseCase;
    }

    void onCreate() {
        Observer<List<ResultsItem>> observer = getObserver(new Consumer<List<ResultsItem>>() {
            @Override
            public void accept(List<ResultsItem> resultados) throws Exception {
                PromocoesPresenter.this.onNext(resultados);
            }
        });
        getPromocoesUseCase.execute(observer);
    }

    private void onNext(List<ResultsItem> resultados) {

    }
}
