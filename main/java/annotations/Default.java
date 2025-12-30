package annotations;

import java.lang.annotation.*;  // Импортируем необходимые классы для создания аннотаций

@Target({ElementType.TYPE, ElementType.FIELD})  // Указываем, что аннотация может применяться к двум типам элементов: классам/интерфейсам (TYPE) и полям классов (FIELD)
@Retention(RetentionPolicy.RUNTIME)  // Аннотация сохраняется до времени выполнения и доступна через Reflection
public @interface Default {  // Объявляем публичную аннотацию Default
    Class<?> value();  // Объявляем обязательный параметр 'value' типа Class<?>.
    // Вопросительный знак означает любой класс. Этот параметр НЕ имеет значения по умолчанию, поэтому его всегда нужно указывать
}