package br.com.pacbittencourt.promoapp.data.remote;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.data.services.PromocoesService;
import br.com.pacbittencourt.promoapp.domain.model.Resultados;
import io.reactivex.Observable;

public final class PromocoesDataSourceRemote {

    private final PromocoesService promocoesService;

    @Inject
    public PromocoesDataSourceRemote(PromocoesService promocoesService) {
        this.promocoesService = promocoesService;
    }

    public Observable<Resultados> getPromocoes() {
        return promocoesService.getPromocoes();
    }
}
