package pack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserManager {

    List<User> users = new ArrayList<>();

    void run(){
        User myself = new User();
        myself.name = "山田太郎";
        myself.gender = "male";
        myself.age = 20;
        this.add(myself);
        User user1 = new User();
        user1.name = "東野圭吾";
        user1.gender = "male";
        user1.age = 62;
        this.add(user1);
        User user2 = new User();
        user2.name = "宮部みゆき";
        user2.gender = "female";
        user2.age = 59;
        this.add(user2);
        User user3 = new User();
        user3.name = "伊坂幸太郎";
        user3.gender = "male";
        user3.age = 49;
        this.add(user3);
        User user4 = new User();
        user4.name = "江國香織";
        user4.gender = "female";
        user4.age = 56;
        this.add(user4);
        User user5 = new User();
        user5.name = "恩田陸";
        user5.gender = "female";
        user5.age = 55;
        this.add(user5);

        /*
        for (User user: find("江國香織", "", 0)){
            print(user);
        }
        */

        Iterator<User> iterator = users.iterator();
        iterator = this.iterator();
        while (iterator.hasNext()){
            print(iterator.next());
        }        
    }

    User create(String name, String gender, Integer age){
        User user = new User();
        user.name = name;
        user.gender = gender;
        user.age = age;
        return user;
    }

    void print(User user){
        System.out.printf("%s %s %d%n", user.name, user.gender, user.age);
    }

    Integer size(){
        return users.size();
    }

    void add(User user){
        users.add(user);
    }

    List<User> find(String name, String gender, Integer age){
        List<User> result = new ArrayList<>();
        for (User user : users){
            if (name.equals(user.name) || gender.equals(user.gender) || age == user.age){
                result.add(user);
            }
        }
        return result;
    }

    /*
    Boolean delete(User user){

    }
    */

    public Iterator<User> iterator(){
        return users.iterator();
    }

    public static void main(String[] args){
        UserManager um = new UserManager();
        um.run();
    }
    
}