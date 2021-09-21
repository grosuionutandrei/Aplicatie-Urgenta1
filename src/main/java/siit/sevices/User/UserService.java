package siit.sevices.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.UserDao;
import siit.model.User;
import siit.model.UsersRole;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    HttpSession session;

    public List<User> allUsers(){
        System.out.println(userDao.getUsers());
        return userDao.getUsers();
    }

    public User getLoggedUser(String user){
        return getUser(this.allUsers(),user);
    }

    public void delete(int id){
        userDao.delete(id);
    }
    public List<String> getRol(){
      return  Arrays.asList(UsersRole.values()).stream().map(e->e.getRol()).collect(Collectors.toList());
    }

    public void  insertNewUser(String name,String phoneNumber,String rol,String password){
        userDao.insertNewUser(name,phoneNumber,rol,phoneNumber);
    }


    private User getUser(List<User> users,String user){
        return users.stream().filter(e->e.getName().equals(user)).findFirst().get();
    }




}
