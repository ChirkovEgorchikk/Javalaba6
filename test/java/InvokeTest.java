import annotations.Invoke;  // Импортируем аннотацию Invoke из пакета annotations
import examples.InvokeExample;  // Импортируем класс InvokeExample из пакета examples
import handlers.AnnotationHandlers;  // Импортируем класс AnnotationHandlers из пакета handlers
import org.junit.jupiter.api.BeforeEach;  // Импортируем аннотацию BeforeEach из JUnit 5
import org.junit.jupiter.api.Test;  // Импортируем аннотацию Test из JUnit 5
import org.junit.jupiter.api.DisplayName;  // Импортируем аннотацию DisplayName из JUnit 5

import static org.junit.jupiter.api.Assertions.*;  // Статический импорт всех методов класса Assertions из JUnit 5

class InvokeTest {  // Объявляем класс InvokeTest для тестирования функциональности @Invoke

    private InvokeExample testObject;  // Объявляем приватное поле testObject типа InvokeExample для использования во всех тестах

    @BeforeEach  // Аннотация JUnit 5: метод setUp будет выполняться ПЕРЕД КАЖДЫМ тестовым методом
    void setUp() {  // Объявляем метод setUp без параметров и возвращаемого значения
        testObject = new InvokeExample();  // Инициализируем поле testObject новым экземпляром InvokeExample перед каждым тестом
    }

