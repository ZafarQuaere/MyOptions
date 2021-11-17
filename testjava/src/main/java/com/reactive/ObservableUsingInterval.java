package com.reactive;

import com.logicprogram.multithreading.Handler;
import com.reactive.observer.DemoObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class ObservableUsingInterval {

    public static void main(String[] arg) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Observable<Long> interval = Observable.interval(3, TimeUnit.MILLISECONDS);
//                interval.subscribe(new DemoObserver());
//            }
//        };

        Observable<Long> interval = Observable.interval(3, TimeUnit.SECONDS);
        interval.subscribe(new DemoObserver());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
