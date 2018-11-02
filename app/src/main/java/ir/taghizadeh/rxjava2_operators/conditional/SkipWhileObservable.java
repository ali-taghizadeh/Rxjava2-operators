package ir.taghizadeh.rxjava2_operators.conditional;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>SkipWhile</h1>
 * Discard items emitted by an Observable until a specified condition becomes false
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/skipwhile.html">Reactivex</a>
 */

public class SkipWhileObservable {

    public void skipWhileObservable() {
        /*

        The SkipWhile subscribes to the source Observable,
        but ignores its emissions until such time as some condition you specify becomes false,
        at which point SkipWhile begins to mirror the source Observable.

        */

        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .skipWhile(integer -> integer != 5)
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
