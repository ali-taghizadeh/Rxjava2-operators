package ir.taghizadeh.rxjava2_operators.creating;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Range</h1>
 * create an Observable that emits a particular range of sequential integers
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/range.html">Reactivex</a>
 */

public class RangeObservable {

    private Disposable disposable;

    public void rangeObservable() {
        /*

        The function takes the starting number and length.
        Here we have a starting number of 25 and a range of 10 numbers, so it will print values from 25 to 34.

        */

        Observable.range(25, 10)
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
