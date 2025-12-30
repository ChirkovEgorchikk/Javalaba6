package examples;  // Пакет examples

import annotations.ToString;  // Импортируем аннотацию ToString

@ToString  // Применяем аннотацию @ToString к классу без указания значения. Будет использовано значение по умолчанию Mode.YES
public class ToStringExample {  // Объявляем публичный класс ToStringExample
    private String name;  // Объявляем приватное поле name типа String. Не имеет аннотации, поэтому наследует режим от класса - YES

    @ToString(ToString.Mode.NO)  // Применяем аннотацию @ToString к полю password с явным указанием значения Mode.NO
    private String password;  // Объявляем приватное поле password типа String. Аннотация указывает не включать это поле в toString

    private int age;  // Объявляем приватное поле age типа int. Не имеет аннотации, наследует Mode.YES от класса

    @ToString(ToString.Mode.YES)  // Применяем аннотацию @ToString к полю email с явным указанием Mode.YES
    private String email;  // Объявляем приватное поле email типа String. Явно указано включать в toString

    public ToStringExample() {}  // Объявляем публичный конструктор по умолчанию без параметров. Пустое тело {}

    public ToStringExample(String name, String password, int age, String email) {  // Объявляем публичный конструктор с четырьмя параметрами
        this.name = name;  // Присваивает значение параметра name полю this.name
        this.password = password;  // Присваивает значение параметра password полю this.password
        this.age = age;  // Присваивает значение параметра age полю this.age
        this.email = email;  // Присваивает значение параметра email полю this.email
    }

    public String getName() { return name; }  // Объявляет геттер getName, возвращающий значение поля name
    public String getPassword() { return password; }  // Объявляет геттер getPassword, возвращающий значение поля password
    public int getAge() { return age; }  // Объявляет геттер getAge, возвращающий значение поля age
    public String getEmail() { return email; }  // Объявляет геттер getEmail, возвращающий значение поля email

    public void setName(String name) { this.name = name; }  // Объявляет сеттер setName, устанавливающий значение поля name
    public void setPassword(String password) { this.password = password; }  // Объявляет сеттер setPassword, устанавливающий значение поля password
    public void setAge(int age) { this.age = age; }  // Объявляет сеттер setAge, устанавливающий значение поля age
    public void setEmail(String email) { this.email = email; }  // Объявляет сеттер setEmail, устанавливающий значение поля email
}