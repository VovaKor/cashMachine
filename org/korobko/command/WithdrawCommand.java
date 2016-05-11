package org.korobko.command;

import org.korobko.CashMachine;
import org.korobko.ConsoleHelper;
import org.korobko.CurrencyManipulator;
import org.korobko.CurrencyManipulatorFactory;
import org.korobko.exception.InterruptOperationException;
import org.korobko.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Вова on 24.02.2016.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res;

    public WithdrawCommand()
    {
        this.res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw_en", Locale.ENGLISH);
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator =
                CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        Pattern p = Pattern.compile("\\d+");
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        while (true){


            String sum = ConsoleHelper.readString();
            Matcher m = p.matcher(sum);
            int summa;
            if (m.matches()&& (Integer.parseInt(sum)>0))
            {
                summa = Integer.parseInt(sum);
                if (currencyManipulator.isAmountAvailable(summa)){
                    try
                    {
                        Map<Integer, Integer> map = currencyManipulator.withdrawAmount(summa);
                        List<Map.Entry<Integer, Integer>> list =
                                new LinkedList<>(map.entrySet());
                        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>()
                        {
                            @Override
                            public int compare(Map.Entry<Integer, Integer> o1,
                                               Map.Entry<Integer, Integer> o2)
                            {
                                return (o2.getKey()).compareTo(o1.getKey());
                            }
                        });


                        for (Map.Entry<Integer, Integer> entry : list)
                        {

                            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                        }
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),summa,currencyCode));
                        break;
                    }catch (NotEnoughMoneyException e){
                        ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    }
                    catch(ConcurrentModificationException e)
                    {


                    }
                }
                else {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                }
            } else
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));

            }

        }
    }
}
