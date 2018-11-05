package ir.taghizadeh.rxjava2_operators.conditional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Contains</h1>
 * Determine whether an Observable emits a particular item or not
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/contains.html">Reactivex</a>
 */

public class ContainsObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        Pass the Contains operator a particular item, and the Observable it returns will emit true if that item is emitted by the source Observable,
        or false if the source Observable terminates without emitting that item.

        */

        List<Integer> integerList = new ArrayList<>(Arrays.asList(1,2,3,4,5,10));
        Observable.fromIterable(integerList)
                .contains(10)
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
