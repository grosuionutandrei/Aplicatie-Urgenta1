package siit.sevices.PeopleAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import siit.db.PeopleDao;
import siit.model.PeopleAdapter;
import siit.model.PeopleDTO;
import siit.sevices.PeopleService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;


@Service
public class PeopleAdapterService {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    PeopleService peopleService;
    @Autowired
    HttpSession session;


    public void PeopleAdapterToPeople(PeopleAdapter peopleAdapter,boolean isIzolat,boolean isHandicap) throws IOException {
        PeopleDTO people = new PeopleDTO();
        people.setName(peopleAdapter.getName());
        people.setState(peopleAdapter.getState());
        people.setObservatii(peopleAdapter.getObservatii());
        people.setDate(getDate(getDate(peopleAdapter)));
        people.setPicture(peopleAdapter.getPicture());
        peopleDao.insertNewPeople(people);
        int id = peopleDao.getAddedPeopleId(people);
        System.out.println(peopleAdapter.getSettlement());
        if(isIzolat && isHandicap){
            peopleDao.insertIntoLocalniciIzolati(id,peopleAdapter);
            peopleDao.insertIntoLocalniciHandicap(id,peopleAdapter);
            peopleDao.insertIntoLocalniciLocalitati(peopleAdapter.getSettlement().getId(),id);
        }else if(isIzolat){
            peopleDao.insertIntoLocalniciIzolati(id,peopleAdapter);
            peopleDao.insertIntoLocalniciLocalitati(peopleAdapter.getSettlement().getId(),id);
        }else if(isHandicap){
            peopleDao.insertIntoLocalniciHandicap(id,peopleAdapter);
            peopleDao.insertIntoLocalniciLocalitati(peopleAdapter.getSettlement().getId(),id);
        }else{
        peopleDao.insertIntoLocalniciLocalitati(peopleAdapter.getSettlement().getId(),id);
        }
    }



    public String getDate(PeopleAdapter peopleAdapter) {
        StringBuilder strb = new StringBuilder();
        for(int i = peopleAdapter.getDate().length()-1;i>=0;i--){
            strb.append(peopleAdapter.getDate().charAt(i));
        }
        return strb.reverse().toString();
    }

    public LocalDate getDate(String date) {
        return LocalDate.parse(date);
    }

    public String name(String name){
        return name;
    }
    public String name (){
       return name();
    }

}
