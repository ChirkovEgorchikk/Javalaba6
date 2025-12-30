package annotations;

import java.lang.annotation.*;  // Импортируем все классы из пакета java.lang.annotation для работы с аннотациями

@Target(ElementType.TYPE)  // Указываем, что эта аннотация может применяться ТОЛЬКО к типам: классам, интерфейсам, enum
@Retention(RetentionPolicy.RUNTIME)  // Указываем, что аннотация будет сохраняться в .class файле и доступна во время выполнения программы через Reflection API
public @interface Cache {  // Объявляем новую аннотацию с именем Cache, доступную всем (public)
    String[] value() default {};  // Объявляем параметр 'value' типа массив строк (String[]).
    // Ключевое слово 'default {}' делает параметр необязательным со значением по умолчанию - пустой массив
}