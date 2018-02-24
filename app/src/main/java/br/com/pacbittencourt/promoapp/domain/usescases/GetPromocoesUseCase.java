package br.com.pacbittencourt.promoapp.domain.usescases;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.pacbittencourt.promoapp.domain.model.Resultados;
import br.com.pacbittencourt.promoapp.domain.repositories.PromocoesRepository;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule.POST_EXECUTION_THREAD;
import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule.THREAD;

public final class GetPromocoesUseCase
        extends UseCase<Resultados, PromocoesRepository> {

    @Inject
    GetPromocoesUseCase(PromocoesRepository repository,
                        @Named(THREAD) Scheduler threadExecutor,
                        @Named(POST_EXECUTION_THREAD) Scheduler postThreadExecutor) {
        super(repository, threadExecutor, postThreadExecutor);
    }

    @Override
    protected Observable<Resultados> buildObservable(Void aVoid) {
        return repository.getPromocoes();
    }

}
