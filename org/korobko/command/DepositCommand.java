package org.korobko.command;

import org.korobko.CashMachine;
import org.korobko.ConsoleHelper;
import org.korobko.CurrencyManipulator;
import org.korobko.CurrencyManipulatorFactory;
import org.korobko.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Вова on 24.02.2016.
 */
class DepositCommand implements Command
{
    private ResourceBundle res;

    public DepositCommand()
    {
        this.res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"deposit_en", Locale.ENGLISH);
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulator currencyManipulator =
                CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        currencyManipulator.addAmount(Integer.parseInt(twoDigits[0]),Integer.parseInt(twoDigits[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),currencyManipulator.getTotalAmount(),currencyCode));
    }
}
