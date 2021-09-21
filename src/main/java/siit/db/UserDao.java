package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        String query = "SELECT * FROM USERS";
        return jdbcTemplate.query(query, this::getUser);
    }
    public void delete (int id){
        String delete="Delete from users where id=?";
        jdbcTemplate.update(delete,id);
    }
    public void insertNewUser(String name,String phoneNumber,String rolUser, String password){
        String insert =  "INSERT INTO USERS (NUME,ROL,NUMAR_DE_TELEFON,PASSWORD)VALUES(?,?,?,?)";
        jdbcTemplate.update(insert,name,rolUser,phoneNumber,phoneNumber);
    }


    private User getUser(ResultSet rs, int rowMapper) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("nume"));
        user.setRole(rs.getString("rol"));
        user.setPhoneNumber(rs.getString("numar_de_telefon"));
        user.setPassword(rs.getString("password"));
        return user;
    }


}
