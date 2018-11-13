package ir.taghizadeh.rxjava2_operators.ui.activity.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.R;

public class SearchViewActivity extends AppCompatActivity {

    /*

        This sample helps you to create an intelligent searchView which gives you a couple of moments
        to complete your words, instead of taking action on every single character.

    */
    Unbinder unbinder;

    @BindView(R.id.button_samples)
    Button button_samples;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.text_searchView)
    TextView text_searchView;

    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_view);
        unbinder = ButterKnife.bind(this);
        button_samples.setVisibility(View.GONE);
    }

    private Disposable getDisposable() {
        return RxSearchView.queryTextChanges(searchView)
                .debounce(600, TimeUnit.MILLISECONDS)
                .filter(changes -> {
                    assert changes != null;
                    return true;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(character -> text_searchView.setText(String.format("%s%s", getString(R.string.search_for), character)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        disposable.dispose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        disposable = getDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
