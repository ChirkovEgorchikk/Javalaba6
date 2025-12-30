package handlers; // Пакет handlers для классов, обрабатывающих аннотации

import annotations.*;  // Импортируем все аннотации из пакета annotations
import java.lang.reflect.Method;  // Импортируем класс Method для работы с методами через reflection
import java.lang.reflect.Field;  // Импортируем класс Field для работы с полями через reflection

public class AnnotationHandlers {  // Объявляем публичный класс AnnotationHandlers

    // Объявляем статический метод invokeAnnotatedMethods, который принимает любой объект (Object) и может выбрасывать исключения (throws Exception)
    public static void invokeAnnotatedMethods(Object obj) throws Exception {
        if (obj == null) {  // Проверяем, не является ли переданный объект null
            throw new IllegalArgumentException("Объект не может быть null");  // Если null, выбрасываем исключение с сообщением
        }

        Class<?> clazz = obj.getClass();  // Получаем объект Class<?> для переданного объекта с помощью метода getClass()
        Method[] methods = clazz.getDeclaredMethods();  // Получаем массив всех объявленных методов в классе с помощью getDeclaredMethods()

        for (Method method : methods) {  // Начинаем цикл for, перебирающий все методы из массива methods
            if (method.isAnnotationPresent(Invoke.class)) {  // Проверяем, присутствует ли аннотация @Invoke на текущем методе
                method.setAccessible(true);  // Устанавливаем доступность метода в true, чтобы можно было вызвать приватные методы
                method.invoke(obj);  // Вызываем метод на объекте obj. invoke() вызывает метод с помощью reflection
            }
        }
    }

