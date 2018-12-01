package com.les;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import sun.rmi.runtime.Log;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
        System.out.println("get event " + event + ",thread=" + Thread.currentThread());
    };

    public void onStart() {
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        App app = new App();
        app.onStart();
        EventBus.getDefault().post(new MessageEvent());

    }

    public void test()
    {
        StringBuffer mRxOperatorsText = new StringBuffer();

        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxOperatorsText.append("First onSubscribe :"+d.isDisposed()+"\n");
                log.info("First onSubscribe :"+d.isDisposed()+"\n");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxOperatorsText.append("First onNext value :"+integer + "\n");
                log.info("First onNext value :"+integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxOperatorsText.append("First onError:"+e.getMessage()+"\n");
                log.info( "First onError:"+e.getMessage()+"\n" );
            }

            @Override
            public void onComplete() {
                mRxOperatorsText.append("First onComplete!\n");
                log.info( "First onComplete!\n");
            }
        });

        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);

        asyncSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxOperatorsText.append("Second onSubscribe :"+d.isDisposed()+"\n");
                log.info( "Second onSubscribe :"+d.isDisposed()+"\n");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxOperatorsText.append("Second onNext value :"+integer + "\n");
                log.info("Second onNext value :"+integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxOperatorsText.append("Second onError:"+e.getMessage()+"\n");
                log.info( "Second onError:"+e.getMessage()+"\n" );
            }

            @Override
            public void onComplete() {
                mRxOperatorsText.append("Second onComplete!\n");
                log.info( "Second onComplete!\n");
            }
        });

        asyncSubject.onNext(4);
        asyncSubject.onNext(5);
        asyncSubject.onComplete();
    }
}
