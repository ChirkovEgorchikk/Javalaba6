package examples;

import annotations.Validate;  // Импортируем аннотацию Validate

@Validate({String.class, Integer.class, Double.class})  // Применяем аннотацию @Validate с массивом из трех классов: String.class, Integer.class, Double.class
public class ValidateExample {  // Объявляем публичный класс ValidateExample
    private String data;  // Объявляем приватное поле data типа String

    public ValidateExample(String data) {  // Объявляем публичный конструктор, принимающий параметр data
        this.data = data;  // Присваивает значение параметра data полю this.data
    }

    public String getData() {  // Объявляет публичный метод-геттер getData
        return data;  // Возвращает значение поля data
    }
}

