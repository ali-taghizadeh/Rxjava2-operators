package ir.taghizadeh.rxjava2_operators.creating;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Interval</h1>
 * Create an Observable that emits a sequence of integers spaced by a given time interval
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/interval.html">Reactivex</a>
 */

public class IntervalObservable {

    private Disposable disposable;

    public void intervalObservable() {
        /*

        Periodically generates an infinite, ever increasing numbers (of type Long).

        */

        Observable.interval(1, TimeUnit.SECONDS)
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
