package com.reactive;

import com.reactive.observer.DemoObserver;

import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class ObservableFromCallable {

    public static void main(String[] arg){

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return doSomething();
            }
        };

        Observable.fromCallable(callable)
                .subscribe(new DemoObserver());
    }

    private static String doSomething() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Zafar";
    }
}
