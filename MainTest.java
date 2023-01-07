package ru.elikhanov;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    @Test
    public void test() throws Exception {
        assertEquals("X",Main.calc("V + V"));
        assertEquals("5",Main.calc("10 / 2"));
        assertEquals("V",Main.calc("X / II"));
        assertEquals("4",Main.calc("3 + 1"));
        assertEquals("II",Main.calc("V - III"));

        Exception e1 = assertThrows(Exception.class,() -> {Main.calc("11 / 3");});
        assertEquals("введите другое число",e1.getMessage());

        Exception e2 = assertThrows(Exception.class,() -> {Main.calc("X / 3");});
        assertEquals("используются одновременно разные системы счисления",e2.getMessage());

        Exception e3 = assertThrows(Exception.class,() -> {Main.calc("X /");});
        assertEquals("строка не является математической операцией",e3.getMessage());

        Exception e4 = assertThrows(Exception.class,() -> {Main.calc("I - III");});
        assertEquals("в римской системе нет отрицательных чисел",e4.getMessage());

        Exception e5 = assertThrows(Exception.class,() -> {Main.calc("1 + 2 + 3");});
        assertEquals("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)",e5.getMessage());
    }
}