package com.reactive.operators;

import com.reactive.observer.DemoObserver;
import com.reactive.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Filter operators are
 * Debounce,distinct,filter, first, last, skipLast,takeLast
 * elementAt,ignoreElement,sample,skip etc
 */
public class FilterOperators {
    static List<Character> chList = new ArrayList<>();

    public static void main(String[] arg) {
//        distinctOperator();
//        debounceOperator();
        elementAtOperator();
    }

    private static void elementAtOperator() {
        Observable.fromIterable(RxUtils.postiveNumbers(10))
                .elementAt(11)
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        System.out.println("onSuccess(): "+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError()");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete()");
                    }
                });
    }

    /**
     * removes duplicate items from observable
     */
    private static void distinctOperator() {
        System.out.println("Debounce Operator -- ");
        Observable.just(1,2,3,2,5,3,7,3,4)
                .distinct()
                .subscribe(new DemoObserver());
    }

    /**
     * emits items from Observable only after certain amount of time.
     *
     */
    private static void debounceOperator() {
        System.out.println("Debounce Operator -- ");
        Random r = new Random();
        Observable.interval(2, TimeUnit.SECONDS)
              .map(c -> {
                  chList.add((char) (r.nextInt(26)+'a'));
                  return chList;
              })
                .doOnEach(notification -> {
                    System.out.println("Current Value: "+notification.getValue());
                }).debounce(1,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new DemoObserver());

        RxUtils.sleep(5000L);
    }
}
