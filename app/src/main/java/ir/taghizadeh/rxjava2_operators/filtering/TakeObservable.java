package ir.taghizadeh.rxjava2_operators.filtering;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Take</h1>
 * Emit only the first n items emitted by an Observable
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/take.html">Reactivex</a>
 */

public class TakeObservable {

    public void takeObservable() {
        /*

        You can emit only the first n items emitted by an Observable and then complete while ignoring the remainder,
        by modifying the Observable with the Take operator.

        */

        Observable.range(1,10)
                .take(5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
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
