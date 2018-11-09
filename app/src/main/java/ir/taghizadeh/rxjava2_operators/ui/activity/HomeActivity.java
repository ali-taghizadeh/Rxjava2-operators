package ir.taghizadeh.rxjava2_operators.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.taghizadeh.rxjava2_operators.R;
import ir.taghizadeh.rxjava2_operators.ui.fragments.OperatorsFragment;

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
        OperatorsFragment operatorsFragment = new OperatorsFragment();
        final Fragment[] fragment = {operatorsFragment.newInstance(getString(R.string.observable_creating)),
                operatorsFragment.newInstance(getString(R.string.observable_transforming)),
                operatorsFragment.newInstance(getString(R.string.observable_filtering)),
                operatorsFragment.newInstance(getString(R.string.observable_combining)),
                operatorsFragment.newInstance(getString(R.string.observable_conditional))};

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frame, fragment[0])
                .commit();

        bottom_navigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.observable_creating:
                    manager.beginTransaction()
                            .replace(R.id.frame, fragment[0])
                            .commit();
                    break;
                case R.id.observable_transforming:
                    manager.beginTransaction()
                            .replace(R.id.frame, fragment[1])
                            .commit();
                    break;
                case R.id.observable_filtering:
                    manager.beginTransaction()
                            .replace(R.id.frame, fragment[2])
                            .commit();
                    break;
                case R.id.observable_combining:
                    manager.beginTransaction()
                            .replace(R.id.frame, fragment[3])
                            .commit();
                    break;
                case R.id.observable_conditional:
                    manager.beginTransaction()
                            .replace(R.id.frame, fragment[4])
                            .commit();
                    break;
            }
            return true;
        });
    }
}
