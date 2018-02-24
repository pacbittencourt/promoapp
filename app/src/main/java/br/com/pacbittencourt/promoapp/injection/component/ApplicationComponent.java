package br.com.pacbittencourt.promoapp.injection.component;


import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.pacbittencourt.promoapp.injection.ApplicationContext;
import br.com.pacbittencourt.promoapp.injection.module.ApplicationModule;
import dagger.Component;
import io.reactivex.Scheduler;

import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule
        .POST_EXECUTION_THREAD;
import static br.com.pacbittencourt.promoapp.injection.module.ApplicationModule.THREAD;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    @Named(THREAD)
    Scheduler scheduler();

    @Named(POST_EXECUTION_THREAD)
    Scheduler postScheduler();
}
