
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Main.calc(scanner.nextLine()));

    }

    public static String calc(String operation) throws Exception {
        String[] values = operation.split(" ");
        String action = values[1];
        boolean isRim = false;
        int a, b;
        String res = "";

        if (values.length > 3)
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (values.length < 3) throw new Exception("строка не является математической операцией");

//если оба римские
        if (Rim.checkRim(values[0]) && Rim.checkRim(values[2])) {
            a = Rim.rimToInt(values[0]);
            b = Rim.rimToInt(values[2]);
            isRim = true;
            if (action.equals("-") && a < b) throw new Exception("в римской системе нет отрицательных чисел");
        }
//если оба арабские
        else if (!Rim.checkRim(values[0]) && !Rim.checkRim(values[2])) {
            a = Integer.parseInt(values[0]);
            b = Integer.parseInt(values[2]);
            //если ввели больше 10
            if (a > 10 || b > 10) {
                isRim = true;
            }
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }


        switch (action) {
            case "+":
                res = String.valueOf(a + b);
                break;
            case "-":
                res = String.valueOf(a - b);
                break;
            case "*":
                res = String.valueOf(a * b);
                break;
            case "/":
                res = String.valueOf(a / b);
                break;
            default:
                throw new Exception("не правильный знак");
        }

        if (isRim) res = Rim.intToRim(Integer.parseInt(res));
        return res;
    }


}

class Rim {
    static Map<Integer, String> rimInt = new HashMap<>();

    static {
        rimInt.put(0, "O");
        rimInt.put(1, "I");
        rimInt.put(2, "II");
        rimInt.put(3, "III");
        rimInt.put(4, "IV");
        rimInt.put(5, "V");
        rimInt.put(6, "VI");
        rimInt.put(7, "VII");
        rimInt.put(8, "VIII");
        rimInt.put(9, "IX");
        rimInt.put(10, "X");
    }

    static boolean checkRim(String number) {
        return rimInt.containsValue(number.toUpperCase());
    }


    static int rimToInt(String number) {
        for (Map.Entry<Integer, String> entry : rimInt.entrySet()) {
            if (entry.getValue().equals(number)) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Invalid Roman numeral");
    }

    static String intToRim(Integer number) {
        if (number == 0) {
            return rimInt.get(0);
        } else if (number < 0 || number > 100) {
            throw new IndexOutOfBoundsException("Invalid number for conversion to Roman numeral");
        }

        StringBuilder romanNumeral = new StringBuilder();

        // Handle hundreds place (C, CC, CCC)
        int hundreds = number / 100;
        for (int i = 0; i < hundreds; i++) {
            romanNumeral.append(rimInt.get(100));
        }

        // Handle tens place (X, XX, XXX, XL, L, LX, LXX, LXXX, XC)
        int tens = (number % 100) / 10;
        if (tens == 9) {
            romanNumeral.append(rimInt.get(10));
            romanNumeral.append(rimInt.get(100));
        } else if (tens >= 5) {
            romanNumeral.append(rimInt.get(50));
            for (int i = 0; i < tens - 5; i++) {
                romanNumeral.append(rimInt.get(10));
            }
        } else if (tens == 4) {
            romanNumeral.append(rimInt.get(10));
            romanNumeral.append(rimInt.get(50));
        } else {
            for (int i = 0; i < tens; i++) {
                romanNumeral.append(rimInt.get(10));
            }
        }

        // Handle ones place (I, II, III, IV, V, VI, VII, VIII, IX)
        int ones = number % 10;
        if (ones == 9) {
            romanNumeral.append(rimInt.get(1));
            romanNumeral.append(rimInt.get(10));
        } else if (ones >= 5) {
            romanNumeral.append(rimInt.get(5));
            for (int i = 0; i < ones - 5; i++) {
                romanNumeral.append(rimInt.get(1));
            }
        } else if (ones == 4) {
            romanNumeral.append(rimInt.get(1));
            romanNumeral.append(rimInt.get(5));
        } else {
            for (int i = 0; i < ones; i++) {
                romanNumeral.append(rimInt.get(1));
            }
        }

        return romanNumeral.toString();
    }




}
