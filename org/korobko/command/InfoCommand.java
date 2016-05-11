package org.korobko.command;

import org.korobko.CashMachine;
import org.korobko.ConsoleHelper;
import org.korobko.CurrencyManipulator;
import org.korobko.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Вова on 24.02.2016.
 */
class InfoCommand implements Command
{
    private ResourceBundle res;

    public InfoCommand()
    {
        this.res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"info_en", Locale.ENGLISH);
    }

    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> manipulatorMap=CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (manipulatorMap.isEmpty())
            ConsoleHelper.writeMessage(res.getString("no.money"));
        else
        {
            int count=0;
            for (CurrencyManipulator manipulator : manipulatorMap)
            {
                if (manipulator.hasMoney() && manipulator.getTotalAmount()>0)
                {
                    ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
                    count++;
                }
            }
            if (count==0)
                ConsoleHelper.writeMessage(res.getString("no.money"));
        }

    }
}
