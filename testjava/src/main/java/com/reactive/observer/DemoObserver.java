package com.reactive.observer;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class DemoObserver implements Observer {


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        System.out.println("onSubscribe(): "+d.toString());
    }

    @Override
    public void onNext(@NonNull Object o) {
        System.out.println("onNext(): "+o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.out.println("onError(): "+e.getLocalizedMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete()");
    }
}
