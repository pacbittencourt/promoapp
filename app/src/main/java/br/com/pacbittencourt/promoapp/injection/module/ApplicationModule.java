package br.com.pacbittencourt.promoapp.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;

import br.com.pacbittencourt.promoapp.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public final class ApplicationModule {

    public static final String THREAD = "Thread";
    public static final String POST_EXECUTION_THREAD = "PostExecution";

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

    @Provides
    @Named(THREAD)
    Scheduler provideExecutionThread() {
        return Schedulers.io();
    }

    @Provides
    @Named(POST_EXECUTION_THREAD)
    Scheduler providePostExecutionThread() {
        return AndroidSchedulers.mainThread();
    }
}
