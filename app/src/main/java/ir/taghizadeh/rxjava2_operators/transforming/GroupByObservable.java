package ir.taghizadeh.rxjava2_operators.transforming;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import ir.taghizadeh.rxjava2_operators.utils.Operators;

/**
 * <h1>GroupBy</h1>
 * Divide an Observable into a set of Observables that each emit a different subset of items from the original Observable
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/groupby.html">Reactivex</a>
 */

public class GroupByObservable implements Operators {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable runOperator() {
        /*

        Here is an animation demonstrating this operator very well :
        http://blogs.microsoft.co.il/iblogger/2015/08/11/animations-of-rx-operators-groupby

        */

        Observable.just(2,3,8,9,14,12)
                .groupBy(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) throws Exception {
                        return integer % 3 == 0;
                    }
                })
                .subscribe(new Observer<GroupedObservable<Boolean, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                        if (booleanIntegerGroupedObservable.getKey()){
                            booleanIntegerGroupedObservable.subscribe(new Observer<Integer>() {
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
