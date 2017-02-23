package ${featurePackageName}.di;

import com.younglive.livestreaming.app.di.ApplicationComponent;
import com.younglive.common.di.PerActivity;
import ${featurePackageName}.${activityClass};
import ${featurePackageName}.${fragmentName};
import ${featurePackageName}.mvp.${featureName}Presenter;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ${featureName}Module.class,
        })
public interface ${featureName}Component {
    void inject(${activityClass} activity);
    void inject(${fragmentName} fragment);

    ${featureName}Presenter presenter();
}
