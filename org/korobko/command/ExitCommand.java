package org.korobko.command;

import org.korobko.CashMachine;
import org.korobko.ConsoleHelper;
import org.korobko.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Вова on 24.02.2016.
 */
class ExitCommand implements Command
{
    private ResourceBundle res;

    public ExitCommand()
    {
        this.res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"exit_en", Locale.ENGLISH);
    }

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String command = ConsoleHelper.readString();
        if (command.equals(res.getString("yes")))ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
