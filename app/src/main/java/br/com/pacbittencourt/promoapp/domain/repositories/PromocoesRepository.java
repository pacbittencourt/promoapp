package br.com.pacbittencourt.promoapp.domain.repositories;

import java.util.List;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import io.reactivex.Observable;

public interface PromocoesRepository
        extends Repository {

    Observable<List<ResultsItem>> getPromocoes();
}
