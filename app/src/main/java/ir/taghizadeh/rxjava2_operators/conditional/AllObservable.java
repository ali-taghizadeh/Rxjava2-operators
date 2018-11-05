package ir.taghizadeh.rxjava2_operators.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>All</h1>
 * Determine whether all items emitted by an Observable meet some criteria
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/all.html">Reactivex</a>
 */

public class AllObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        Pass a predicate function to the All operator that accepts an item emitted by the source Observable and
        returns a boolean value based on an evaluation of that item.
        All returns an Observable that emits a single boolean value:
        true if and only if the source Observable terminates normally and every item emitted by the source Observable evaluated as true according to this predicate;
        false if any item emitted by the source Observable evaluates as false according to this predicate.

        */

        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,2,3,4,5,10));
        Observable.fromIterable(integerList)
                .all(ints -> ints < 10)
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
