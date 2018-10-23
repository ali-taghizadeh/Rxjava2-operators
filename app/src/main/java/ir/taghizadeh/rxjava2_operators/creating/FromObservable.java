package ir.taghizadeh.rxjava2_operators.creating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>From</h1>
 * Constructs a sequence from a pre-existing source or generator type.
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/just.html">Reactivex</a>
 */

public class FromObservable {

    public void fromIterable() {
        /*

        This operator is another way to create an Observable.
        It works like .just() but it is specifically designed to work with Lists, Sets, Collections or a custom Iterable.
        When just is given a collection, it converts it into an Observable that emits each item from the list.
        But by means of this operator we can pass a list of items to the Observable and each item is emitted one at a time.

        */

        List<Integer> list = new ArrayList<>(Arrays.asList(1,1,2,3,6,5,4,4,4));
        Observable.fromIterable(list)
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
