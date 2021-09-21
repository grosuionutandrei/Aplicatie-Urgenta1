package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.CommuneDao;
import siit.model.Commune;

import java.util.List;

@Service
public class ComunneService {
    private   String tempString;
    public String getTempString() {
        return tempString;
    }
    public void setTempString(String tempString) {
        this.tempString = tempString;
    }


   @Autowired
    CommuneDao communeDao;
    public List<Commune> viewModelCommune(){
        System.out.println(communeDao.viewCommuneList());
        return communeDao.viewCommuneList();
    }
    public Commune getCommune(String name){
        Commune commune = new Commune();
        commune = this.viewModelCommune().stream().filter(n->n.getName().equals(name)).findFirst().get();
        return commune;
    }

    public void insertNewCommune(String name){
        communeDao.insertNewCommune(name);
    }
    public void delete(int  id){
        communeDao.deleteCommune(id);
    }




}
