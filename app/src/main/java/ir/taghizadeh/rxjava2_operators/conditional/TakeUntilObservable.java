package ir.taghizadeh.rxjava2_operators.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

/**
 * <h1>TakeUntil</h1>
 * Discard any items emitted by an Observable after a second Observable emits an item or terminates
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/takeuntil.html">Reactivex</a>
 */

public class TakeUntilObservable implements Operators {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        The TakeUntil subscribes and begins mirroring the source Observable.
        It also monitors a second Observable that you provide.
        If this second Observable emits an item or sends a termination notification,
        the Observable returned by TakeUntil stops mirroring the source Observable and terminates.

        */

        Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS)
                .map(aLong -> aLong * 10);
        Observable<Long> longObservable1 = Observable.interval(5, TimeUnit.SECONDS)
                .map(aLong -> aLong * 5);
        longObservable.takeUntil(longObservable1)
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
