package ir.taghizadeh.rxjava2_operators.combining;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Concat</h1>
 * Emit the emissions from two or more Observables without interleaving them
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/concat.html">Reactivex</a>
 */

public class ConcatObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        The Concat operator concatenates the output of multiple Observables so that they act like a single Observable,
        with all of the items emitted by the first Observable being emitted before any of the items emitted by the second Observable
        (and so forth, if there are more than two).

        */

        Observable<String> stringObservable = Observable.just("1", "2", "3", "4", "5");
        Observable<String> stringObservable1 = Observable.just("Item1", "Item2", "Item3", "Item4", "Item5", "Item6");
        Observable.concat(stringObservable1, stringObservable)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
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

            return compositeDisposable;
    }

    private  CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

}
