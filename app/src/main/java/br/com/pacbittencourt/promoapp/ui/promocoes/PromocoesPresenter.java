package br.com.pacbittencourt.promoapp.ui.promocoes;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.domain.model.Resultados;
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
        Observer<Resultados> observer = getObserver(new Consumer<Resultados>() {
            @Override
            public void accept(Resultados resultados) throws Exception {
                PromocoesPresenter.this.onNext(resultados);
            }
        });
        getPromocoesUseCase.execute(observer);
    }

    private void onNext(Resultados resultados) {

    }
}
