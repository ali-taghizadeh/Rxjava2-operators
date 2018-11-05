package ir.taghizadeh.rxjava2_operators.filtering;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

/**
 * <h1>Skip</h1>
 * Suppress the first n items emitted by an Observable
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/skip.html">Reactivex</a>
 */

public class SkipObservable implements Operators {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        You can ignore the first n items emitted by an Observable and attend only to those items that come after,
        by modifying the Observable with the Skip operator.

        */

        Observable.range(1,10)
                .skip(5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
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
        return compositeDisposable;
    }

    private  CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
