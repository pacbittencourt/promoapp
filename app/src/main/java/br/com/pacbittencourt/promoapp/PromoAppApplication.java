package br.com.pacbittencourt.promoapp;

import android.app.Application;

import br.com.pacbittencourt.promoapp.injection.component.ApplicationComponent;
import br.com.pacbittencourt.promoapp.injection.component.DaggerApplicationComponent;
import br.com.pacbittencourt.promoapp.injection.module.ApplicationModule;

public final class PromoAppApplication
        extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
