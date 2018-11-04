package ir.taghizadeh.rxjava2_operators.transforming;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <h1>Buffer</h1>
 * Buffer gathers items emitted by an Observable into batches and emit the batch instead of emitting one item at a time.
 *
 * @author ALi Taghizadeh
 * @see <a href="http://reactivex.io/documentation/operators/buffer.html">Reactivex</a>
 */

public class BufferObservable {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable bufferObservable() {
        /*

        Buffer will create a window when the first value is produced.
        It will then put that value in to an internal cache. The window will stay open until the count of values has been reached.
        Each of these values will have been cached.
        Now that the count has been reached the window will close and the cache will be OnNext'ed as an IList<T>.
        When the next value is produced from the source, the cache is cleared and we start again.
        This means that Buffer will take an IObservable<T> and return an IObservable<IList<T>>.

        source|-1-2-3-4-5-6-7-8|
        result|-----1-----4---7|
                    2     5   8
                    3     6

        */

        Observable
                .just("1","2","3","4","5","6","7","8")
                .buffer(3)
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(List<String> list) {
                        System.out.println("onNext : ");
                        for (String s : list){
                            System.out.println(s);
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
