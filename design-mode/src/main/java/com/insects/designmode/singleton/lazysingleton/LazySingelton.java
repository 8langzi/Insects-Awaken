package com.insects.designmode.singleton.lazysingleton;

// 懒汉式
public class LazySingelton {

    private static LazySingelton instance;

    private LazySingelton(){}

    public static LazySingelton getInstance(){
        if(null == instance){
            instance = new LazySingelton();
        }
        return instance;
    }


}
