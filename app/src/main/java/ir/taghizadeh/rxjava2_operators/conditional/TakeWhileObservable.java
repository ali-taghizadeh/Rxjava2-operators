package ir.taghizadeh.rxjava2_operators.conditional;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

/**
 * <h1>TakeWhile</h1>
 * Mirror items emitted by an Observable until a specified condition becomes false
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/takewhile.html">Reactivex</a>
 */

public class TakeWhileObservable implements Operators {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        The TakeWhile mirrors the source Observable until such time as some condition you specify becomes false,
        at which point TakeWhile stops mirroring the source Observable and terminates its own Observable.

        */

        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .takeWhile(integer -> integer != 5)
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
