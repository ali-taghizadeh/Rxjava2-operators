package ir.taghizadeh.rxjava2_operators.filtering;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>TakeLast</h1>
 * Emit only the final n items emitted by an Observable
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/takelast.html">Reactivex</a>
 */

public class TakeLastObservable {

    private Disposable disposable;

    public void takeObservable() {
        /*

        You can emit only the final n items emitted by an Observable and ignore those items that come before them,
        by modifying the Observable with the TakeLast operator.

        */

        Observable.range(1,10)
                .takeLast(5)
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
