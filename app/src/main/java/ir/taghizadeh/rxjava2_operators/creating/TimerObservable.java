package ir.taghizadeh.rxjava2_operators.creating;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Timer</h1>
 * Create an Observable that emits a particular item after a given delay
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/timer.html">Reactivex</a>
 */

public class TimerObservable {

    private Disposable disposable;

    public void timerObservable() {
        /*

        The Timer operator creates an Observable that emits one particular item after a span of time that you specify (In this case 5 seconds).
        timer() operator emits JUST a single item after a delay
        whereas interval() operator, on the other hand, will emit items spaced out with a given interval.

        */

        Observable
                .timer(5, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println(aLong);
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

    public void dispose(){
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
