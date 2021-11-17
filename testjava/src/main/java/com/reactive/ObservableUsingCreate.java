package com.reactive;

import com.reactive.observer.DemoObserver;
import com.reactive.utils.RxUtils;
import com.reactive.utils.Shape;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class ObservableUsingCreate {
    public static void main(String[] arg) {
        List<Shape> shapes = RxUtils.shapes(3);
        Observable<Object> objectObservable = Observable.create(new ObservableOnSubscribe<Object>() {
            @SuppressWarnings("CheckResult")
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Exception {
                try {
                    shapes.forEach(emitter::onNext);
                } catch (Exception e) {
                    emitter.onError(e);
                    e.printStackTrace();
                }
                emitter.onComplete();
            }
        });
        objectObservable.subscribe(new DemoObserver());
    }
}
