package examples;  // Пакет examples для примеров

import annotations.Default;  // Импортируем аннотацию Default из пакета annotations

@Default(String.class)  // Применяем аннотацию @Default к классу DefaultExample, передавая String.class как значение параметра value
public class DefaultExample {  // Объявляем публичный класс DefaultExample
    @Default(Integer.class)  // Применяем аннотацию @Default к полю number, передавая Integer.class как значение параметра value
    private int number;  // Объявляем приватное поле number типа int

    public DefaultExample(int number) {  // Объявляем публичный конструктор, принимающий параметр number типа int
        this.number = number;  // Присваиваем значение параметра полю класса this.number
    }

    public int getNumber() {  // Объявляем публичный метод-геттер для получения значения поля number
        return number;  // Возвращаем значение поля number
    }
}