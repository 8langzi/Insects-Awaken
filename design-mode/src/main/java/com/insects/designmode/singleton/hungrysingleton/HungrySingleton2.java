package com.insects.designmode.singleton.hungrysingleton;


// 饿汉式2
public class HungrySingleton2 {

    private static HungrySingleton2 hungrySingleton2;

    static {
        hungrySingleton2 = new HungrySingleton2();
    }

    private HungrySingleton2(){}

    public static HungrySingleton2 getInstance(){
        return hungrySingleton2;
    }

}
