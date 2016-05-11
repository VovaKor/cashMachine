package org.korobko;

import org.korobko.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Вова on 21.02.2016.
 */
public class ConsoleHelper
{
   private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common_en", Locale.ENGLISH);
    public static void writeMessage(String message){
       System.out.println(message);
   }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
        Pattern p = Pattern.compile("\\d+");
        String[] result = readString().split(" ");
            Matcher m = p.matcher(result[0]);
                Matcher m1 = p.matcher(result[1]);
        while (true){
            if (m.matches()&& m1.matches()&&Integer.parseInt(result[0])>0&&
                    Integer.parseInt(result[1])>0)
            {
            result[0] = result[0];
                result[1] = result[1];
                break;
            } else
            {
            writeMessage(res.getString("invalid.data"));
            }

        }
        return result;

    }
    public static String askCurrencyCode() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.currency.code"));
        Pattern p = Pattern.compile("^[A-Z]{3}$");

        while (true)
        {

                String result = readString().toUpperCase();
                Matcher m = p.matcher(result);
                if (m.matches())
                {
                    return result;
                } else
                {
                    writeMessage(res.getString("invalid.data"));
                }


        }
    }
    public static String readString() throws InterruptOperationException
    {

        String result="";
        try
        {
            result= reader.readLine();
            if (result.equalsIgnoreCase(res.getString("operation.EXIT")))throw new InterruptOperationException();

        }
        catch (IOException e)
        {

        }
        return result;
    }
    public static Operation askOperation() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.operation"));

        while (true)
        {
            try
            {
            int result = Integer.parseInt(readString());


              return Operation.getAllowableOperationByOrdinal(result);
            } catch (NumberFormatException e)
            {
                writeMessage(res.getString("choose.operation"));
            }catch (IllegalArgumentException e){
                writeMessage(e.getMessage());
            }


        }
    }
    public static void printExitMessage(){
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
