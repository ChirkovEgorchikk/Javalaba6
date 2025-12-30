package annotations;

import java.lang.annotation.*;  // Импортируем классы для создания аннотаций

@Target({ElementType.TYPE, ElementType.FIELD})  // Аннотация может применяться к классам/интерфейсам и к полям классов
@Retention(RetentionPolicy.RUNTIME)  // Доступна во время выполнения для анализа через reflection
public @interface ToString {  // Объявляем аннотацию ToString
    Mode value() default Mode.YES;  // Объявляем параметр 'value' типа Mode (перечисление ниже) со значением по умолчанию Mode.YES.
    // Если аннотация используется без указания значения, будет использоваться YES

    enum Mode {  // Объявляем вложенное перечисление (enum) прямо внутри аннотации
        YES, NO  // Два возможных значения перечисления: YES (включать в toString) и NO (не включать в toString)
    }
}