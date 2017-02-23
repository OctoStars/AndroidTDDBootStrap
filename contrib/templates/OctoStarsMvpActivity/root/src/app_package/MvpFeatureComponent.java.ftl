package ${featurePackageName};

import com.github.piasy.octostars.di.AppComponent;
import com.github.piasy.yamvp.dagger2.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class)
interface ${featureName}Component {

    void inject(${featureName}Activity activity);
}