    // Объявляем статический метод processDefaultAnnotation, принимающий объект Class<?> и ничего не возвращающий
    public static void processDefaultAnnotation(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Default.class)) {  // Проверяем, присутствует ли аннотация @Default на переданном классе
            Default annotation = clazz.getAnnotation(Default.class);  // Получаем объект аннотации Default с помощью getAnnotation()
            System.out.println("Класс по умолчанию: " + annotation.value().getName());  // Выводим в консоль имя класса из аннотации
        } else {  // Если аннотация @Default отсутствует на классе
            System.out.println("Аннотация @Default не найдена");  // Выводим сообщение об отсутствии аннотации
        }
    }

    // Объявляем статический метод toStringWithAnnotation, принимающий объект и возвращающий String, может выбрасывать IllegalAccessException
    public static String toStringWithAnnotation(Object obj) throws IllegalAccessException {
        if (obj == null) {  // Проверяем, не является ли объект null
            return "null";  // Если null, возвращаем строку "null"
        }

        Class<?> clazz = obj.getClass();  // Получаем объект Class<?> для переданного объекта
        StringBuilder result = new StringBuilder(clazz.getSimpleName() + "{");  // Создаем StringBuilder, начинающийся с имени класса и открывающей фигурной скобки

        boolean classHasToString = clazz.isAnnotationPresent(ToString.class);  // Проверяем, есть ли аннотация @ToString на классе
        ToString.Mode defaultMode = classHasToString ?  // Используем тернарный оператор для определения режима по умолчанию
                clazz.getAnnotation(ToString.class).value() : ToString.Mode.YES;  // Если аннотация есть - берем ее значение, иначе используем YES

        Field[] fields = clazz.getDeclaredFields();  // Получаем массив всех объявленных полей класса
        boolean firstField = true;  // Создаем флаг firstField со значением true для отслеживания первого поля

        for (Field field : fields) {  // Начинаем цикл for-each по всем полям
            field.setAccessible(true);  // Устанавливаем доступность поля в true для доступа к приватным полям

            ToString.Mode fieldMode = defaultMode;  // Начинаем с режима по умолчанию для этого поля
            if (field.isAnnotationPresent(ToString.class)) {  // Проверяем, есть ли аннотация @ToString на текущем поле
                fieldMode = field.getAnnotation(ToString.class).value();  // Если есть, берем значение из аннотации поля
            }

            if (fieldMode == ToString.Mode.YES) {  // Проверяем, должен ли режим поля быть YES (включать в toString)
                if (!firstField) {  // Проверяем, не является ли это поле первым
                    result.append(", ");  // Если не первое, добавляем запятую и пробел для разделения полей
                }
                result.append(field.getName())  // Добавляем имя поля в результат
                        .append("=")  // Добавляем знак равенства
                        .append(field.get(obj));  // Добавляем значение поля, полученное с помощью field.get(obj)
                firstField = false;  // Устанавливаем флаг firstField в false, так как первое поле уже обработано
            }
        }

        result.append("}");  // Добавляем закрывающую фигурную скобку в результат
        return result.toString();  // Преобразуем StringBuilder в String и возвращаем результат
    }

    // Объявляем статический метод processValidateAnnotation, принимающий объект Class<?>
    public static void processValidateAnnotation(Class<?> clazz) {
        if (clazz == null) {  // Проверяем, не является ли переданный класс null
            System.out.println("Ошибка: передан null вместо класса");  // Если null, выводим сообщение об ошибке
            return;  // Завершаем выполнение метода досрочно с помощью return
        }

        if (!clazz.isAnnotationPresent(Validate.class)) {  // Проверяем отсутствие аннотации @Validate на классе
            System.out.println("Аннотация @Validate не найдена");  // Если аннотации нет, выводим сообщение
            return;  // Завершаем выполнение метода
        }

        Validate annotation = clazz.getAnnotation(Validate.class);  // Получаем объект аннотации Validate с класса
        Class<?>[] classes = annotation.value();  // Получаем массив классов из аннотации с помощью метода value()

        if (classes.length == 0) {  // Проверяем, пуст ли массив классов
            throw new IllegalArgumentException("Массив классов в аннотации @Validate пуст");  // Если пуст, выбрасываем исключение
        }

        System.out.println("Классы в аннотации @Validate:");  // Выводим заголовок в консоль
        for (Class<?> c : classes) {  // Начинаем цикл for-each по всем классам в массиве
            System.out.println("  - " + c.getName());  // Для каждого класса выводим его полное имя с отступом
        }
    }

    // Объявляем статический метод processTwoAnnotation, принимающий объект Class<?>
    public static void processTwoAnnotation(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Two.class)) {  // Проверяем наличие аннотации @Two на классе
            Two annotation = clazz.getAnnotation(Two.class);  // Получаем объект аннотации Two
            System.out.println("first: " + annotation.first());  // Выводим значение параметра first из аннотации
            System.out.println("second: " + annotation.second());  // Выводим значение параметра second из аннотации
        } else {  // Если аннотация @Two отсутствует
            System.out.println("Аннотация @Two не найдена");  // Выводим сообщение об отсутствии аннотации
        }
    }

    // Объявляем статический метод processCacheAnnotation, принимающий объект Class<?>
    public static void processCacheAnnotation(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Cache.class)) {  // Проверяем наличие аннотации @Cache на классе
            Cache annotation = clazz.getAnnotation(Cache.class);  // Получаем объект аннотации Cache
            String[] areas = annotation.value();  // Получаем массив строк (областей кэширования) из аннотации

            if (areas.length == 0) {  // Проверяем, пуст ли массив областей
                System.out.println("Список кешируемых областей пуст");  // Если пуст, выводим соответствующее сообщение
            } else {  // Если массив не пустой
                System.out.println("Кешируемые области:");  // Выводим заголовок
                for (String area : areas) {  // Начинаем цикл for-each по всем областям в массиве
                    System.out.println("  - " + area);  // Для каждой области выводим ее название с отступом
                }
            }
        } else {  // Если аннотация @Cache отсутствует
            System.out.println("Аннотация @Cache не найдена");  // Выводим сообщение об отсутствии аннотации
        }
    }
}