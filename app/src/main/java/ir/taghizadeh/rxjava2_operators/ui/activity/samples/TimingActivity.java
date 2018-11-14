package ir.taghizadeh.rxjava2_operators.ui.activity.samples;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.R;

public class TimingActivity extends AppCompatActivity {

    /*

    This sample is consist of some super simple ways of timing between operations

*/

    Unbinder unbinder;
    @BindView(R.id.image_timing_1)
    ImageView image_timing_1;
    @BindView(R.id.image_timing_2)
    ImageView image_timing_2;
    @BindView(R.id.image_timing_2_orange)
    ImageView image_timing_2_orange;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        unbinder = ButterKnife.bind(this);
    }

    @SuppressLint("WrongConstant")
    private void blink(View view) {
        view.setVisibility(View.VISIBLE);
        ObjectAnimator objAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        objAnimator.setDuration(1000);
        objAnimator.setRepeatMode(Animation.REVERSE);
        objAnimator.setRepeatCount(1);
        objAnimator.start();
    }

    @OnClick(R.id.button_timing1)
    void blink_5times_Interval2s() {
        dispose();
        Observable
                .interval(2, TimeUnit.SECONDS)
                .take(5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        blink(image_timing_1);
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

    @OnClick(R.id.button_timing2)
    void blink_bothViews() {
        dispose();
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(input -> blink(image_timing_2))
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(input -> blink(image_timing_2_orange))
                .doOnSubscribe(disposable -> getCompositeDisposable().add(disposable))
                .subscribe();
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    private void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dispose();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
