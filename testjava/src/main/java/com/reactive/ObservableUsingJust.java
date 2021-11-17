package com.reactive;


import com.reactive.observer.DemoObserver;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ObservableUsingJust {
    public static final String LOGGER = ObservableUsingJust.class.getSimpleName();

    public static void main(String... arg){
        Observable.just("1","2","3","4","5","6","7","8","9","10") // just can emit max 10 elements
                .subscribe(new DemoObserver());
    }
}
