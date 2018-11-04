package ir.taghizadeh.rxjava2_operators.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>SkipUntil</h1>
 * Discard items emitted by an Observable until a second Observable emits an item
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/skipuntil.html">Reactivex</a>
 */

public class SkipUntilObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable skipUntilObservable() {
        /*

        The SkipUntil subscribes to the source Observable, but ignores its emissions until such time as a second Observable emits an item,
        at which point SkipUntil begins to mirror the source Observable.

        */

        Observable<Long> longObservable = Observable.interval(1, TimeUnit.SECONDS)
                .map(aLong -> aLong * 10);
        Observable<Long> longObservable1 = Observable.interval(2, TimeUnit.SECONDS)
                .map(aLong -> aLong * 5);
        longObservable.skipUntil(longObservable1)
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
