package org.korobko;

import org.korobko.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by Вова on 22.02.2016.
 */
public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();
    public boolean hasMoney(){
        boolean result=true;
        if (denominations.isEmpty()) result = false;
        else {
            int zerosCount=0;
            for (Map.Entry<Integer,Integer> pair : denominations.entrySet()){
                if (pair.getValue()==0) zerosCount++;
            }
            if (zerosCount==denominations.size()) result=false;
        }
        return result;
    }
    public int getTotalAmount(){
        int result=0;
        for (Map.Entry<Integer, Integer> entry: denominations.entrySet()){
            result += entry.getKey()*entry.getValue();
        }
        return result;
    }
    public boolean isAmountAvailable(int expectedAmount){

        return hasMoney()&& expectedAmount <= getTotalAmount();
    }
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        Map<Integer, Integer> temp = new HashMap<>(denominations);
        int sum = expectedAmount;
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> pair : temp.entrySet())
            list.add(pair.getKey());
        Collections.sort(list);
        Collections.reverse(list);
        Map<Integer,Integer> result = new TreeMap<>(Collections.reverseOrder());
        for(Integer d: list){
            int key = d;
            int value = temp.get(d);
            while (true){
                if (sum < key || value <= 0) {
                    temp.put(key, value);
                    break;
                }
                sum -= key;
                value--;
                if (result.containsKey(key))
                    result.put(key, result.get(key) + 1);
                else
                    result.put(key, 1);
            }
        }
        if (sum > 0)
            throw new NotEnoughMoneyException();
        else {

            denominations.clear();
            denominations.putAll(temp);

        }
        return result;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;

    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }
}
