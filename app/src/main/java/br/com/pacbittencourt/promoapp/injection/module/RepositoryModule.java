package br.com.pacbittencourt.promoapp.injection.module;

import br.com.pacbittencourt.promoapp.data.repositories.PromocoesRepositoryImpl;
import br.com.pacbittencourt.promoapp.domain.repositories.PromocoesRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    PromocoesRepository providePromocoesRepository(PromocoesRepositoryImpl promocoesRepository) {
        return promocoesRepository;
    }
}
