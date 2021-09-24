package siit.sevices.PeopleAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import siit.db.PeopleDao;
import siit.model.PeopleAdapter;
import siit.model.PeopleDTO;
import siit.model.Settlement;
import siit.sevices.PeopleService;
import siit.sevices.SettlementService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;


@Service
public class PeopleAdapterService {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    PeopleService peopleService;
    @Autowired
    HttpSession session;
    @Autowired
    SettlementService settlementService;


    private final Logger LOGGER = Logger.getLogger(PeopleAdapterService.class.getName());



    public void PeopleAdapterToPeople(PeopleAdapter peopleAdapter,boolean isIzolat,boolean isHandicap) throws IOException {
        PeopleDTO people = new PeopleDTO();
        people.setName(peopleAdapter.getName());
        people.setState(peopleAdapter.getState());
        people.setObservatii(peopleAdapter.getObservatii());
        people.setDate(getDate(getDate(peopleAdapter)));
//        people.setPicture(peopleAdapter.getPicture());
//        peopleDao.insertNewPeople1(people);
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

    private byte[] convertFromMultipartToByteArray(MultipartFile part)  {
        byte[] byt = null;
        try{
            String content=part.getContentType();
            String name = part.getName();
            LOGGER.info("Part name " + name +" content type" + content);
             byt = part.getBytes();

        }catch(IOException e){
            LOGGER.info(e.getStackTrace().toString());
        }
        return byt;
     }


     public void setSettlementService(String settlement,PeopleAdapter peopleAdapter){
         List<Settlement> settlements =  settlementService.displaySettlements(session.getAttribute("selected_commune").toString());
         peopleAdapter.setSettlement(settlementService.getSettlement(settlement,settlements));
     }


}
