package siit.sevices.Alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.PeopleDao;
import siit.model.ChartValues;
import siit.model.People;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class InformatiiUtileService {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    HttpSession session;

    public List<ChartValues> chartValuesState(){
        List<ChartValues> chartValues = new LinkedList<>();
        List<People> peoples = peopleDao.getPeoples(session.getAttribute("selected_commune").toString());
        double salvat = (double)countPeoples("salvat",peoples);
        double nesalvat = (double)countPeoples("nesalvat",peoples);
        ChartValues salvati = new ChartValues("Salvati",salvat);
        ChartValues nesalvati = new ChartValues("Nesalvati",nesalvat);
        Collections.addAll(chartValues,salvati,nesalvati);
        return chartValues;
    }
    public List<ChartValues> chartValuesAge(){
        List<ChartValues> chartValues = new LinkedList<>();
        List<People> people =  peopleDao.getPeoples(session.getAttribute("selected_commune").toString());
        people.stream().forEach(e->chartValues.add(new ChartValues("Age",e.getAge())));
        return chartValues;
    }


    private static long countPeoples(String state,List<People>peopleList){
         return peopleList.stream().filter(e->e.getState().equals(state)).count();
    }








}
