package examples;

import annotations.Invoke;  // Импортируем аннотацию Invoke

public class InvokeExample {  // Объявляем публичный класс InvokeExample
    private boolean method1Executed = false;  // Объявляем приватное поле method1Executed типа boolean, инициализируемое значением false
    private boolean method2Executed = false;  // Объявляем приватное поле method2Executed типа boolean, инициализируемое значением false
    private String result = "";  // Объявляем приватное поле result типа String, инициализируемое пустой строкой ""

    @Invoke  // Применяем аннотацию @Invoke к методу method1(). Это означает, что метод должен автоматически вызываться
    public void method1() {  // Объявляем публичный метод method1 без параметров и возвращаемого значения (void)
        method1Executed = true;  // Устанавливаем значение поля method1Executed в true, отмечая что метод был выполнен
        result += "Method1 ";  // Добавляем строку "Method1 " к текущему значению поля result. Оператор += добавляет строку справа к строке слева
    }

    @Invoke  // Применяем аннотацию @Invoke к методу method2()
    public void method2() {  // Объявляем публичный метод method2 без параметров
        method2Executed = true;  // Устанавливаем значение поля method2Executed в true
        result += "Method2 ";  // Добавляем строку "Method2 " к полю result
    }

    public void method3() {  // Объявляем публичный метод method3 без аннотации @Invoke
        result += "Method3 ";  // Добавляет строку "Method3 " к полю result. Этот метод не будет вызываться автоматически
    }

    public boolean isMethod1Executed() {  // Объявляем публичный метод-геттер isMethod1Executed, возвращающий boolean
        return method1Executed;  // Возвращает текущее значение поля method1Executed
    }

    public boolean isMethod2Executed() {  // Объявляем публичный метод-геттер isMethod2Executed, возвращающий boolean
        return method2Executed;  // Возвращает текущее значение поля method2Executed
    }

    public String getResult() {  // Объявляем публичный метод-геттер getResult, возвращающий String
        return result.trim();  // Возвращает значение поля result, обрезая начальные и конечные пробелы с помощью метода trim()
    }

    public void reset() {  // Объявляем публичный метод reset без параметров для сброса состояния объекта
        method1Executed = false;  // Устанавливает поле method1Executed в false
        method2Executed = false;  // Устанавливает поле method2Executed в false
        result = "";  // Устанавливает поле result в пустую строку ""
    }
}