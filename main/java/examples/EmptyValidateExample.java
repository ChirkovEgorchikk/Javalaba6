package examples;

import annotations.Validate;  // Импортируем аннотацию Validate

@Validate({})  // Применяем аннотацию @Validate с пустым массивом классов. Фигурные скобки {} создают пустой массив
public class EmptyValidateExample {  // Объявляем публичный класс EmptyValidateExample
    // Пустой класс для тестирования обработки аннотации @Validate с пустым массивом. Класс не содержит полей и методов
}