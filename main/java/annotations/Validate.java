package annotations;

import java.lang.annotation.*;  // Импортируем классы для работы с аннотациями

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})  // Аннотация может применяться к классам и к другим аннотациям (ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)  // Доступна во время выполнения через reflection
public @interface Validate {  // Объявляем аннотацию Validate
    Class<?>[] value();  // Объявляем обязательный параметр 'value' типа массив классов Class<?>[]. Знак ? означает "любой класс".
    // Этот параметр не имеет значения по умолчанию
}