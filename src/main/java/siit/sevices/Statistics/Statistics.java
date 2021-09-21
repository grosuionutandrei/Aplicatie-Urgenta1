package siit.sevices.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.PeopleDao;
import siit.db.TipAlimentDao;
import siit.model.Alimente.Alimente;
import siit.model.ChartValues;
import siit.model.People;
import siit.sevices.Alimente.AlimenteService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class Statistics {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    HttpSession session;
    @Autowired
    AlimenteService alimenteService;

    private Map<Integer,Integer> ageValues(){
        List<People> peoples = peopleDao.getPeoples(session.getAttribute("selected_commune").toString());
        Map<Integer,Integer> values = new HashMap<>();
        for(People people:peoples){
            if(values.containsKey(people.getAge())){
                values.put(people.getAge(),values.get(people.getAge())+1);
            }else{
                values.put(people.getAge(),1);
            }
        }
        return values;
    }

    public List<ChartValues> ageChartValues(){
        Map<Integer,Integer> ageValues = ageValues();
        List<ChartValues> chartValues = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry: ageValues.entrySet()){
            chartValues.add(new ChartValues(String.valueOf(entry.getKey()),entry.getValue()));
        }
        return chartValues;
    }

    public List<Alimente> alimentes (){
        return  alimenteService.getList();
    }





}
