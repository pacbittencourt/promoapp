package br.com.pacbittencourt.promoapp.domain.repositories;

import br.com.pacbittencourt.promoapp.domain.model.Resultados;
import io.reactivex.Observable;

public interface PromocoesRepository
        extends Repository {

    Observable<Resultados> getPromocoes();
}
