package com.reactive.operators;

import com.reactive.observer.DemoObserver;
import com.reactive.utils.RxUtils;
import com.reactive.utils.Shape;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Combine Operators
 * 1) combineLatest Operator
 * 2) merge Operator
 * 3) zip Operator
 */
public class CombineOperators {
    public static void main(String[] arg) {
        mergeOperator();
        System.out.println();
        combineLatestOperator();
        System.out.println();
        zipOperator();
    }

    /**
     * zip works on multiple observables and zip them together.
     * it emits the results based on the function used to zip the events.
     */
    private static void zipOperator() {
        System.out.println("Zip Operator -----");
        Observable<Shape> shapesObs = Observable.fromIterable(RxUtils.shapes(5));
        Observable<Integer> numbersObs = Observable.fromIterable(RxUtils.postiveNumbers(3));
        numbersObs.zipWith(shapesObs,(o1,o2) -> {
           return o1.toString()+" :: "+o2.toString();
        })
        .subscribe(new DemoObserver());
    }

    /**
     *
     */
    private static void mergeOperator() {
        System.out.println("merge Operator -----");
        Observable<Shape> shapeObservable = Observable.fromIterable(RxUtils.shapes(7));
        Observable<Integer> numberObservable = Observable.fromIterable(RxUtils.postiveNumbers(7));

        Observable.merge(shapeObservable,numberObservable)
                .subscribe(new DemoObserver());

    }

    /**
     *
     */
    private static void combineLatestOperator() {
        System.out.println("combineLatest Operator -----");
        Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Long> observable2 = Observable.interval(2, TimeUnit.SECONDS);

        Observable.combineLatest(observable1, observable2, (o1, o2) ->
        {
            return "first: " + o1.toString() + "  second: " + o2.toString();
        })
                .take(6)
                .subscribe(new DemoObserver());
        RxUtils.sleep(10000L);
    }
}
