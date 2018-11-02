package ir.taghizadeh.rxjava2_operators.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.fragments.CombiningFragment;
import ir.taghizadeh.rxjava2_operators.ui.fragments.ConditionalFragment;
import ir.taghizadeh.rxjava2_operators.ui.fragments.CreatingFragment;
import ir.taghizadeh.rxjava2_operators.ui.fragments.FilteringFragment;
import ir.taghizadeh.rxjava2_operators.ui.fragments.TransformingFragment;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initNavigationBar();
    }

    private void initNavigationBar() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frame, new CreatingFragment()).commit();
        bottom_navigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.observable_creating:
                    manager.beginTransaction()
                            .replace(R.id.frame, new CreatingFragment()).commit();
                    break;
                case R.id.observable_transforming:
                    manager.beginTransaction()
                            .replace(R.id.frame, new TransformingFragment()).commit();
                    break;
                case R.id.observable_filtering:
                    manager.beginTransaction()
                            .replace(R.id.frame, new FilteringFragment()).commit();
                    break;
                case R.id.observable_combining:
                    manager.beginTransaction()
                            .replace(R.id.frame, new CombiningFragment()).commit();
                    break;
                case R.id.observable_conditional:
                    manager.beginTransaction()
                            .replace(R.id.frame, new ConditionalFragment()).commit();
                    break;
            }
            return true;
        });
    }

}
