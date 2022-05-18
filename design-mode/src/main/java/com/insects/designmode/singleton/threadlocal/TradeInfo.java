package com.insects.designmode.singleton.threadlocal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TradeInfo {

    public static ThreadLocal<ConcurrentHashMap<String, List<Map<String, Object>>>> threadLocal = new ThreadLocal<>();

    public static void updateTradeInfo(List<Map<String,Object>> map,String key){
        ConcurrentHashMap<String, List<Map<String, Object>>> tradeInfo = threadLocal.get();
        if(tradeInfo == null && tradeInfo.isEmpty()){
            tradeInfo = new ConcurrentHashMap<>();
        }
        if(tradeInfo.contains(key)){
            List<Map<String, Object>> mapList = tradeInfo.get(key);
            mapList.addAll(map);
            tradeInfo.put(key,mapList);
            threadLocal.set(tradeInfo);
        }else {
            tradeInfo.put(key, map);
            threadLocal.set(tradeInfo);
        }
    }


    public static ConcurrentHashMap<String, List<Map<String, Object>>> get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }

}
