package ir.taghizadeh.rxjava2_operators.combining;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Join</h1>
 * Combine items emitted by two Observables whenever an item from one Observable is emitted during a time window
 * defined according to an item emitted by the other Observable
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/join.html">Reactivex</a>
 */

public class JoinObservable {

    public void joinObservable() {
        /*

        The Join operator combines the items emitted by two Observables,
        and selects which items to combine based on duration-windows that you define on a per-item basis.
        You implement these windows as Observables whose lifespans begin with each item emitted by either Observable.
        When such a window-defining Observable either emits an item or completes, the window for the item it is associated with closes.
        So long as an item’s window is open, it will combine with any item emitted by the other Observable. You define the function by which the items combine.


        */
        Observable<Long> left = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> right = Observable.interval(2, TimeUnit.SECONDS);

        left.join(right,
                        aLong -> Observable.timer(1, TimeUnit.SECONDS),
                        aLong -> Observable.timer(2, TimeUnit.SECONDS),
                        (aLong, aLong2) -> {
                            System.out.println("Left result: " + aLong + " Right Result: " + aLong2);
                            return Arrays.asList(aLong.intValue(), aLong2.intValue());
                        })
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        for (Integer i : integers){
                            System.out.println(i);
                        }
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
