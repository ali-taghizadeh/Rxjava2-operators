package ir.taghizadeh.rxjava2_operators.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>SequenceEqual</h1>
 * Determine whether two Observables emit the same sequence of items
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/sequenceequal.html">Reactivex</a>
 */

public class SequenceEqualObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        Pass SequenceEqual two Observables, and it will compare the items emitted by each Observable,
        and the Observable it returns will emit true only if both sequences are the same
        (the same items, in the same order, with the same termination state).

        */

        Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS)
                .map(aLong -> aLong * 2);
        Observable<Long> longObservable1 = Observable.interval(2, TimeUnit.SECONDS)
                .map(aLong -> aLong);
        Observable.sequenceEqual(longObservable, longObservable1)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
