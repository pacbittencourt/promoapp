package br.com.pacbittencourt.promoapp.injection.module;

import android.app.Application;
import android.content.Context;

import br.com.pacbittencourt.promoapp.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }
}
