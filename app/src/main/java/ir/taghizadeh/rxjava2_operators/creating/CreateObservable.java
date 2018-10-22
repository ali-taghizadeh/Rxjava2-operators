package ir.taghizadeh.rxjava2_operators.creating;

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
 *
 */

public class CreateObservable {

    public void CreateObservable_method1() {
        /*

        The first way to create an observable.
        Here we create both observable and observer separately and eventually we connect them together by subscribe operator.

        */
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("First Rx experience");
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
}
