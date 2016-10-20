import rx.Observable;
import rx.Subscriber;
import rx.functions.*;
import rx.observables.BlockingObservable;
import rx.observables.MathObservable;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;
import rx.schedulers.Timestamped;
import rx.util.async.Async;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CreateMain {

    public static void main(String[] args) {
        CreateMain main = new CreateMain();

        // 创建操作
//        main.testCreate();
//        main.testDefer();
//        main.testEmpty();
//        main.testNever();
//        main.testThrow();
//        main.testFrom();
//        main.testInterval();
//        main.testJust();
//        main.testRange();
//        main.testRepeat();
//        main.testTimer();

        // 变换操作
//        main.testBuffer();
//        main.testFlatMap();
//        main.testMap();
//        main.testScan();

        // 过滤操作
//        main.testDistinct();
//        main.testElementAt();
//        main.testFilter();
//        main.testFirst();
//        main.testIgnoreElement();
//        main.testLast();
//        main.testSkip();
//        main.testSkipLast();
//        main.testTake();
//        main.testTakeLast();

        // 结合操作
//        main.testCombineLatest();
//        main.testMerge();
//        main.testStartWith();
//        main.testZip();

        // 辅助操作
//        main.testDelay();
//        main.testDoOnNext();
//        main.testDoOnSubscribe();
//        main.testFinallyDo();
//        main.testObserveOn();
//        main.testSerialize();
//        main.testForeach();
//        main.testTimeInterval();
//        main.testTimeout();
//        main.testTimestamp();
//        main.testToList();

        // 条件和布尔操作
//        main.testAll();
//        main.testAmb();
//        main.testContains();
//        main.testExists();
//        main.testDefaultIfEmpty();
//        main.testSequenceEqual();
//        main.testSkipUntil();
//        main.testSkipWhile();
//        main.testTakeWhile();

        // 算术和聚合操作
//        main.testAverage();
//        main.testConcat();
//        main.testReduce();
//        main.testMin();

        // 异步操作
//        main.testStart();
//        main.testToAsync();
//        main.testStartFuture();


        // 阻塞操作 BlockingObservable
        main.testBlockingObservable();
    }

    void testBlockingObservable() {

        BlockingObservable a = BlockingObservable.from(Observable.just("Hi", "Man"));

        a.forEach(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }


    void testStartFuture() {

        // 完全不懂Future是啥玩意
        Async.startFuture(new Func0<Future<?>>() {
            @Override
            public Future<?> call() {
                return null;
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println(o);
            }
        });
    }


    void testToAsync() {
        Async.toAsync(new Func0<String>() {
            @Override
            public String call() {
                return "hi man";
            }
        }).call().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }


    void testStart() {
        Async.start(new Func0<String>() {
            @Override
            public String call() {
                return "hello world";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }


    void testMin() {

        Observable<Integer> a = Observable.range(1, 5);
        MathObservable.min(a).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("min " + integer);
            }
        });
        MathObservable.max(a).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("max " + integer);
            }
        });
        a.count().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("count " + integer);
            }
        });
        MathObservable.sumInteger(a).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("sum " + integer);
            }
        });


    }

    void testReduce() {
        Observable<Integer> a = Observable.range(1, 5);
        a.reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

    }

    void testConcat() {
        Observable<Integer> a = Observable.just(1, 2, 3);
        Observable<Integer> b = Observable.just(4, 5, 6);

        a.concatWith(b).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

    }

    void testAverage() {
        Observable<Integer> a = Observable.just(1, 2, 3, 4);

        MathObservable.from(a).averageInteger(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }


    void testTakeWhile() {
        Observable<Integer> a = Observable.just(1, 2, 3);

        a.takeWhile(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 3;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    void testSkipWhile() {
        Observable<Integer> a = Observable.just(1, 2, 3, 4, 5);

        a.skipWhile(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return false;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }


    void testSkipUntil() {
        Observable<Integer> a = Observable.just(1, 3, 2);
        Observable<Integer> b = Observable.just(4, 5, 6);

        a.skipUntil(b).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

        a.skipUntil(Observable.empty()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("end");

    }


    void testSequenceEqual() {

        Observable<Integer> a = Observable.just(1, 3, 2);
        Observable<Integer> b = Observable.just(1, 2, 3);

        Observable.sequenceEqual(a, b).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println(aBoolean);
            }
        });

    }


    void testDefaultIfEmpty() {
        Observable.empty()
                .defaultIfEmpty("000")
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        System.out.println(o);
                    }
                });

    }

    void testExists() {
        Observable.just(1, 2, 3)
                .exists(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer == 2;
                    }
                })
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println(aBoolean);
                    }
                });

    }


    void testContains() {
        Observable.just(1, 2, 3)
                .contains(2)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        System.out.println(aBoolean);
                    }
                });
    }


    void testAmb() {
        Observable<Integer> sa = Observable.just(1, 2, 3);
        Observable<Integer> sb = Observable.just(4, 5, 6);

        Observable.amb(sa, sb).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }


    void testAll() {
        Observable.just(1, 2, 3)
                .all(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 2;
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                System.out.println(aBoolean);
            }
        });

    }


    void testToList() {
        Observable.just(1, 3, 2)
                .toSortedList()
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        System.out.println(integers);
                    }
                });
    }

    void testTimestamp() {
        Observable.just(3, 4)
                .timestamp()
                .subscribe(new Action1<Timestamped<Integer>>() {
                    @Override
                    public void call(Timestamped<Integer> integerTimestamped) {
                        System.out.println(integerTimestamped);
                    }
                });
    }

    void testTimeout() {
        Observable.just(1)
                .delay(2000, TimeUnit.MILLISECONDS, Schedulers.immediate())
                .timeout(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                });
    }


    void testTimeInterval() {
        Observable.just(2, 3, 4)
                .timeInterval()
                .subscribe(new Action1<TimeInterval<Integer>>() {
                    @Override
                    public void call(TimeInterval<Integer> integerTimeInterval) {
                        System.out.println(integerTimeInterval);
                    }
                });
    }


    void testForeach() {
        Observable.just(1, 2, 3)
                .forEach(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("error ?");
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        System.out.println("complete ?");
                    }
                });
    }

    void testSerialize() {
        Observable.just(1, 2, 3)
                .serialize()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    void testObserveOn() {
        Observable.just(2, 3, 4)
                .observeOn(Schedulers.computation())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    void testFinallyDo() {
        Observable.just(3, 4, 5)
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Complete");
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("terminated");
                    }
                })
                .doAfterTerminate(new Action0() { // 同 fillnalyDo
                    @Override
                    public void call() {
                        System.out.println("fillayDo");
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    void testDoOnSubscribe() {
        Observable.just(2, 3, 4)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("有人来订阅了");
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("onNext " + integer);
                    }
                });
    }


    void testDoOnNext() {
        Observable.just(1, 2, 3)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("doOnNext  " + integer);
                    }
                })
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("doOnNext2  " + integer);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("onNext" + integer);
                    }
                });
    }

    private void testDelay() {
        Observable.just(5).delay(1, TimeUnit.SECONDS, Schedulers.immediate())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    private void testZip() {
        Observable<Integer> sourceA = Observable.from(new Integer[]{1, 2, 3, 4});
        Observable<Integer> sourceB = Observable.from(new Integer[]{28, 38, 58});

        Observable.zip(sourceA, sourceB, new Func2<Integer, Integer, String>() {
            @Override
            public String call(Integer integer, Integer integer2) {
                return integer + "---" + integer2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                System.out.println(str);
            }
        });
    }

    private void testStartWith() {
        Observable.just(1, 2, 3).startWith(100).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }


    private void testMerge() {

        Observable<Integer> sourceA = Observable.from(new Integer[]{1, 2, 3});
        Observable<Integer> sourceB = Observable.from(new Integer[]{28, 38, 58});

        Observable.merge(sourceA, sourceB).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

    }


    private void testCombineLatest() {

        Observable<String> sourceName = Observable.from(new String[]{"Hello", "World"});
        Observable<Integer> sourceAge = Observable.from(new Integer[]{28, 38, 58});

        Observable.combineLatest(sourceName, sourceAge, new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {
                return String.format("%s age : %d", s, integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }


    private void testTake() {
        Observable.range(1, 10)
                .take(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    private void testTakeLast() {
        Observable.range(1, 10)
                .takeLast(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    private void testSkipLast() {
        Observable.range(1, 10)
                .skipLast(3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    private void testSkip() {
        Observable.range(100, 10)
                .skip(3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    private void testLast() {
        Observable.range(10, 10)
                .last()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    private void testIgnoreElement() {
        Observable.just(1, 2, 3)
                .ignoreElements()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("next " + integer);
                    }
                });
    }


    private void testFirst() {
        Observable.range(1, 100)
                .first(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer a) {
                        return (a % 2 == 1) && (a % 3 == 1) && (a > 1);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    private void testFilter() {
        Observable.range(1, 10)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 3 == 0;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    private void testElementAt() {
        Observable.range(33, 10)
                .elementAt(5)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }


    private void testDistinct() {
        Observable.from(new Integer[]{1, 5, 3, 5, 7, 1})
                .distinct()
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("next " + integer);
                    }
                });
    }


    private void testScan() {
        Observable.range(1, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer a, Integer b) {
                        return a + b;
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("next " + integer);
                    }
                });

    }


    private void testMap() {
        Observable.from(new String[]{"Hello", "world"})
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.length();
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("next" + integer);
                    }
                });
    }

    private void testFlatMap() {
        String[] names = new String[]{"Hello", "World"};

        // 先产生只有一个元素的数据源
        Observable<String[]> source = Observable.just(names);

        // 对这个元素进行分割,降维
        source.flatMap(new Func1<String[], Observable<String>>() {
            @Override
            public Observable<String> call(String[] strings) {
                // 从一个数组, 生成多个元素的 数据
                return Observable.from(strings);
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("next " + s);
            }
        });

    }


    private void testBuffer() {
        Observable.range(1, 10).buffer(4).subscribe(new Subscriber<List<Integer>>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(List<Integer> integers) {
                System.out.println("next " + integers);
            }
        });
    }


    private void testTimer() {
        Observable.timer(1000, TimeUnit.MILLISECONDS, Schedulers.immediate())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("next " + aLong);
                    }
                });
    }


    private void testRepeat() {
        Observable.just(5).repeat(3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("next " + integer);
            }
        });
    }


    private void testRange() {
        Observable.range(10, 5).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("next " + integer);
            }
        });
    }

    private void testJust() {
        Observable.just(1, 8, 9).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("next " + integer);
            }
        });
    }


    private void testInterval() {


        Observable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.immediate()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("next " + aLong);
            }
        });
    }


    private void testFrom() {
        Integer[] items = {1, 2, 3, 4, 5};
        Observable.from(items).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("next:" + integer);
            }
        });
    }

    private void testThrow() {
        Observable.error(new Exception()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Object integer) {
                System.out.println("onNext " + integer);
            }
        });
    }


    private void testNever() {
        Observable.never().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Object integer) {
                System.out.println("onNext " + integer);
            }
        });
    }

    private void testEmpty() {
        Observable.empty().subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Object integer) {
                System.out.println("onNext " + integer);
            }
        });
    }


    private void testDefer() {

        System.out.println("没有例子 :(");

    }


    private void testCreate() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                    }
                    observer.onCompleted();
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("next:" + integer);
            }
        });
    }
}
