package ir.taghizadeh.rxjava2_operators.conditional;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>DefaultIfEmpty</h1>
 * Emit items from the source Observable, or a default item if the source Observable emits nothing
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/defaultifempty.html">Reactivex</a>
 */

public class DefaultIfEmptyObservable {

    public void defaultIfEmptyObservable() {
        /*

        The DefaultIfEmpty operator simply mirrors the source Observable exactly if the source Observable emits any items.
        If the source Observable terminates normally (with an onComplete) without emitting any items,
        the Observable returned from DefaultIfEmpty will instead emit a default item of your choosing before it too completes.

        */

        Observable.just(1, 3,5,7,9)
                .filter(integer -> integer % 2 == 0)
                .defaultIfEmpty(Integer.MAX_VALUE)
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
