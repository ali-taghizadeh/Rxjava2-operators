package ir.taghizadeh.rxjava2_operators.combining;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Merge</h1>
 * Combine multiple Observables into one by merging their emissions
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/merge.html">Reactivex</a>
 */

public class MergeObservable {

    public void mergeObservable() {
        /*

        You can combine the output of multiple Observables so that they act like a single Observable,
        by using the Merge operator but it won’t maintain the sequential execution.


        */

        Observable<String> stringObservable = Observable.just("1", "2", "3", "4", "5");
        Observable<String> stringObservable1 = Observable.just("Item1", "Item2", "Item3", "Item4", "Item5", "Item6");
        Observable.merge(stringObservable1, stringObservable)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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

    }

}
