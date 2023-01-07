package ru.elikhanov;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Main.calc(scanner.nextLine()));

    }

    public static String calc(String operation) throws Exception {
        String[] values = operation.split( " ");
        String action= values[1];
        boolean isRIm=false;
        int a,b;
        String res = "";


        if (values.length>3) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (values.length<3) throw new Exception("строка не является математической операцией");

        //если оба римские
        if(Rim.checkRim(values[0]) && Rim.checkRim(values[2])){
            a = Rim.rimToInt(values[0]);
            b = Rim.rimToInt(values[2]);
            isRIm = true;
            if (action.equals("-") && a<b) throw new Exception("в римской системе нет отрицательных чисел");
        }
        //если оба арабские
        else if(!Rim.checkRim(values[0]) && !Rim.checkRim(values[2])){
            a = Integer.parseInt(values[0]);
            b = Integer.parseInt(values[2]);
            //если ввели больше 10
            if (a>10||b>10) throw new Exception("введите другое число");
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }

        switch (action) {
            case "+" -> res = String.valueOf(a + b);
            case "-" -> res = String.valueOf(a - b);
            case "*" -> res = String.valueOf(a * b);
            case "/" -> res = String.valueOf(a / b);
        }

        if (isRIm) res = Rim.intToRim(Integer.parseInt(res));
        return res;
    }

}


class Rim {

    //создаем ArrayList в котором индекс значению будет соответствовать его арабской сс
    static List<String> rimInt = new ArrayList<>();

    static {
        rimInt.add("O");
        rimInt.add("I");
        rimInt.add("II");
        rimInt.add("III");
        rimInt.add("IV");
        rimInt.add("V");
        rimInt.add("VI");
        rimInt.add("VII");
        rimInt.add("VIII");
        rimInt.add("IX");
        rimInt.add("X");
    }

    //метод для проверки является ли строка римском цифрой
     static boolean checkRim(String number) {
        //return rimInt.get(number) != null;
        return rimInt.contains(number);
    }

    //метод для преобразования римской цифры в арабскую
     static int rimToInt(String number) {
        return rimInt.indexOf(number);
    }

    //метод для преобразования арабской цифры в римскую
     static String intToRim(Integer number) {
        return rimInt.get(number);
    }

}