    @Test  // Аннотация JUnit 5: указывает, что метод testInvokeAnnotatedMethods является тестовым
    @DisplayName("Тест вызова методов с аннотацией @Invoke")  // Аннотация JUnit 5: задает человекочитаемое имя теста
    void testInvokeAnnotatedMethods() {  // Объявляем тестовый метод testInvokeAnnotatedMethods
        assertFalse(testObject.isMethod1Executed());  // Проверяем утверждение: метод isMethod1Executed() должен возвращать false (метод1 еще не выполнен)
        assertFalse(testObject.isMethod2Executed());  // Проверяем утверждение: метод isMethod2Executed() должен возвращать false (метод2 еще не выполнен)
        assertEquals("", testObject.getResult());  // Проверяем утверждение: метод getResult() должен возвращать пустую строку (результат пуст)

        assertDoesNotThrow(() -> {  // Проверяем утверждение: следующий блок кода НЕ должен выбрасывать исключений
            AnnotationHandlers.invokeAnnotatedMethods(testObject);  // Вызываем метод invokeAnnotatedMethods на testObject
        });

        assertTrue(testObject.isMethod1Executed());  // Проверяем утверждение: теперь isMethod1Executed() должен возвращать true
        assertTrue(testObject.isMethod2Executed());  // Проверяем утверждение: теперь isMethod2Executed() должен возвращать true

        String result = testObject.getResult();  // Получаем результат выполнения методов
        assertNotNull(result);  // Проверяем утверждение: результат не должен быть null

        // Проверяем содержимое результата
        assertTrue(result.contains("Method1"));  // Проверяем утверждение: результат должен содержать подстроку "Method1"
        assertTrue(result.contains("Method2"));  // Проверяем утверждение: результат должен содержать подстроку "Method2"
        assertFalse(result.contains("Method3"));  // Проверяем утверждение: результат НЕ должен содержать подстроку "Method3" (метод без аннотации)

        // Подсчитываем количество вызовов каждого метода
        int countMethod1 = countOccurrences(result, "Method1");  // Вызываем вспомогательный метод для подсчета вхождений "Method1"
        int countMethod2 = countOccurrences(result, "Method2");  // Вызываем вспомогательный метод для подсчета вхождений "Method2"
        assertEquals(1, countMethod1, "Method1 должен быть вызван 1 раз");  // Проверяем утверждение: countMethod1 должен быть равен 1
        assertEquals(1, countMethod2, "Method2 должен быть вызван 1 раз");  // Проверяем утверждение: countMethod2 должен быть равен 1
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест отсутствия исключений при вызове")  // Человекочитаемое имя теста
    void testNoExceptionsWhenInvoking() {  // Объявляем тестовый метод
        assertDoesNotThrow(() -> {  // Проверяем, что вызов не вызывает исключений
            AnnotationHandlers.invokeAnnotatedMethods(testObject);  // Вызываем тестируемый метод
        });

        assertTrue(testObject.isMethod1Executed());  // Дополнительно проверяем, что состояние изменилось
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест корректности изменения состояния объекта")  // Человекочитаемое имя
    void testObjectStateChangedCorrectly() {  // Объявляем метод
        boolean initialMethod1State = testObject.isMethod1Executed();  // Сохраняем начальное состояние method1
        boolean initialMethod2State = testObject.isMethod2Executed();  // Сохраняем начальное состояние method2
        String initialResult = testObject.getResult();  // Сохраняем начальный результат

        assertDoesNotThrow(() -> {  // Проверяем отсутствие исключений
            AnnotationHandlers.invokeAnnotatedMethods(testObject);  // Вызываем метод
        });

        assertNotEquals(initialMethod1State, testObject.isMethod1Executed());  // Проверяем, что состояние изменилось
        assertNotEquals(initialMethod2State, testObject.isMethod2Executed());  // Проверяем, что состояние изменилось
        assertNotEquals(initialResult, testObject.getResult());  // Проверяем, что результат изменился

        assertTrue(testObject.isMethod1Executed());  // Проверяем новое состояние
        assertTrue(testObject.isMethod2Executed());  // Проверяем новое состояние

        String result = testObject.getResult();  // Получаем результат
        assertTrue(result.contains("Method1"));  // Проверяем содержимое
        assertTrue(result.contains("Method2"));  // Проверяем содержимое
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест многократного вызова аннотированных методов")  // Человекочитаемое имя
    void testMultipleInvocations() {  // Объявляем метод
        assertDoesNotThrow(() -> {  // Проверяем отсутствие исключений
            AnnotationHandlers.invokeAnnotatedMethods(testObject);  // Первый вызов
            String firstResult = testObject.getResult();  // Сохраняем результат после первого вызова

            AnnotationHandlers.invokeAnnotatedMethods(testObject);  // Второй вызов
            String secondResult = testObject.getResult();  // Сохраняем результат после второго вызова

            // Проверяем, что результат стал длиннее
            assertTrue(secondResult.length() > firstResult.length());  // Второй результат должен быть длиннее

            // Подсчитываем количество вызовов
            int countMethod1 = countOccurrences(secondResult, "Method1");  // Считаем "Method1" во втором результате
            int countMethod2 = countOccurrences(secondResult, "Method2");  // Считаем "Method2" во втором результате
            assertEquals(2, countMethod1, "Method1 должен быть вызван 2 раза");  // Проверяем, что 2 раза
            assertEquals(2, countMethod2, "Method2 должен быть вызван 2 раза");  // Проверяем, что 2 раза
        });
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест обработки null объекта")  // Человекочитаемое имя
    void testNullObjectThrowsException() {  // Объявляем метод
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {  // Проверяем, что передача null вызывает исключение
            AnnotationHandlers.invokeAnnotatedMethods(null);  // Передаем null в метод
        });

        assertTrue(exception.getMessage().contains("не может быть null"));  // Проверяем сообщение исключения
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест вызова только аннотированных методов")  // Человекочитаемое имя
    void testOnlyAnnotatedMethodsAreInvoked() {  // Объявляем метод
        class TestClass {  // Определяем локальный внутренний класс прямо в методе
            boolean method1Called = false;  // Поле для отслеживания вызова method1
            boolean method2Called = false;  // Поле для отслеживания вызова method2
            boolean method3Called = false;  // Поле для отслеживания вызова method3

            @Invoke  // Аннотация на method1
            public void method1() { method1Called = true; }  // Метод устанавливает флаг в true

            @Invoke  // Аннотация на method2
            public void method2() { method2Called = true; }  // Метод устанавливает флаг в true

            public void method3() { method3Called = true; }  // Метод БЕЗ аннотации
        }

        TestClass testObj = new TestClass();  // Создаем экземпляр локального класса

        assertDoesNotThrow(() -> {  // Проверяем отсутствие исключений
            AnnotationHandlers.invokeAnnotatedMethods(testObj);  // Вызываем метод
        });

        assertTrue(testObj.method1Called);  // Проверяем, что method1 вызван
        assertTrue(testObj.method2Called);  // Проверяем, что method2 вызван
        assertFalse(testObj.method3Called);  // Проверяем, что method3 НЕ вызван
    }

    @Test  // Еще один тестовый метод
    @DisplayName("Тест сброса состояния и повторного вызова")  // Человекочитаемое имя
    void testResetAndReinvoke() {  // Объявляем метод
        assertDoesNotThrow(() -> {  // Проверяем отсутствие исключений
            AnnotationHandlers.invokeAnnotatedMethods(testObject);  // Первый вызов
            assertTrue(testObject.isMethod1Executed());  // Проверяем, что методы вызваны
            assertTrue(testObject.isMethod2Executed());

            testObject.reset();  // Сбрасываем состояние объекта

            // Проверяем, что состояние сброшено
            assertFalse(testObject.isMethod1Executed());  // Проверяем, что method1 не выполнен
            assertFalse(testObject.isMethod2Executed());  // Проверяем, что method2 не выполнен
            assertEquals("", testObject.getResult());  // Проверяем, что результат пуст

            AnnotationHandlers.invokeAnnotatedMethods(testObject);  // Повторный вызов

            // Проверяем, что методы снова вызваны
            assertTrue(testObject.isMethod1Executed());  // Проверяем, что method1 выполнен
            assertTrue(testObject.isMethod2Executed());  // Проверяем, что method2 выполнен

            String result = testObject.getResult();  // Получаем результат
            assertTrue(result.contains("Method1"));  // Проверяем содержимое
            assertTrue(result.contains("Method2"));  // Проверяем содержимое
        });
    }

    // Вспомогательный приватный метод для подсчета вхождений подстроки
    private int countOccurrences(String text, String substring) {  // Объявляем метод с двумя параметрами
        int count = 0;  // Инициализируем счетчик нулем
        int index = 0;  // Инициализируем индекс поиска нулем
        while ((index = text.indexOf(substring, index)) != -1) {  // Цикл while: ищем подстроку в тексте начиная с index
            count++;  // Увеличиваем счетчик на 1
            index += substring.length();  // Сдвигаем индекс на длину найденной подстроки
        }
        return count;  // Возвращаем общее количество найденных вхождений
    }
}