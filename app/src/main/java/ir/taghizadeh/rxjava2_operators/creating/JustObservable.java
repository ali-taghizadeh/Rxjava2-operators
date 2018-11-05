package ir.taghizadeh.rxjava2_operators.creating;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

/**
 * <h1>Just</h1>
 * The Just operator converts an item into an Observable that emits that item.
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/just.html">Reactivex</a>
 */

public class JustObservable implements Operators {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        Here we convert a single string value into an observable that emits that string.

        */
        Observable.just("Pass a single string")
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

    public void justObservable_method2() {
        /*

        Here we convert 4 values into an observable that emits those values.
        Also  remember that just operator cannot take more than 10 arguments.

        */
        Observable.just("Item", "1", "88.9", "Item2")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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
    }

    public void justObservable_method3() {
        /*

        In this method we convert a list of strings into an observable.
        Note that just operator will give you only 1 emission. This time a list.
        In fact, just operator treats everything as an item regardless it is an array item or a string item.

        */
        Observable.just(new String[]{"Item1","Item2","Item3","Item4","Item5","Item6","Item7","Item8","Item9","Item10"})
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String[] strings) {
                        System.out.println(strings.length);
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

    private  CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
