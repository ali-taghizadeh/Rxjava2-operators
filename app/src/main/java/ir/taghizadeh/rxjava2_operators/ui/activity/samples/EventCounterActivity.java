package ir.taghizadeh.rxjava2_operators.ui.activity.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import ir.taghizadeh.rxjava2_operators.R;

public class EventCounterActivity extends AppCompatActivity {

/*

    With the combination of map and buffer operators we count the number of clicks on a button
    within 2 seconds

*/
    Unbinder unbinder;

    @BindView(R.id.button_samples)
    Button button_samples;
    @BindView(R.id.button_event_counter)
    Button button_event_counter;
    @BindView(R.id.text_event_counter)
    TextView text_event_counter;
    Animation animation;
    private Disposable disposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_counter);
        unbinder = ButterKnife.bind(this);
        button_samples.setVisibility(View.GONE);
    }

    private Disposable getDisposable() {
        return RxView.clicks(button_event_counter)
                .map(onClickEvent -> 1)
                .buffer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Integer>>() {
                    @Override
                    public void onNext(List<Integer> integers) {
                        if (integers.size() > 0)
                            setAnimation(integers.size());
                        else text_event_counter.setText("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("DONE!");
                    }
                });
    }

    private void setAnimation(int size) {
        animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        text_event_counter.setText(String.format(getString(R.string.taps), size));
        text_event_counter.setAnimation(animation);
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
