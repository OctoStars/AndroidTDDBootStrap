package ${featurePackageName}.di;

import com.younglive.common.di.PerActivity;
import ${featurePackageName}.${featureName}PresenterImpl;
import ${featurePackageName}.mvp.${featureName}Presenter;

import dagger.Module;
import dagger.Provides;

@Module
class ${featureName}Module {
    @PerActivity
    @Provides
    ${featureName}Presenter provide${featureName}Presenter(${featureName}PresenterImpl presenter) {
        return presenter;
    }
}
