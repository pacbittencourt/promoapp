package br.com.pacbittencourt.promoapp.data.repositories;

import java.util.List;

import javax.inject.Inject;

import br.com.pacbittencourt.promoapp.data.remote.PromocoesDataSourceRemote;
import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import br.com.pacbittencourt.promoapp.domain.repositories.PromocoesRepository;
import io.reactivex.Observable;

public class PromocoesRepositoryImpl implements PromocoesRepository {

    private final PromocoesDataSourceRemote promocoesDataSourceRemote;

    @Inject
    public PromocoesRepositoryImpl(PromocoesDataSourceRemote promocoesDataSourceRemote) {
        this.promocoesDataSourceRemote = promocoesDataSourceRemote;
    }

    @Override
    public Observable<List<ResultsItem>> getPromocoes() {
        return promocoesDataSourceRemote.getPromocoes();
    }
}
