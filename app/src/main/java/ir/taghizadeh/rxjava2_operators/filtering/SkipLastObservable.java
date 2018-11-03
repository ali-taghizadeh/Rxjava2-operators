package ir.taghizadeh.rxjava2_operators.filtering;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>SkipLast</h1>
 * Suppress the final n items emitted by an Observable
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/skiplast.html">Reactivex</a>
 */

public class SkipLastObservable {

    private Disposable disposable;

    public void skipLastObservable() {
        /*

        You can ignore the first n items emitted by an Observable and attend only to those items that come after,
        by modifying the Observable with the Skip operator.

        */

        Observable.range(1,10)
                .skipLast(5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
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

    public void dispose(){
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
