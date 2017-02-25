package ${featurePackageName};

import android.os.Bundle;
import com.github.piasy.octostars.RouteTable;
import butterknife.ButterKnife;
import com.chenenyu.router.annotation.Route;
import com.github.piasy.octostars.BootstrapActivity;
import com.github.piasy.octostars.BootstrapApp;
import com.github.piasy.octostars.RouteTable;
import javax.inject.Inject;

@Route(RouteTable.${featureName?upper_case})
public class ${activityClass} extends BootstrapActivity<${featureName}Component> implements ${featureName}View {

    @Inject
    ${featureName}Presenter mPresenter;

    private ${featureName}Component m${featureName}Component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.${layoutName});
        ButterKnife.bind(this);
        mPresenter.attachView(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detachView();
        mPresenter.onDestroy();
    }

    @Override
    protected void initializeDi() {
        m${featureName}Component = Dagger${featureName}Component.builder()
                .appComponent(BootstrapApp.get().appComponent())
                .build();
        m${featureName}Component.inject(this);
    }

    @Override
    public ${featureName}Component getComponent() {
        return m${featureName}Component;
    }
}
