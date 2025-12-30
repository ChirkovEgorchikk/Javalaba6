import input.InputHandler;
import examples.*;
import handlers.AnnotationHandlers;
import java.util.Scanner;
import examples.EmptyValidateExample;
import examples.EmptyCacheExample;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ДЕМОНСТРАЦИЯ АННОТАЦИЙ ===");

        boolean exit = false;

        while (!exit) {
            printMenu();
            int choice = InputHandler.readIntInRange("Выберите действие (0-7): ", 0, 7);

            switch (choice) {
                case 1:
                    demonstrateInvoke();
                    break;
                case 2:
                    demonstrateDefault();
                    break;
                case 3:
                    demonstrateToString();
                    break;
                case 4:
                    demonstrateValidate();
                    break;
                case 5:
                    demonstrateTwo();
                    break;
                case 6:
                    demonstrateCache();
                    break;
                case 7:
                    runAllDemonstrations();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Выход из программы.");
                    break;
            }

            if (!exit) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Демонстрация @Invoke");
        System.out.println("2. Демонстрация @Default");
        System.out.println("3. Демонстрация @ToString");
        System.out.println("4. Демонстрация @Validate");
        System.out.println("5. Демонстрация @Two");
        System.out.println("6. Демонстрация @Cache");
        System.out.println("7. Запустить все демонстрации");
        System.out.println("0. Выход");
        System.out.println("=============");
    }

    private static void demonstrateInvoke() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ @Invoke ===");

        InvokeExample example = new InvokeExample();
        System.out.println("Состояние до вызова методов:");
        System.out.println("  method1Executed: " + example.isMethod1Executed());
        System.out.println("  method2Executed: " + example.isMethod2Executed());
        System.out.println("  result: " + example.getResult());

        try {
            System.out.println("\nВызываем методы с аннотацией @Invoke...");
            AnnotationHandlers.invokeAnnotatedMethods(example);

            System.out.println("Состояние после вызова методов:");
            System.out.println("  method1Executed: " + example.isMethod1Executed());
            System.out.println("  method2Executed: " + example.isMethod2Executed());
            System.out.println("  result: " + example.getResult());

            example.reset();
            System.out.println("Состояние сброшено");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void demonstrateDefault() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ @Default ===");

        System.out.println("Аннотация на классе DefaultExample:");
        AnnotationHandlers.processDefaultAnnotation(DefaultExample.class);

        DefaultExample example = new DefaultExample(42);
        System.out.println("Создан объект с number = " + example.getNumber());
    }

    private static void demonstrateToString() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ @ToString ===");

        // Используем прямой Scanner для гарантированного ввода
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Введите данные для объекта ToStringExample:");

        // Ввод имени
        System.out.print("Имя: ");
        String name = "";
        while (name.trim().isEmpty()) {
            name = inputScanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.print("Имя не может быть пустым. Введите имя: ");
            }
        }

        // Ввод пароля
        System.out.print("Пароль: ");
        String password = "";
        while (password.trim().isEmpty()) {
            password = inputScanner.nextLine();
            if (password.trim().isEmpty()) {
                System.out.print("Пароль не может быть пустым. Введите пароль: ");
            }
        }

        // Ввод возраста с проверкой
        int age = 0;
        while (age <= 0 || age > 150) {
            System.out.print("Возраст: ");
            try {
                age = Integer.parseInt(inputScanner.nextLine());
                if (age <= 0 || age > 150) {
                    System.out.println("Ошибка: возраст должен быть от 1 до 150");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число");
            }
        }

        // Ввод email с проверкой
        System.out.print("Email: ");
        String email = "";
        while (email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
            email = inputScanner.nextLine();
            if (email.trim().isEmpty()) {
                System.out.print("Email не может быть пустым. Введите email: ");
            } else if (!email.contains("@") || !email.contains(".")) {
                System.out.print("Некорректный email. Должен содержать @ и . Введите email: ");
            }
        }

        // Создаем объект с введенными данными
        ToStringExample example = new ToStringExample(name, password, age, email);

        System.out.println("\nСоздан объект:");
        System.out.println("  name: " + example.getName());
        System.out.println("  password: " + example.getPassword());
        System.out.println("  age: " + example.getAge());
        System.out.println("  email: " + example.getEmail());

        try {
            System.out.println("\nСтроковое представление с учетом аннотации @ToString:");
            String result = AnnotationHandlers.toStringWithAnnotation(example);
            System.out.println(result);

            System.out.println("\nПоле password скрыто: " + !result.contains(password));

            // Опционально: предложить изменить данные
            System.out.print("\nХотите изменить данные? (да/нет): ");
            String answer = inputScanner.nextLine().toLowerCase();

            if (answer.equals("да") || answer.equals("д") || answer.equals("yes") || answer.equals("y")) {
                System.out.print("Новое имя: ");
                String newName = inputScanner.nextLine();
                if (!newName.trim().isEmpty()) {
                    example.setName(newName);
                }

                System.out.print("Новый возраст (1-150): ");
                try {
                    String newAgeStr = inputScanner.nextLine();
                    if (!newAgeStr.trim().isEmpty()) {
                        int newAge = Integer.parseInt(newAgeStr);
                        if (newAge > 0 && newAge <= 150) {
                            example.setAge(newAge);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Игнорируем некорректный ввод
                }

                String newResult = AnnotationHandlers.toStringWithAnnotation(example);
                System.out.println("Обновленное строковое представление:");
                System.out.println(newResult);
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demonstrateValidate() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ @Validate ===");

        System.out.println("Аннотация на классе ValidateExample:");
        try {
            AnnotationHandlers.processValidateAnnotation(ValidateExample.class);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nПопытка обработать класс с пустым массивом:");
        try {
            AnnotationHandlers.processValidateAnnotation(EmptyValidateExample.class);
        } catch (IllegalArgumentException e) {
            System.err.println("Ожидаемая ошибка: " + e.getMessage());
        }

        ValidateExample example = new ValidateExample("Тестовые данные");
        System.out.println("Создан объект с данными: " + example.getData());
    }

    private static void demonstrateTwo() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ @Two ===");

        System.out.println("Аннотация на классе TwoExample:");
        AnnotationHandlers.processTwoAnnotation(TwoExample.class);

        TwoExample example = new TwoExample("Описание объекта");
        System.out.println("Создан объект с описанием: " + example.getDescription());
    }

    private static void demonstrateCache() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ @Cache ===");

        System.out.println("Аннотация на классе CacheExample:");
        AnnotationHandlers.processCacheAnnotation(CacheExample.class);

        System.out.println("\nАннотация на классе EmptyCacheExample:");
        AnnotationHandlers.processCacheAnnotation(EmptyCacheExample.class);

        CacheExample example = new CacheExample("Кэшируемые данные");
        System.out.println("Создан объект с данными: " + example.getData());
    }

    private static void runAllDemonstrations() {
        System.out.println("\n=== ЗАПУСК ВСЕХ ДЕМОНСТРАЦИЙ ===\n");

        demonstrateInvoke();
        demonstrateDefault();
        demonstrateToString();
        demonstrateValidate();
        demonstrateTwo();
        demonstrateCache();

        System.out.println("\n=== ВСЕ ДЕМОНСТРАЦИИ ЗАВЕРШЕНЫ ===");
    }
}