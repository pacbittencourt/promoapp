package br.com.pacbittencourt.promoapp.ui.promocoes;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.data.local.LocalCacheManager;
import br.com.pacbittencourt.promoapp.domain.model.Resultados;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.domain.usescases.GetPromocoesUseCase;
import br.com.pacbittencourt.promoapp.injection.ActivityContext;
import br.com.pacbittencourt.promoapp.ui.base.BaseRxPresenter;
import br.com.pacbittencourt.promoapp.ui.utils.NetworkUtils;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

class PromocoesPresenter
        extends BaseRxPresenter<PromocoesView> {

    private final GetPromocoesUseCase getPromocoesUseCase;

    private LocalCacheManager localCacheManager;

    private Context context;

    @Inject
    PromocoesPresenter(@ActivityContext Context context,
                       GetPromocoesUseCase getPromocoesUseCase,
                       LocalCacheManager localCacheManager) {
        this.context = context;
        this.getPromocoesUseCase = getPromocoesUseCase;
        this.localCacheManager = localCacheManager;
    }

    @Override
    public void detachView() {
        super.detachView();
        getPromocoesUseCase.unsubscribe();
    }

    void onCreate() {
        carregarDados();
    }

    void refresh() {
        carregarDados();
    }

    private void carregarDados() {
        ifViewAttached(new ViewAction<PromocoesView>() {
            @Override
            public void run(@NonNull PromocoesView view) {
                view.showLoading(false);
                if (NetworkUtils.isOnline(context)) {
                    carregarPromocoes();
                } else {
                    carregarPromocoesOffline();
                }
            }
        });
    }

    private void carregarPromocoesOffline() {
        localCacheManager.getAllResultItems().subscribe(new Consumer<List<ResultsItem>>() {
            @Override
            public void accept(List<ResultsItem> resultsItems) throws Exception {
                PromocoesPresenter.this.onNextResultadosOffline(resultsItems);
            }
        });
    }

    private void onNextResultadosOffline(final List<ResultsItem> resultsItems) {
        ifViewAttached(new ViewAction<PromocoesView>() {
            @Override
            public void run(@NonNull PromocoesView view) {
                view.setData(resultsItems);
                view.showContent();
            }
        });
    }

    private void carregarPromocoes() {
        Observer<Resultados> observer = getObserver(new Consumer<Resultados>() {
            @Override
            public void accept(Resultados resultados) throws Exception {
                PromocoesPresenter.this.onNextResultadosOnline(resultados);
            }
        });
        getPromocoesUseCase.execute(observer);
    }

    private void onNextResultadosOnline(final Resultados resultados) {
        ifViewAttached(new ViewAction<PromocoesView>() {
            @Override
            public void run(@NonNull PromocoesView view) {
                saveToDatabase(resultados);
                view.setData(resultados.getResults());
                view.showContent();
            }
        });
    }

    private void saveToDatabase(Resultados resultados) {
        int i = 0;
        for (ResultsItem item : resultados.getResults()) {
            item.setId(i);
            i++;
            localCacheManager.insertResultsItem(item);
        }
    }

    void onPromocaoClicked(ResultsItem resultsItem) {
        navigator.goToProdutos(resultsItem);
    }
}
