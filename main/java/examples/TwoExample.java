package examples;

import annotations.Two;  // Импортируем аннотацию Two

@Two(first = "Пример", second = 42)  // Применяем аннотацию @Two к классу, указывая оба обязательных параметра: first="Пример" и second=42
public class TwoExample {  // Объявляем публичный класс TwoExample
    private String description;  // Объявляем приватное поле description типа String

    public TwoExample(String description) {  // Объявляем публичный конструктор, принимающий один параметр description
        this.description = description;  // Присваивает значение параметра description полю this.description
    }

    public String getDescription() {  // Объявляет публичный метод-геттер getDescription
        return description;  // Возвращает значение поля description
    }
}