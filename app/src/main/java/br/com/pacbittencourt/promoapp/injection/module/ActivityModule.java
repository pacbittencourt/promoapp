package br.com.pacbittencourt.promoapp.injection.module;

import android.content.Context;

import br.com.pacbittencourt.promoapp.injection.ActivityContext;
import dagger.Module;
import dagger.Provides;

@Module
public final class ActivityModule {

    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return context;
    }
}
