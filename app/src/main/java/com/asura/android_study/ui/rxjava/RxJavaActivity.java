package com.asura.android_study.ui.rxjava;

import com.asura.android_study.R;
import com.asura.android_study.ui.base.BaseActivity;


/**
 * rxjava1
 * <p>
 * Created by Liuxd on 2016/9/14 13:25.
 */
public class RxJavaActivity extends BaseActivity {

    @Override
    public int setLayoutId() {
        return R.layout.activity_rxjava;
    }

    @Override
    public void init() {
       /* //------------------------------------基础用法1-------------------------------------------------------------
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                //准备数据
            }

            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Logger.d("onNext  s%", s);
            }
        };
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onStart();
                subscriber.onNext("hello world!");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);

        //-------------------------------------基础用法2------------------------------------------------------------

        String[] strings = new String[]{"Hello", "World", "Android", "3", "4", "5", "6", "7"};
        Logger.d(strings);
        Observable.from(strings).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d("call：" + s);
            }
        });

        //---------------------------------map变换----------------------------------------------------------------

        Observable.just("持家").map(new Func1<String, GirlFriend>() {
            @Override
            public GirlFriend call(String s) {
                GirlFriend girlFriend = new GirlFriend();
                girlFriend.setFeature(s);
                return girlFriend;
            }
        }).subscribe(new Action1<GirlFriend>() {
            @Override
            public void call(GirlFriend girlFriend) {
                Logger.d(girlFriend.toString());
            }
        });
        //-----------------------------超长日志测试-------------------------------------------------------------

        //        StringBuffer stringbuff = new StringBuffer();
        //        String s = "1234567890abcdefghijklmnopqrstuvwxyz";
        //        for (int i = 0; i < 10000; i++) {
        //            stringbuff.append(s);
        //            Logger.d("循环次数：" + i);
        //        }
        //        Logger.d("最终结果：" + stringbuff.toString());

        //-------------------------------------flatmap变换------------------------------------------------------------
        List<Clothes> clothes1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Clothes clothes = new Clothes();
            clothes.setName("衣服1:" + i);
            clothes1.add(clothes);
        }
        List<Clothes> clothes2 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Clothes clothes = new Clothes();
            clothes.setName("衣服2:" + i);
            clothes2.add(clothes);
        }
        List<Clothes> clothes3 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Clothes clothes = new Clothes();
            clothes.setName("衣服3:" + i);
            clothes3.add(clothes);
        }
        GirlFriend girl1 = new GirlFriend();
        girl1.setClothes(clothes1);
        girl1.setFeature("漂亮");
        GirlFriend girl2 = new GirlFriend();
        girl2.setClothes(clothes2);
        girl2.setFeature("温柔");
        GirlFriend girl3 = new GirlFriend();
        girl3.setClothes(clothes3);
        girl3.setFeature("勤快");
        GirlFriend[] girls = new GirlFriend[]{
                girl1, girl2, girl3
        };
        Subscriber<Clothes> sub_clothes = new Subscriber<Clothes>() {
            @Override
            public void onCompleted() {
                Logger.d("flatmap变换 onCompleted()");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Clothes clothes) {
                Logger.d(clothes);
            }
        };
        Observable.from(girls).flatMap(new Func1<GirlFriend, Observable<Clothes>>() {
            @Override
            public Observable<Clothes> call(GirlFriend girlFriend) {
                // -----------------------------------filter过滤----------------------------------
                return Observable.from(girlFriend.getClothes())
                        .filter(new Func1<Clothes, Boolean>() {
                            @Override
                            public Boolean call(Clothes clothes) {
                                return clothes.getName().equals("衣服2:7");
                            }
                        });
            }
        }).subscribe(sub_clothes);
//----------------------------------------timerTask----------------------------------------------------
        RxCountDown.countdown(0, 10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Logger.d("当前进度" + integer);
            }
        });

        //-----------------------------repeat重复--------------------------------------------
        Observable.just("哈哈哈repeat").repeat(6).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d("重复收到的值：" + s);
            }
        });
        Observable.from(girls).repeat(6).map(new Func1<GirlFriend, String>() {
            @Override
            public String call(GirlFriend girlFriend) {
                return girlFriend.getFeature();
            }
        })
//        允许我们在输出最后一个元素之后做一些额外的事情
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        //只会在最后调用一次，不管重复次数
                        Logger.d("本次的女朋友发完了！");
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                //只会在最后调用一次，不管重复次数
                Logger.d("女朋友发完了...");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("女朋友出错了..." + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Logger.d("收到一个" + s + "的女朋友，开心！");
            }
        });
        *//*
        收到一个漂亮的女朋友，开心！
        收到一个温柔的女朋友，开心！
        收到一个勤快的女朋友，开心！
        收到一个漂亮的女朋友，开心！
        收到一个温柔的女朋友，开心！
        收到一个勤快的女朋友，开心！
        收到一个漂亮的女朋友，开心！
        收到一个温柔的女朋友，开心！
        收到一个勤快的女朋友，开心！
        收到一个漂亮的女朋友，开心！
        收到一个温柔的女朋友，开心！
        收到一个勤快的女朋友，开心！
        本次的女朋友发完了！
        女朋友发完了...
        *//*
//              .subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Logger.d("收到一个" + s + "的女朋友，开心！");
//            }
//        })
//        ;

        //-------------------------------range 特定范围--------------------------------------
//        Observable.from(strings).range(2, 8).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Logger.d("range(2,8):" + s);
//            }
//        });*/
    }
}
