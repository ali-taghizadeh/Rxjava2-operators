package ir.taghizadeh.rxjava2_operators.combining;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * <h1>Zip</h1>
 * Combine the emissions of multiple Observables together
 * via a specified function and emit single items for each combination based on the results of this function
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/zip.html">Reactivex</a>
 */

public class ZipObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable zipObservable() {
        /*

        It applies this function in strict sequence,
        so the first item emitted by the new Observable will be the result of the function applied to the first item emitted by Observable #1
        and the first item emitted by Observable #2;
        The second item emitted by the new zip-Observable will be the result of the function applied to the second item emitted by Observable #1
        and the second item emitted by Observable #2; and so forth.
        It will only emit as many items as the number of items emitted by the source Observable that emits the fewest items.

        */

        Observable<Integer> integerObservable = Observable.just(1, 2 , 3 , 4 ,5 );
        Observable<String> stringObservable = Observable.just("Item1", "Item2","Item3", "Item4","Item5", "Item6");
        Observable.zip(integerObservable, stringObservable, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return s + " : " +  integer;
            }
        }).subscribe(new Observer<String>() {
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
