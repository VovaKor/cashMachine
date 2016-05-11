package org.korobko;

import org.korobko.command.CommandExecutor;
import org.korobko.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by Вова on 21.02.2016.
 */
public class CashMachine
{
    public static final String RESOURCE_PATH =
            "org.korobko.resources.";
    public static void main(String[] args)
    {

        try
        {
        Locale.setDefault(Locale.ENGLISH);
        Operation operation;
        do
        {
            CommandExecutor.execute(Operation.LOGIN);
            operation = ConsoleHelper.askOperation();

            CommandExecutor.execute(operation);
        }while (!operation.equals(Operation.EXIT));
        }
             catch (InterruptOperationException e)
             {
                ConsoleHelper.printExitMessage();
            }
    }
}
