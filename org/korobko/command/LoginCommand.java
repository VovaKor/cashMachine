package org.korobko.command;

import org.korobko.CashMachine;
import org.korobko.ConsoleHelper;
import org.korobko.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Вова on 25.02.2016.
 */
class LoginCommand implements Command
{
    private ResourceBundle validCreditCards;
    private ResourceBundle res;
    public LoginCommand()
    {
        this.validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"verifiedCards", Locale.ENGLISH);
        this.res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"login_en", Locale.ENGLISH);
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        Pattern cardN = Pattern.compile("^\\d{12}$");
        Pattern pinC = Pattern.compile("^\\d{4}$");
        while (true)
        {

            String readCardN = ConsoleHelper.readString();
            String readPinC = ConsoleHelper.readString();
            Matcher card = cardN.matcher(readCardN);
            Matcher pin = pinC.matcher(readPinC);
            if (card.matches()&&pin.matches())
            {

               if (validCreditCards.containsKey(readCardN)&&
                       validCreditCards.getString(readCardN).equals(readPinC)){
                   ConsoleHelper.writeMessage(String.format(res.getString("success.format"),readCardN));
                   break;
               }
               else {
                   ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),readCardN));
                   ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
               }
            } else
            {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            }


        }
    }
}
