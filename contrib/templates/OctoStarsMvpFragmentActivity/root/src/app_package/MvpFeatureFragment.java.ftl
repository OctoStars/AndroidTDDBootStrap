package ${featurePackageName};

import ${baseMvpFragmentClassFqcn};
import ${appPackageName}.R;
import ${featurePackageName}.di.${featureName}Component;
import ${featurePackageName}.mvp.${featureName}Presenter;
import ${featurePackageName}.mvp.${featureName}View;
import org.greenrobot.eventbus.EventBus;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import javax.inject.Inject;
import android.view.View;

public class ${fragmentName} extends ${baseMvpFragmentClass}<${featureName}View, ${featureName}Presenter>
        implements ${featureName}View {

    @Inject
    EventBus mBus;
    @Inject
    Resources mResources;

    /**
     * called in super.onViewCreated()
     */
    @Override
    protected void injectDependencies() {
        ${featureName}Component component = this.getComponent(${featureName}Component.class);
        component.inject(this);
        presenter = component.presenter();
    }

    @Override
    protected void bindViews(View view) {
        super.bindViews(view);
    }

    @NonNull
    @Override
    protected EventBus getBus() {
        return mBus;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.${fragmentLayoutName};
    }
}
