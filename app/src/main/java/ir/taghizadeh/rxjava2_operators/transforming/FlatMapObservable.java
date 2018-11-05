package ir.taghizadeh.rxjava2_operators.transforming;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <h1>FlatMap</h1>
 * Transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/flatmap.html">Reactivex</a>
 */

public class FlatMapObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        Observable.flatMap() takes the emissions of one Observable and returns the emissions of another Observable to take its place.
        Note that FlatMap merges the emissions of these Observables, so that they may interleave.

        */

        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Observable
                .fromIterable(integers)
                .flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(final Integer integer) {
                        return Observable.just(integer * 2)
                                .delay(1, TimeUnit.SECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
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
