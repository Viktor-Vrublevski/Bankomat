package com.javarush.task.task26.task2613;

import java.util.*;

public class CurrencyManipulatorFactory {

    private static Map<String,CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory(){

    }

    public static CurrencyManipulator  getManipulatorByCurrencyCode(String currencyCode){
        currencyCode = currencyCode;
       if (map.containsKey(currencyCode)) return map.get(currencyCode);
       else map.put(currencyCode,new CurrencyManipulator(currencyCode));
           return map.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        List<CurrencyManipulator> list = new ArrayList<>();
        for (Map.Entry<String, CurrencyManipulator> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }
}
