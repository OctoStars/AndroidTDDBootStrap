package ${featurePackageName};

import com.younglive.common.di.HasComponent;
import ${baseMvpActivityClassFqcn};
import ${featurePackageName}.di.Dagger${featureName}Component;
import ${featurePackageName}.di.${featureName}Component;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

public class ${activityClass} extends ${baseMvpActivityClass}
        implements HasComponent<${featureName}Component> {

    private ${featureName}Component m${featureName}Component;

    @Inject
    EventBus mBus;

    @Nullable
    @Override
    protected EventBus getBus() {
        return mBus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

<#if useFragment>
        if (savedInstanceState == null) {
            safeCommit(getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new ${fragmentName}(), "${fragmentName}"));
        }
<#else>
        setContentView(R.layout.${layoutName});
</#if>
    }

    @Override
    protected void initializeInjector() {
        m${featureName}Component = Dagger${featureName}Component.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        m${featureName}Component.inject(this);
    }

    @Override
    public ${featureName}Component getComponent() {
        return m${featureName}Component;
    }
}
