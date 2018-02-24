package br.com.pacbittencourt.promoapp.data.remote;

import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.data.services.PromocoesService;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import io.reactivex.Observable;

public final class PromocoesDataSourceRemote {

    private final PromocoesService promocoesService;

    @Inject
    public PromocoesDataSourceRemote(PromocoesService promocoesService) {
        this.promocoesService = promocoesService;
    }

    public Observable<List<ResultsItem>> getPromocoes() {
        return promocoesService.getPromocoes();
    }
}
