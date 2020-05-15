package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;


class DepositCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle( CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String cod = ConsoleHelper.askCurrencyCode();
        String[] numbers = ConsoleHelper.getValidTwoDigits(cod);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(cod);
       try {
           int one = Integer.parseInt(numbers[0]);
           int two = Integer.parseInt(numbers[1]);
            manipulator.addAmount(one, two);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),one*two,cod));
       }catch (NumberFormatException e){
           ConsoleHelper.writeMessage(res.getString("invalid.data"));
       }

    }
}
