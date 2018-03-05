package br.com.pacbittencourt.promoapp.data.services;

import br.com.pacbittencourt.promoapp.domain.model.Resultados;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PromocoesService {

    @GET("web?empresa=20")
    Observable<Resultados> getPromocoes();
}
