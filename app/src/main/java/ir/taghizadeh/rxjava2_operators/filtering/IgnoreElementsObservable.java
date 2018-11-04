package ir.taghizadeh.rxjava2_operators.filtering;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>IgnoreElements</h1>
 * Do not emit any items from an Observable but mirror its termination notification
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/ignoreelements.html">Reactivex</a>
 */

public class IgnoreElementsObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable ignoreElementsObservable() {
        /*

        If you do not care about the items being emitted by an Observable,
        but you do want to be notified when it completes or when it terminates with an error,
        you can apply the ignoreElements operator to the Observable,
        which will ensure that it will never call its observers’ onNext handlers.

        */

        Observable.range(1, 10)
                .ignoreElements()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
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
