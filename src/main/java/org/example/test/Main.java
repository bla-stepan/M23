package org.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Main {
    @SpringBootApplication
    public static class AccessingDataMongodbApplication implements CommandLineRunner {
        //ДОБАВИМ КОД
        @Autowired
        private UserRepository repository;
        //-----

        public static void main(String[] args) {
            SpringApplication.run(AccessingDataMongodbApplication.class, args);

        }

        @Override
        public void run(String... args) throws Exception {
            System.out.println("Клиенты найдены с помощью функции findAll():");
            //ДОБАВЛЕНО
            repository.deleteAll();

            // Добавляем несколько пользователей
            //repository.save(new User("Tester", "Testerov", "test@test.com", "+1005224"));
            //repository.save(new User("SecondUser", "His latName", "test@test.com", "+795001"));

            //практика добавили два пользователя при помощи метода saveUser
            saveUser("Tester", "Testerov", "test@test.com", "+1005224");
            saveUser("SecondUser", "His latName", "test@test.com", "+795001");
            saveUser("User", "His latName", "test200@test.com", "+795991");
            updateUser("User", "FEDOR");
            deleteUser("FEDOR");


            // Находим всех пользователей
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (User user : repository.findAll()) {
                System.out.println(user);
            }
            System.out.println();

            // Ищем конкретного пользователя
            System.out.println("Customer found with findByFirstName('Tester'):");
            System.out.println("--------------------------------");
            System.out.println(repository.findByFirstName("Tester"));

            System.out.println("Customers found with findByEmail('test@test.com'):");
            System.out.println("--------------------------------");
            for (User User : repository.findByEmail("test@test.com")) {
                System.out.println(User);
            }
        }
        void saveUser(String firstName, String lastName, String email, String phone){
            repository.save(new User(firstName, lastName, email, phone));
        }

        void updateUser(String oldfirstName, String newfirstName){
            User user = repository.findByFirstName(oldfirstName);//ищем по имени (старому имени)
            user.firstName= newfirstName;//меняем астрибут пользователя имя на новое
            repository.save(user);//сохраняем
        }

        void deleteUser(String firstName){
            repository.deletePersonByfirstName(firstName);
        }
    }
}
