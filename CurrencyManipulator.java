package com.javarush.task.task26.task2613;


import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

/*
класс , который будет хранить всю информацию про выбранную валюту
 */
public class CurrencyManipulator {

    private String currencyCode;

    // это Map<номинал, количество>
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    // Добавляет банкноты в банкомат
    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            count = count + denominations.get(denomination);
        }
        denominations.put(denomination, count);
    }

    //  Считает общий номинал всех банкнот
    public int getTotalAmount() {
        int quantity = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            quantity += entry.getKey() * entry.getValue();
        }
        return quantity;
    }

    // Проверяет есть ли банкноты в банкомате
    public boolean hasMoney() {
        if (denominations.isEmpty()) {
            return false;
        }
        return true;
    }
//   достаточно ли средств на счету
    public boolean isAmountAvailable(int expectedAmount){
        if (expectedAmount <= getTotalAmount()) return true;
        return false;
    }

  // Списать деньги со счета
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(denominations);
        Map<Integer, Integer> maps = new HashMap<>();
        while (true){
            if (expectedAmount == 0) break;
            int max = 0;
           try {
              max  = treeMap.floorKey(expectedAmount);
               if (treeMap.get(max) <=0 ){
                   denominations.remove(max);
                   continue;
               }else {
                   treeMap.put(max, treeMap.get(max) - 1);
                   expectedAmount -= max;
                   if (maps.size() == 0 | !maps.containsKey(max)) {
                       maps.put(max, 1);
                   } else {
                       maps.put(max, maps.get(max) + 1);
                   }
               }

           }catch (NullPointerException e){
               throw new NotEnoughMoneyException();
           }
        }
        for (Map.Entry<Integer,Integer> entry : denominations.entrySet()){
            if (maps.containsKey(entry.getKey())){
            denominations.put(entry.getKey(),entry.getValue() -
                    maps.get(entry.getKey()));
            }
        }
        Set<Integer> set = denominations.keySet();
       for (Integer key : set){
           if (denominations.get(key) <= 0){
               denominations.remove(key);
           }
       }
        return maps;
    }

}
