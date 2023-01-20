package lk.ise.pos.DB;

import lk.ise.pos.entity.Customer;
import lk.ise.pos.entity.Item;
import lk.ise.pos.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Arrays;


public class Database {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();

    static {
        users.add(new User("Linda",encryptPassword("1234")));
        users.add(new User("Anna",encryptPassword("0000")));
        users.add(new User("Tom",encryptPassword("4321")));

        items.add(new Item("D001","Description 1",25,2500));
        items.add(new Item("D002","Description 2",34,4570));
        items.add(new Item("D003","Description 3",20,3250));
        items.add(new Item("D004","Description 4",30,5000));

        customers.add(new Customer("C001","Lahiru","Galle",55000));
        customers.add(new Customer("C002","Kavindu","Mathara",30000));
        customers.add(new Customer("C003","Rasindu","Colombo",45000));
        customers.add(new Customer("C004","Saman","Panadura",90000));
        customers.add(new Customer("C005","Daham","Kaluthara",70000));

    }

    // Encrypt Password
    private static String encryptPassword(String rowPassword){
        return BCrypt.hashpw(rowPassword,BCrypt.gensalt());
    }

}
