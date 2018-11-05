package ir.taghizadeh.rxjava2_operators.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

/**
 * <h1>CombineLatestObservable</h1>
 * When an item is emitted by either of two Observables,
 * combine the latest item emitted by each Observable via a specified function
 * and emit items based on the results of this function
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/combinelatest.html">Reactivex</a>
 */

public class CombineLatestObservable implements Operators {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        The CombineLatest operator behaves in a similar way to Zip,
        but while Zip emits items only when each of the zipped source Observables have emitted a previously unzipped item,
        CombineLatest emits an item whenever any of the source Observables emits an item
        (as long as each of the source Observables has emitted at least one item)

        */

        Observable<Long> longObservable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> longObservable2 = Observable.interval(2, TimeUnit.SECONDS);
        Observable.combineLatest(longObservable1, longObservable2, (aLong, aLong2) ->
                "Observable1: " + aLong + " Observable2: " + aLong2)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
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
