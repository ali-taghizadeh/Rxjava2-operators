package ir.taghizadeh.rxjava2_operators.transforming;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * <h1>Map</h1>
 * Transform the items emitted by an Observable by applying a function to each item
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/map.html">Reactivex</a>
 */

public class MapObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable mapObservable() {
        /*

        The Map operator applies a function of your choosing to each item emitted by the source Observable,
        and returns an Observable that emits the results of these function applications.

        */

        Observable.just(1,2,3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "new data : " + integer * 2;
                    }
                })
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
