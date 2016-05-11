package org.korobko.command;

import org.korobko.exception.InterruptOperationException;

/**
 * Created by Вова on 24.02.2016.
 */
interface Command
{
    void execute() throws InterruptOperationException;
}
