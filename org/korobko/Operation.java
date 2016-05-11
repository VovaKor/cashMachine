package org.korobko;

/**
 * Created by Вова on 21.02.2016.
 */
public enum Operation
{
    INFO, DEPOSIT, WITHDRAW,EXIT,LOGIN;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        switch (i){
            case 0:throw new IllegalArgumentException("Operation not allowed. Choose new operation");
            case 1:return INFO;
            case 2:return DEPOSIT;
            case 3:return WITHDRAW;
            case 4:return EXIT;
            default:break;
        }
          throw new IllegalArgumentException("Wrong operation code. Reenter = ");
    }
}
