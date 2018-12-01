package com.les;

import static org.junit.Assert.assertTrue;

import com.les.rxJava.O01HelloWord;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class RxJavaTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testBasic()
    {
        //lamda
        Flowable.just("Hello world 1").subscribe((String str) -> { O01HelloWord.printThreadAndData(str);});
        //lamda
        Flowable.just("Hello world 2").subscribe((str) -> O01HelloWord.printThreadAndData(str));
        //lamda
        Flowable.just("Hello world 3").subscribe(O01HelloWord::printThreadAndData);

        Flowable.just("Hello world 4")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        O01HelloWord.printThreadAndData(s);
                    }
                });

    }

    @Test
    public void testFluentAPI()
    {
        Flowable.fromCallable(() -> {
            // imitate expensive computation
            Thread.sleep(1000);
            O01HelloWord.printThreadAndData("Flowable");
            return "Done";
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe(O01HelloWord::printThreadAndData, Throwable::printStackTrace);

        try {
            // <--- wait for the flow to finish
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFluentAPI2()
    {
        Flowable<String> source = Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        });

        Flowable<String> runBackground = source.subscribeOn(Schedulers.io());

        Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());

        showForeground.subscribe(System.out::println, Throwable::printStackTrace);


    }

    @Test
    public void testParallelProcess()
    {
        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map((w) ->
                                {
                                    String data = "computing...";
                                    O01HelloWord.printThreadAndData(data + "begin");
                                    TimeUnit.SECONDS.sleep( (10 - w) * 1);
                                    O01HelloWord.printThreadAndData(data + "end");
                                    return w * w;
                                })
                )
                .blockingSubscribe(O01HelloWord::printThreadAndData);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParallelProcessConcatMap()
    {
        Flowable.range(1, 10)
                .concatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map((w) ->
                                {
                                    String data = "computing...";
                                    printBeginAndEnd(w, data);
                                    return w * w;
                                })
                )
                .blockingSubscribe(O01HelloWord::printThreadAndData);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printBeginAndEnd(Integer w, String data) throws InterruptedException {
        O01HelloWord.printThreadAndData(data + "begin");
        TimeUnit.SECONDS.sleep((10 - w) * 1);
        O01HelloWord.printThreadAndData(data + "end");
        O01HelloWord.printThreadAndData(data);
    }

    @Test
    public void testParallelProcessConcatMapEager()
    {
        Flowable.range(1, 10)
                .concatMapEager(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map((w) ->
                                {
                                    String data = "computing...";
                                    printBeginAndEnd(w, data);
                                    return w * w;
                                })
                )
                .blockingSubscribe(O01HelloWord::printThreadAndData);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
