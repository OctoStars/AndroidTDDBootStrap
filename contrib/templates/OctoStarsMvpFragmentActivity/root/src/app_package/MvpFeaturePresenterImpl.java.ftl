package ${featurePackageName};

import com.younglive.common.di.PerActivity;
import ${baseMvpPresenterClassFqcn};
import ${featurePackageName}.mvp.${featureName}Presenter;
import ${featurePackageName}.mvp.${featureName}View;
import org.greenrobot.eventbus.EventBus;
import android.support.annotation.NonNull;
import javax.inject.Inject;

@PerActivity
public class ${featureName}PresenterImpl extends ${baseMvpPresenterClass}<${featureName}View>
        implements ${featureName}Presenter {

    private final EventBus mBus;

    @Inject
    ${featureName}PresenterImpl(EventBus bus) {
        mBus = bus;
    }

    @NonNull
    @Override
    protected EventBus getBus() {
        return mBus;
    }
}
