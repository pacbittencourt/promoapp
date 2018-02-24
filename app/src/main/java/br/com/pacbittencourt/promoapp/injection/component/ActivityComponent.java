package br.com.pacbittencourt.promoapp.injection.component;

import android.content.Context;

import br.com.pacbittencourt.promoapp.injection.ActivityContext;
import br.com.pacbittencourt.promoapp.injection.ActivityScope;
import br.com.pacbittencourt.promoapp.injection.module.ActivityModule;
import br.com.pacbittencourt.promoapp.injection.module.ApiModule;
import br.com.pacbittencourt.promoapp.injection.module.RepositoryModule;
import br.com.pacbittencourt.promoapp.ui.main.MainActivity;
import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, ApiModule.class, RepositoryModule.class})
public interface ActivityComponent {

    @ActivityContext
    Context context();

    void inject(MainActivity mainActivity);
}