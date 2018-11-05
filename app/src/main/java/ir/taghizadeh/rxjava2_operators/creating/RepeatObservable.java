package ir.taghizadeh.rxjava2_operators.creating;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Repeat</h1>
 * Create an Observable that emits a particular item multiple times
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/repeat.html">Reactivex</a>
 */

public class RepeatObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        Here we use the just() operator which we used before.

        */

        Observable.just("Pass a single string 3 times")
                .repeat(3)
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
