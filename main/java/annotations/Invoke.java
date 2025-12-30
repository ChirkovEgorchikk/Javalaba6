package annotations;
import java.lang.annotation.*;  // Импорт классов для работы с аннотациями

@Target(ElementType.METHOD)  // Указываем, что эта аннотация может применяться ТОЛЬКО к методам классов
@Retention(RetentionPolicy.RUNTIME)  // Аннотация сохраняется до времени выполнения, чтобы можно было находить и вызывать методы через reflection
public @interface Invoke {  // Объявляем аннотацию Invoke.
    // Это маркерная аннотация - не имеет параметров, используется только для пометки методов
}