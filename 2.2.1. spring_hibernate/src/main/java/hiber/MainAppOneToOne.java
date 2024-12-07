package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainAppOneToOne {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car car1 = new Car("BMW", 1234);
      Car car2 = new Car("BMW", 5678);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User4", "Lastname4", "user4@mail.ru");
      user1.setCar(car1);
      user2.setCar(car2);

      car1.setUser(user1);
      car2.setUser(user2);

      carService.add(car1);
      carService.add(car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
         System.out.println(user.getCar());
      }

      User userWithCar = carService.findUserbySeriesAndModel("BMW", 1234);

      System.out.println("Id = "+userWithCar.getId());
      System.out.println("First Name = "+userWithCar.getFirstName());
      System.out.println("Last Name = "+userWithCar.getLastName());
      System.out.println("Email = "+userWithCar.getEmail());
      System.out.println();

      context.close();
   }
}
