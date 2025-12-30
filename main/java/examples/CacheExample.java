package examples;

import annotations.Cache;  // Импортируем аннотацию Cache из пакета annotations

@Cache({"users", "products", "orders"})  // Применяем аннотацию @Cache к классу CacheExample,
// передавая массив из трех строк: "users", "products", "orders" как значение параметра value
public class CacheExample {  // Объявляем публичный класс CacheExample
    private String data;  // Объявляем приватное поле data типа String для хранения данных

    public CacheExample(String data) {  // Объявляем публичный конструктор класса, который принимает один параметр - строку data
        this.data = data;  // Присваиваем значение параметра data полю класса this.data с помощью ключевого слова this
    }

    public String getData() {  // Объявляем публичный метод-геттер для получения значения поля data
        return data;  // Возвращаем значение поля data
    }
}

