package com.javarush.task.task26.task2613;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        Operation operation = null;
        switch (i){
            case 1: operation = INFO;
            break;
            case 2: operation = DEPOSIT;
            break;
            case 3: operation = WITHDRAW;
            break;
            case 4: operation = EXIT;
            break;
            default: throw new IllegalArgumentException();
        }
        return operation;
    }
}
