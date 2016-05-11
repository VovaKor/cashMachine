package org.korobko.command;

import org.korobko.Operation;
import org.korobko.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Вова on 24.02.2016.
 */
public class CommandExecutor
{
    private static Map<Operation, Command> commandMap;
    static {
        commandMap = new HashMap<>();
        commandMap.put(Operation.INFO,new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
        commandMap.put(Operation.LOGIN,new LoginCommand());
    }
    private CommandExecutor()
    {

    }
    public static final void execute(Operation operation) throws InterruptOperationException
    {

        commandMap.get(operation).execute();
    }
}
