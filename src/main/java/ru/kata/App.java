package ru.kata;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kata.config.MyConfig;
import ru.kata.entity.User;

public class App
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        System.out.println(communication.getAllUsers());
        System.out.println(communication.save(new User(3L, "James", "Brown", (byte) 26)));
        System.out.println(communication.update(new User(3L, "Thomas", "Shelby", (byte) 26)));
        System.out.println(communication.delete(3L));


    }
}
