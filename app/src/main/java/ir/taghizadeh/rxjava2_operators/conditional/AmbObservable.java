package ir.taghizadeh.rxjava2_operators.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

/**
 * <h1>Amb</h1>
 * Given two or more source Observables, emit all of the items from only the first of these Observables to emit an item or notification
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/amb.html">Reactivex</a>
 */

public class AmbObservable implements Operators {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        When you pass a number of source Observables to Amb, it will pass through the emissions and notifications of exactly one of these Observables:
        the first one that sends a notification to Amb, either by emitting an item or sending an onError or onCompleted notification.
        Amb will ignore and discard the emissions and notifications of all of the other source Observables.

        */

        Observable<Long> longObservable = Observable.interval(2, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return aLong * 10;
                    }
                });
        Observable<Long> longObservable1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return aLong * 5;
                    }
                });
        Observable.ambArray(longObservable, longObservable1)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
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
        return compositeDisposable;
    }

    private  CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
