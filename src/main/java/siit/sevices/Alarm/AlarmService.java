package siit.sevices.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.AlarmDao;
import siit.db.PeopleDao;
import siit.model.Alarm;


import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AlarmService {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    AlarmDao alarmDao;
    @Autowired
    HttpSession session;

    public Alarm getAlarm(){
        return alarmDao.getAlarm(session.getAttribute("selected_commune").toString());
    }
    public List<Alarm> getAlarms(){
        return alarmDao.getAlarm();
    }

    public void insertAlarm(Boolean alarm){
        alarmDao.insertAlarm(alarm);
    }

    public void update(Boolean alarm){
       alarmDao.updateAlarm(alarm);
    }

    public void insertOrUpdate(Alarm alarm,Boolean alarmed){
        if(alarm==null){
            insertAlarm(alarmed);
        }else{
            update(alarmed);
        }
    }


    public void updatePeopleState(boolean alarm, HttpSession session) {
        String salvat = "salvat";
        String nesalvat = "nesalvat";
        if (alarm) {
            peopleDao.updateAlarm(nesalvat, session.getAttribute("selected_commune").toString());
        } else if (!alarm) {
            peopleDao.updateAlarm(salvat, session.getAttribute("selected_commune").toString());
        }
    }

}
