import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Character, Integer> ROMAN_VALUES = new LinkedHashMap<>();

    static {
        ROMAN_VALUES.put('I', 1);
        ROMAN_VALUES.put('V', 5);
        ROMAN_VALUES.put('X', 10);
        ROMAN_VALUES.put('L', 50);
        ROMAN_VALUES.put('C', 100);
        ROMAN_VALUES.put('D', 500);
        ROMAN_VALUES.put('M', 1000);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String operation) throws Exception {
        String[] values = operation.split(" ");
        String action = values[1];
        boolean isRim = false;
        int a, b;
        String res = "";

        if (values.length > 3)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (values.length < 3) throw new Exception("Строка не является математической операцией");

        // Если оба римские
        if (checkRim(values[0]) && checkRim(values[2])) {
            a = rimToInt(values[0]);
            b = rimToInt(values[2]);
            isRim = true;
            if (action.equals("-") && a < b) throw new Exception("В римской системе нет отрицательных чисел");
        }
        // Если оба арабские
        else if (!checkRim(values[0]) && !checkRim(values[2])) {
            a = Integer.parseInt(values[0]);
            b = Integer.parseInt(values[2]);
            // Если ввели больше 10
            if (a > 10 || b > 10) throw new Exception("Введите число от 1 до 10 включительно");
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
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
        }

        if (isRim) res = intToRim(Integer.parseInt(res));
        return res;
    }

    private static boolean checkRim(String number) {
        for (char c : number.toCharArray()) {
            if (!ROMAN_VALUES.containsKey(c)) {
                return false;
            }
        }
        return true;
    }

    private static int rimToInt(String number) {
        int result = 0;
        int previousValue = 0;

        for (int i = number.length() - 1; i >= 0; i--) {
            int value = ROMAN_VALUES.get(number.charAt(i));

            if (value < previousValue) {
                result -= value;
            } else {
                result += value;
                previousValue = value;
            }
        }

        return result;
    }

    private static String intToRim(int number) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : ROMAN_VALUES.entrySet()) {
            while (number >= entry.getValue()) {
                result.append(entry.getKey());
                number -= entry.getValue();
            }
        }

        return result.toString();
    }
}

