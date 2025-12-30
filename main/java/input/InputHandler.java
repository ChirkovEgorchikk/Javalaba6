package input;  // Пакет input для классов, обрабатывающих пользовательский ввод

import java.util.Scanner;  // Импортируем класс Scanner для чтения ввода с консоли

public class InputHandler {  // Объявляем публичный класс InputHandler
    private static Scanner scanner = new Scanner(System.in);  // Объявляем статическое поле scanner типа Scanner, инициализируемое новым Scanner для System.in

    // Объявляем статический метод readString, принимающий строку prompt и возвращающий String
    public static String readString(String prompt) {
        System.out.print(prompt);  // Выводим приглашение prompt в консоль без перевода строки (print вместо println)
        String input = scanner.nextLine();  // Читаем строку, введенную пользователем, с помощью scanner.nextLine()

        while (input.trim().isEmpty()) {  // Начинаем цикл while, который выполняется пока введенная строка пуста (после удаления пробелов с помощью trim())
            System.out.println("Ошибка: ввод не может быть пустым");  // Выводим сообщение об ошибке
            System.out.print(prompt);  // Снова выводим приглашение
            input = scanner.nextLine();  // Снова читаем строку от пользователя
        }

        return input;  // Возвращаем валидную непустую строку
    }

    // Объявляем статический метод readInt, принимающий строку prompt и возвращающий int
    public static int readInt(String prompt) {
        while (true) {  // Бесконечный цикл while(true), который будет прерван только при успешном чтении числа
            try {  // Начинаем блок try для отлова исключений
                System.out.print(prompt);  // Выводим приглашение
                String input = scanner.nextLine();  // Читаем строку от пользователя
                return Integer.parseInt(input);  // Пытаемся преобразовать строку в int с помощью Integer.parseInt()
            } catch (NumberFormatException e) {  // Ловим исключение NumberFormatException, если строка не содержит число
                System.out.println("Ошибка: введите целое число");  // Выводим сообщение об ошибке
            }
        }
    }

    // Объявляем статический метод readIntInRange, принимающий строку prompt, минимальное и максимальное значения
    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {  // Бесконечный цикл
            int value = readInt(prompt);  // Вызываем метод readInt для получения целого числа
            if (value >= min && value <= max) {  // Проверяем, находится ли число в заданном диапазоне
                return value;  // Если да, возвращаем число
            }
            System.out.printf("Ошибка: число должно быть от %d до %d\n", min, max);  // Если нет, выводим сообщение об ошибке с форматированием
        }
    }

    // Объявляем статический метод readStrings, принимающий строку prompt и количество строк, возвращающий массив строк
    public static String[] readStrings(String prompt, int count) {
        String[] result = new String[count];  // Создаем массив строк result длиной count
        System.out.println(prompt);  // Выводим общее приглашение с переводом строки

        for (int i = 0; i < count; i++) {  // Цикл for от 0 до count-1 для чтения каждой строки
            System.out.printf("  Строка %d: ", i + 1);  // Выводим нумерованное приглашение для каждой строки с форматированием
            result[i] = scanner.nextLine();  // Читаем строку и сохраняем в массив под индексом i
        }

        return result;  // Возвращаем заполненный массив строк
    }

    // Объявляем статический метод readEmail, принимающий строку prompt и возвращающий String
    public static String readEmail(String prompt) {
        while (true) {  // Бесконечный цикл
            String email = readString(prompt);  // Вызываем readString для получения непустой строки
            if (email.contains("@") && email.contains(".")) {  // Проверяем, содержит ли строка символы @ и .
                return email;  // Если содержит, возвращаем email
            }
            System.out.println("Ошибка: введите корректный email (должен содержать @ и .)");  // Если не содержит, выводим сообщение об ошибке
        }
    }

    // Объявляем статический метод readAge, принимающий строку prompt и возвращающий int
    public static int readAge(String prompt) {
        while (true) {  // Бесконечный цикл
            int age = readInt(prompt);  // Вызываем readInt для получения целого числа
            if (age > 0 && age <= 150) {  // Проверяем, находится ли возраст в разумном диапазоне от 1 до 150
                return age;  // Если да, возвращаем возраст
            }
            System.out.println("Ошибка: возраст должен быть от 1 до 150");  // Если нет, выводим сообщение об ошибке
        }
    }

    // Объявляем статический метод confirm, принимающий строку prompt и возвращающий boolean
    public static boolean confirm(String prompt) {
        System.out.print(prompt + " (да/нет): ");  // Выводим приглашение с вариантами ответа
        String input = scanner.nextLine().toLowerCase();  // Читаем строку и преобразуем в нижний регистр для сравнения

        // Цикл while, который проверяет, является ли ввод одним из допустимых ответов
        while (!input.equals("да") && !input.equals("нет") &&
                !input.equals("yes") && !input.equals("no") &&
                !input.equals("д") && !input.equals("н") &&
                !input.equals("y") && !input.equals("n")) {
            System.out.println("Ошибка: введите 'да' или 'нет'");  // Выводим сообщение об ошибке
            System.out.print(prompt + " (да/нет): ");  // Повторяем приглашение
            input = scanner.nextLine().toLowerCase();  // Читаем строку снова
        }

        // Возвращаем true, если ввод соответствует положительному ответу
        return input.equals("да") || input.equals("yes") ||
                input.equals("д") || input.equals("y");
    }
}