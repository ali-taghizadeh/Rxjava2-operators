package ir.taghizadeh.rxjava2_operators.creating;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Create</h1>
 * You can create an Observable from scratch by using the Create operator.
 * You pass this operator a function that accepts the observer as its parameter.
 * Write this function so that it behaves as an Observable — by calling the observer’s onNext, onError, and onCompleted methods appropriately.
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/create.html">Reactivex</a>
 */

public class CreateObservable {

    public void createObservable_method1() {
        /*

        The first way:
        Here we create the observable and then we define an observer separately and eventually we connect them together by subscribe operator.

        */
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("The first way");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
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
        };

        observable.subscribe(observer);
    }

    public void createObservable_method2() {
        /*

        The second way:
        Implementation seems easier in this method as everything happens at once.

        */
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("The second way");
                emitter.onComplete();
            }
        })
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

    public void createObservable_method3(final List<String> list) {
        /*

        A more complicated method:
        It's exactly what we practiced in method2. The only difference is that in this method, emitter will pass a list of strings.

        */
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                try {
                    for (String str : list) {
                        emitter.onNext(str);
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
                emitter.onComplete();
            }
        })
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
}
