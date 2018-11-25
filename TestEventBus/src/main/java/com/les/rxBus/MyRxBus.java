package com.les.rxBus;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.les.rxJava.O01HelloWord;
import java.util.ArrayList;

/**
 * @ProjectName: TestEventBus
 * @Package: com.les.rxBus
 * @ClassName: RxBUs
 * @Description:
 * @Author: king
 * @CreateDate: 2018/11/13 10:45
 * @UpdateUser: king
 * @UpdateDate: 2018/11/13 10:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyRxBus {

    public void onCreate() {
        RxBus.get().register(this);
    }

    public void onDestroy() {
        RxBus.get().unregister(this);
    }
    @Subscribe
    public void eat(String food) {
        O01HelloWord.printThreadAndData(food);
    }

    @Subscribe(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore(ArrayList<String> foods) {
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }

    @Subscribe(
            thread = EventThread.COMPUTATION,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public void eatMore2(ArrayList<String> foods) {
        // purpose
        foods.forEach(O01HelloWord::printThreadAndData);
    }

    /*
    @Produce
    public String produceFood() {
        O01HelloWord.printThreadAndData("This is bread!");
        return "This is bread!";
    }
    */

    /*
    @Produce(
            thread = EventThread.IO,
            tags = {
                    @Tag("EAT_MORE")
            }
    )
    public ArrayList<String> produceMoreFood() {
        O01HelloWord.printThreadAndData("produce food");
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("123");
        stringArrayList.add("234");
        return stringArrayList;
    }
    */
    public void post() {
        RxBus.get().post(this);
    }

    public void postByTag() {
        RxBus.get().post("TAG_STORY", this);
    }

}
