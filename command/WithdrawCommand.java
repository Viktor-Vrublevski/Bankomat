package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String cod = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(cod);
        while (true) {
            ConsoleHelper.writeMessage("Enter amount");
            int amount = 0;
            try {
                amount = Integer.parseInt(ConsoleHelper.readString());
                if (amount <= 0) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                } else {
                    try {
                        if (manipulator.isAmountAvailable(amount)) {
                            Map<Integer, Integer> map = manipulator.withdrawAmount(amount);
                            TreeMap<Integer, Integer> treeMap = new TreeMap<>((o1, o2) -> o2 - o1);
                            treeMap.putAll(map);
                            for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
                                ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                            }

                            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),
                                    amount, cod));
                            break;
                        } else {
                            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                        }
                    } catch (NotEnoughMoneyException e) {
                        ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    }
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
            }

        }
    }
}
