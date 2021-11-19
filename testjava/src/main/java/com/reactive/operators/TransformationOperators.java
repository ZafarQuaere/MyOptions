package com.reactive.operators;

import com.reactive.observer.DemoObserver;
import com.reactive.utils.RxUtils;
import com.reactive.utils.Shape;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.flowable.FlowableDistinctUntilChanged;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureDrop;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class TransformationOperators {
    public static void main(String[] arg){
//        bufferOperator();
//        System.out.println();
//        groupByOperator();
//        System.out.println();
        mapOperator();
        System.out.println();
        flatMapOperator();
        System.out.println();
        scanOperator();
    }

    /**
     * scan Operator also modify the items and transmit them but it includes
     * previous result as well.
     */
    private static void scanOperator() {
        Observable<Integer> scanObservable = Observable.fromIterable(RxUtils.postiveNumbers(10));
        scanObservable.scan((integer1,integer2) -> {return integer1+integer2;})
                .subscribe(new DemoObserver());
    }

    /**
     * flatMap operator also modify the items but it returns Observable which can be subscribed again
     * and it doesn't maintain order.
     */
    private static void flatMapOperator() {
        Observable.fromIterable(RxUtils.postiveNumbers(9))
                .flatMap(integer -> {return twice(integer);})
                .subscribe(new DemoObserver());
    }

    private static ObservableSource<?> twice(Integer integer) {
        return Observable.create(observableEmitter -> {
            if (!observableEmitter.isDisposed()) {
                observableEmitter.onNext(integer*2);
            } else {
                observableEmitter.onComplete();
            }
        });
    }

    /**
     * map returns list of events, flatmap returns observable.
     */
    private static void mapOperator() {
        Observable.fromIterable(RxUtils.postiveNumbers(9))
                .map(integer -> integer*2)
                .subscribe(new DemoObserver());
    }

    private static void groupByOperator() {
        Observable.fromIterable(RxUtils.shapes(20))
                .groupBy(new Function<Shape, Object>() {
                    @Override
                    public Object apply(@NonNull Shape shape) throws Exception {
                        return shape.getShape();
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<GroupedObservable<Object, Shape>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GroupedObservable<Object, Shape> objectShapeGroupedObservable) {
                        System.out.println("key:  "+objectShapeGroupedObservable.getKey());
                        objectShapeGroupedObservable.subscribe(new DemoObserver());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        delay(1000);

    }

    private static void bufferOperator() {
        Observable.fromIterable(RxUtils.shapes(10))
                .buffer(3) // buffer operator (it will display the list in the collection of 3)
                .subscribe(new DemoObserver());
    }

    private static void delay(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
