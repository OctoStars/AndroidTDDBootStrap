package ${featurePackageName};

import com.github.piasy.yamvp.dagger2.ActivityScope;
import com.github.piasy.yamvp.rx.YaRxPresenter;
import javax.inject.Inject;

@ActivityScope
class ${featureName}Presenter extends YaRxPresenter<${featureName}View> {
    @Inject
    ${featureName}Presenter() {
        super();
    }
}
