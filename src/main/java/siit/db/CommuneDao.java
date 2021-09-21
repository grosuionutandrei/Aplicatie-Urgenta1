package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Commune;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommuneDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Commune> viewCommuneList(){
        String query = "select * from comune ";
        return jdbcTemplate.query(query,this::getCommune);
    }
    public Commune viewCommune(String name){
       String query = "select * from comune where name =?";
      return jdbcTemplate.queryForObject(query,this::getCommune,name);
    }
    public void insertNewCommune(String name){
        String insert="INSERT INTO COMUNE(NAME)VALUES(?)";
        jdbcTemplate.update(insert,name);
    }
    public void deleteCommune(int id){
        String delete ="DELETE FROM COMUNE WHERE ID=?";
        jdbcTemplate.update(delete,id);
    }






    private Commune getCommune(ResultSet rs, int rownum) throws SQLException {
        Commune commune = new Commune();
        commune.setId(rs.getInt("id"));
        commune.setName(rs.getString("name"));
        return  commune;



    }
}
