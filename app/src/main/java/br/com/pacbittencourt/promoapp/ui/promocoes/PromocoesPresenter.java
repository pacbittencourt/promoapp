package br.com.pacbittencourt.promoapp.ui.promocoes;

import android.support.annotation.NonNull;

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

    @Override
    public void detachView() {
        super.detachView();
        getPromocoesUseCase.unsubscribe();
    }

    void onCreate() {
        ifViewAttached(new ViewAction<PromocoesView>() {
            @Override
            public void run(@NonNull PromocoesView view) {
                view.showLoading(false);
                carregarPromocoes();
            }
        });
    }

    private void carregarPromocoes() {
        Observer<Resultados> observer = getObserver(new Consumer<Resultados>() {
            @Override
            public void accept(Resultados resultados) throws Exception {
                PromocoesPresenter.this.onNext(resultados);
            }
        });
        getPromocoesUseCase.execute(observer);
    }

    private void onNext(final Resultados resultados) {
        ifViewAttached(new ViewAction<PromocoesView>() {
            @Override
            public void run(@NonNull PromocoesView view) {
                view.setData(resultados);
                view.showContent();
            }
        });
    }

    public void reload() {
        ifViewAttached(new ViewAction<PromocoesView>() {
            @Override
            public void run(@NonNull PromocoesView view) {
                view.showLoading(false);
                carregarPromocoes();
            }
        });
    }
}
