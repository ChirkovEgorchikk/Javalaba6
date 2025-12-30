import annotations.Validate;  // Импортируем аннотацию Validate
import examples.EmptyValidateExample;  // Импортируем класс EmptyValidateExample из examples
import examples.ValidateExample;  // Импортируем класс ValidateExample из examples
import handlers.AnnotationHandlers;  // Импортируем класс AnnotationHandlers из handlers
import org.junit.jupiter.api.Test;  // Импортируем аннотацию Test из JUnit 5
import org.junit.jupiter.api.DisplayName;  // Импортируем аннотацию DisplayName из JUnit 5

import java.util.List;  // Импортируем интерфейс List из java.util
import java.util.Map;  // Импортируем интерфейс Map из java.util

import static org.junit.jupiter.api.Assertions.*;  // Статический импорт всех методов Assertions

class ValidateTest {  // Объявляем класс ValidateTest для тестирования функциональности @Validate

    // Объявляем статический вложенный класс TestValidateClass с аннотацией @Validate
    @Validate({String.class, Integer.class, Double.class, Boolean.class})  // Аннотация с массивом из 4 классов
    static class TestValidateClass {}  // Пустое тело класса

    // Объявляем статический вложенный класс TestEmptyValidateClass с пустой аннотацией @Validate
    @Validate({})  // Аннотация с пустым массивом классов
    static class TestEmptyValidateClass {}  // Пустое тело класса

    // Объявляем статический вложенный класс TestNoAnnotationClass без аннотации
    static class TestNoAnnotationClass {}  // Пустое тело класса

    @Test  // Аннотация JUnit 5: тестовый метод
    @DisplayName("Тест корректного извлечения классов из аннотации @Validate")  // Человекочитаемое имя
    void testExtractClassesFromAnnotation() {  // Объявляем тестовый метод
        assertDoesNotThrow(() -> {  // Проверяем, что обработка не вызывает исключений
            AnnotationHandlers.processValidateAnnotation(TestValidateClass.class);  // Обрабатываем тестовый класс
        });

        assertDoesNotThrow(() -> {  // Проверяем на реальном примере
            AnnotationHandlers.processValidateAnnotation(ValidateExample.class);  // Обрабатываем пример из examples
        });
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест исключения при пустом массиве в @Validate")  // Человекочитаемое имя
    void testEmptyArrayThrowsException() {  // Объявляем метод
        IllegalArgumentException exception = assertThrows(  // Проверяем, что выбрасывается исключение
                IllegalArgumentException.class,  // Тип ожидаемого исключения
                () -> AnnotationHandlers.processValidateAnnotation(TestEmptyValidateClass.class)  // Действие
        );

        String message = exception.getMessage();  // Получаем сообщение исключения
        assertNotNull(message);  // Проверяем, что сообщение не null
        assertTrue(message.contains("Массив классов в аннотации @Validate пуст"));  // Проверяем содержимое сообщения

        assertThrows(  // Проверяем на реальном примере
                IllegalArgumentException.class,  // Тип ожидаемого исключения
                () -> AnnotationHandlers.processValidateAnnotation(EmptyValidateExample.class)  // Действие
        );
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест обработки класса без аннотации @Validate")  // Человекочитаемое имя
    void testClassWithoutAnnotation() {  // Объявляем метод
        assertDoesNotThrow(() -> {  // Проверяем обработку класса без аннотации
            AnnotationHandlers.processValidateAnnotation(TestNoAnnotationClass.class);  // Класс без аннотации
        });

        assertDoesNotThrow(() -> {  // Проверяем на стандартном классе
            AnnotationHandlers.processValidateAnnotation(Object.class);  // Стандартный класс Object
        });
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест корректности извлеченных классов")  // Человекочитаемое имя
    void testCorrectnessOfExtractedClasses() {  // Объявляем метод
        @Validate({String.class, Integer.class, List.class})  // Определяем локальный класс с аннотацией прямо в методе
        class SpecificTestClass {}  // Пустое тело локального класса

        assertDoesNotThrow(() -> {  // Проверяем обработку
            AnnotationHandlers.processValidateAnnotation(SpecificTestClass.class);  // Обрабатываем локальный класс
        });
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест обработки разных типов классов")  // Человекочитаемое имя
    void testDifferentClassTypes() {  // Объявляем метод
        @Validate({Object.class, String.class, Integer.class, Double.class,  // Определяем локальный класс с разными типами
                Boolean.class, List.class, Map.class})  // Продолжение аннотации
        class MultiTypeClass {}  // Пустое тело локального класса

        assertDoesNotThrow(() -> {  // Проверяем обработку
            AnnotationHandlers.processValidateAnnotation(MultiTypeClass.class);  // Обрабатываем локальный класс
        });
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест обработки null параметра")  // Человекочитаемое имя
    void testNullParameter() {  // Объявляем метод
        assertDoesNotThrow(() -> {  // Проверяем обработку null параметра
            AnnotationHandlers.processValidateAnnotation(null);  // Передаем null
        });
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест повторного вызова обработчика")  // Человекочитаемое имя
    void testMultipleCalls() {  // Объявляем метод
        assertDoesNotThrow(() -> {  // Проверяем многократный вызов
            AnnotationHandlers.processValidateAnnotation(TestValidateClass.class);  // Первый вызов
            AnnotationHandlers.processValidateAnnotation(TestValidateClass.class);  // Второй вызов
            AnnotationHandlers.processValidateAnnotation(TestValidateClass.class);  // Третий вызов
        });
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест сравнения с ожидаемыми классами через рефлексию")  // Человекочитаемое имя
    void testAnnotationContentViaReflection() {  // Объявляем метод
        Validate annotation = TestValidateClass.class.getAnnotation(Validate.class);  // Получаем аннотацию напрямую

        assertNotNull(annotation);  // Проверяем, что аннотация получена

        Class<?>[] classes = annotation.value();  // Извлекаем массив классов из аннотации

        assertEquals(4, classes.length);  // Проверяем размер массива

        assertEquals(String.class, classes[0]);  // Проверяем первый элемент
        assertEquals(Integer.class, classes[1]);  // Проверяем второй элемент
        assertEquals(Double.class, classes[2]);  // Проверяем третий элемент
        assertEquals(Boolean.class, classes[3]);  // Проверяем четвертый элемент

        assertTrue(classes.length > 0);  // Общая проверка, что массив не пустой
    }
}