package com.insects.designmode.singleton.hungrysingleton;

// 饿汉式1
public class HungrySingleton1 {

    private static HungrySingleton1 hungrySingleton = new HungrySingleton1();

    private HungrySingleton1(){}

    private static HungrySingleton1 getInstance(){
        return hungrySingleton;
    }

}
