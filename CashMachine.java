package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName()+".resources.";

    public static void main(String[] args)  {
      try {
          Locale.setDefault(Locale.ENGLISH);
          Operation operation = null;
          CommandExecutor.execute(Operation.LOGIN);
          do {
              operation = ConsoleHelper.askOperation();
              CommandExecutor.execute(operation);
          } while (!Operation.EXIT.equals(operation));
      }catch (InterruptOperationException e){
          ConsoleHelper.printExitMessage();
      }

    }
}
