package br.com.pacbittencourt.promoapp.data.services;

import java.util.List;

import br.com.pacbittencourt.promoapp.domain.model.ResultsItem;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PromocoesService {

    @GET("web?empresa=20")
    Observable<List<ResultsItem>> getPromocoes();
}
