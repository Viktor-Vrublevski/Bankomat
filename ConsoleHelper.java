package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private final static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +
            ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String str = "";
        try {
            str = bis.readLine();
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
        if (str.equalsIgnoreCase("exit")) {
            throw new InterruptOperationException();
        }
        return str;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
       writeMessage(res.getString("choose.currency.code"));
        String currency = null;
        while (true) {
            currency = readString();
            if (currency.matches("[a-z A-Z]{3}")) {
                currency = currency.toUpperCase();
                return currency;
            } else {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }

    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] numbers = null;
        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
            String result = null;

                result = readString();
                int one = 0;
                int two = 0;
                numbers = result.split(" ");
            try {
                one = Integer.parseInt(numbers[0]);
                two = Integer.parseInt(numbers[1]);
                if (one > 0 & two > 0 & numbers.length == 2) break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }

        }
        return numbers;
    }

    // Выбор операции
    public static Operation askOperation() throws InterruptOperationException {
        int number = 0;

        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage("1 - "+ res.getString("operation.INFO")+"\n"+
                    "2 - "+ res.getString("operation.DEPOSIT")+"\n"+
                    "3 - "+ res.getString("operation.WITHDRAW")+"\n"+
                    "4 - "+ res.getString("operation.EXIT"));
            try {
                String str = readString();
                if (str.equals("0")) throw new IllegalArgumentException();
                    return Operation.getAllowableOperationByOrdinal(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                writeMessage("ERROR. Repeat again");
            }

        }
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
