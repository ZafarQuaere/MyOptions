package com.reactive;

import com.reactive.observer.DemoObserver;
import com.reactive.utils.RxUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ObservableWithFlowable {

    public static void main(String[] arg) {

        // without flowable there is a chance of outOfMemory Error.
      /*  Observable<Integer> obsWidotFlow = Observable.fromIterable(RxUtils.postiveNumbers(200000))
                .repeat()
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread());
        obsWidotFlow.subscribe(new DemoObserver());

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

*/
        Flowable<Integer> flowableObsrvble = Flowable.fromIterable(RxUtils.postiveNumbers(200000))
                .repeat()
                .observeOn(Schedulers.newThread(),false,5)
                .subscribeOn(Schedulers.newThread())
                .doOnNext(integer -> {
                    System.out.println("Emitting integer: "+integer);});

        flowableObsrvble.subscribe(new Subscriber<Integer>() {
            private Subscription mSubscription;
            final AtomicInteger counter = new AtomicInteger(0);
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe(), "+s.toString());
                mSubscription = s;
                s.request(5);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext(), "+integer);
                delay(100);
                if (counter.incrementAndGet()%5 == 0){
                    mSubscription.request(5);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError(), "+t.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete(), ");
            }
        });
        delay(10000);
    }

    private static void delay(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
