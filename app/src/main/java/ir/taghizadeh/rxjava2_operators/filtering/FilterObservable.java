package ir.taghizadeh.rxjava2_operators.filtering;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * <h1>Filter</h1>
 * Emit only those items from an Observable that pass a predicate test
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/filter.html">Reactivex</a>
 */

public class FilterObservable {

    private Disposable disposable;

    public void filterObservable() {
        /*

        The Filter operator filters an Observable by only allowing items through that pass a test that you specify in the form of a predicate function.

        */

        Observable.range(1, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 5 == 0;
                    }
                })
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
