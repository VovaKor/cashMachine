package org.korobko;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Вова on 22.02.2016.
 */
public final class CurrencyManipulatorFactory
{
    private static Map<String, CurrencyManipulator> manipulators = new HashMap<>();

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        Collection<CurrencyManipulator> collection = new ArrayList<>(manipulators.values());

        return  collection;
    }

    private CurrencyManipulatorFactory()
    {

    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){

            if (manipulators.containsKey(currencyCode)){
                return manipulators.get(currencyCode);
            }

        CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
        manipulators.put(currencyCode,manipulator);
        return manipulator;
    }
}
