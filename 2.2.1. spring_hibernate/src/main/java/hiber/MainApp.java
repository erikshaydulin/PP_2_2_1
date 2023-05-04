package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.addCar(new Car("toyota", 4));
      userService.addCar(new Car("vaz", 2112));
      userService.addCar(new Car("vaz", 2114));
      userService.addCar(new Car("ford focus", 2));

      userService.add(new User("Zaur", "Tregulov", "tregulZaurov@mail.ru", userService.listCars().get(0)));
      userService.add(new User("Hasbik", "usfboec", "ufcuser01@mail.ru", userService.listCars().get(1)));
      userService.add(new User("Elena", "Terminator", "ArnoldSwartz@yandex.ru", userService.listCars().get(2)));
      userService.add(new User("Mark", "Dakaskos", "dakaskMark@mail.ru", userService.listCars().get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserWithThisCar("ford focus", 2));
      System.out.println(userService.getUserWithThisCar("vaz", 2112));

      context.close();
   }
}
