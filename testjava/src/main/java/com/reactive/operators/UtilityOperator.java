package com.reactive.operators;

import com.reactive.observer.DemoObserver;
import com.reactive.utils.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 1) do and delay Operator
 * 2) timestamp and timeout operator
 * 3) all and contains operator
 * 4) skipWhile and takeWhile operator
 */
public class UtilityOperator {

    public static void main(String[] arg){
//        System.out.println("delay operator");
//        delayOperator();
//        System.out.println();
        System.out.println("do operators");
        doOperator();
        System.out.println();
        System.out.println("timestamp operator");
        timeStampOperator();
        System.out.println();
        System.out.println("timeOut operator");
        timeoutOperator();
    }

    private static void timeoutOperator() {
        Observable.timer(4,TimeUnit.SECONDS)
                .timeout(2,TimeUnit.SECONDS) //here it will show the error value as timer will delay 4 sec but timeout is 2 sec
                .subscribe(new DemoObserver());
        RxUtils.sleep(6000l);
    }

    private static void timeStampOperator() {
        Observable.fromIterable(RxUtils.shapes(5))
                .timestamp() // it will attach the times with every item emitted by observer.
                .subscribe(new DemoObserver());
    }

    private static void doOperator() {
        Observable.fromIterable(RxUtils.shapes(10))
                .doOnSubscribe(disposable -> {
                    System.out.println("Stream is subscribed");
                })
                .doOnEach(shapeNotification -> {
                    System.out.println("current value {}: "+shapeNotification.getValue());
                })
                .doOnNext(shape -> {
                    System.out.println("Shape is: "+shape);
                })
                .doOnComplete(()->{
                    System.out.println("doOnComplete()");
                })
                .subscribe(new DemoObserver());
    }

    private static void delayOperator() {
        Observable.fromIterable(RxUtils.postiveNumbers(10))
                .delay(4, TimeUnit.SECONDS)
                .subscribe(new DemoObserver());
        RxUtils.sleep(50000l);
    }
}
