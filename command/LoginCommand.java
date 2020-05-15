package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +
            "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String card = "";
        String pin2 = "";
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            card = ConsoleHelper.readString();
            pin2 = ConsoleHelper.readString();
            if (card.matches("[0-9]{12}") & pin2.matches("[0-9]{4}")) {
                try {
                    if (validCreditCards.containsKey(card) & validCreditCards.getString(card).equals(pin2)) {
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
                        break;
                    }
                } catch (MissingResourceException e) {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),card));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
