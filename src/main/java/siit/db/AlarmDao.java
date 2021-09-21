package siit.db;

import org.h2.tools.SimpleResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Alarm;

import javax.servlet.http.HttpSession;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AlarmDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HttpSession session;




    private boolean check2(){
        String query = "select * from Alarm where commune=?";
        String comm = session.getAttribute("selected_commune").toString();
        boolean hasRecord =
                jdbcTemplate
                        .query(query,
                                new Object[] { comm },
                                (ResultSet rs) -> {
                                    if (rs.next()) {
                                        return true;
                                    }
                                    return false;
                                }
                        );
     return hasRecord;
    }

    public Alarm getAlarm(String commune){
        String query = "select * from Alarm where commune=?";
        boolean check = check2();
        if(!check){
            return null;
        }else{
        return jdbcTemplate.queryForObject(query,this::getAlarmDataBase,commune);}
    }
    public List<Alarm> getAlarm(){
        String query = "select * from alarm";
        return jdbcTemplate.query(query,this::getAlarmDataBase);
    }

    public void insertAlarm(Boolean alarm){
        String commune = session.getAttribute("selected_commune").toString();
        String query = "insert into Alarm (commune,value) values(?,?)";
        String value = String.valueOf(alarm);
        jdbcTemplate.update(query,commune,value);
    }

    public void updateAlarm(Boolean alarm){
        String value = String.valueOf(alarm);
        String commune = session.getAttribute("selected_commune").toString();
        String query = "Update alarm set value =? where commune=?";
        jdbcTemplate.update(query,value,commune);
    }

    private Alarm getAlarmDataBase(ResultSet rs, int rowNum) throws SQLException {
        Alarm alarm = new Alarm();
        boolean  value = Boolean.valueOf(rs.getString("value"));
        alarm.setValue(value);
        alarm.setCommune(rs.getString("commune"));
        return alarm;
    }
}
