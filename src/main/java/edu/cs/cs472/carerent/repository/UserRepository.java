package edu.cs.cs472.carerent.repository;

import edu.cs.cs472.carerent.model.Car;
import edu.cs.cs472.carerent.model.User;

import java.util.List;

public class UserRepository {
    private List<User> userList;
    public UserRepository(List<User> userList){this.userList = userList;}

    public List<User> getUserList() {
        return userList;
    }

    public User addNewUser(User user){
        userList.add(user);
       return  user;
    }
}
