package ir.taghizadeh.rxjava2_operators.transforming;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * <h1>Scan</h1>
 * Apply a function to each item emitted by an Observable, sequentially, and emit each successive value
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/scan.html">Reactivex</a>
 */

public class ScanObservable {

    public void groupByObservable() {
        /*

        This operator Transforms each item into another item, like you did with map.
       But it also includes the “previous” item when you get around to doing a transform

        */

        Observable.range(1, 10)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

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
}
