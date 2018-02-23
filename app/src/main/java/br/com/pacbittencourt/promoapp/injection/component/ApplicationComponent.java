package br.com.pacbittencourt.promoapp.injection.component;


import android.content.Context;

import javax.inject.Singleton;

import br.com.pacbittencourt.promoapp.injection.ApplicationContext;
import br.com.pacbittencourt.promoapp.injection.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
}
