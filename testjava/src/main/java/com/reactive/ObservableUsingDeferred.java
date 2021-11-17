package com.reactive;

import com.reactive.observer.DemoObserver;
import com.reactive.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ObservableUsingDeferred {

    public static void main(String[] arg) {

        Observable<Integer> defer = Observable.defer(() -> { // this will not get called until it is subscribed
            System.out.println("Subscribed");
            return Observable.fromIterable(RxUtils.postiveNumbers(6));
        });
        System.out.println("Subscribe below");
        defer.subscribe(new DemoObserver());
        System.out.println();
        defer.subscribe(new DemoObserver());
    }
}
