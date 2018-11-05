package ir.taghizadeh.rxjava2_operators.creating;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Defer</h1>
 * just(), from(), and other Observable creation tools store the value of data when created, not when subscribed
 * But by using defer() operator, it does not create the Observable until the observer subscribes, and create a fresh Observable for each observer
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/just.html">Reactivex</a>
 */

public class DeferObservable {

    private CompositeDisposable compositeDisposable;
    private String value = "firstValue";

    private Observable<String> valueObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just(value);
            }
        });
    }

    public CompositeDisposable runOperator() {

        value = "secondValue";
        Observable<String> stringObservable = valueObservable();

        stringObservable.subscribe(new Observer<String>() {
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
