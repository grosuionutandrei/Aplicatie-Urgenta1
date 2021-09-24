package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import siit.db.CommuneDao;
import siit.db.SettlementDao;
import siit.model.Settlement;
import siit.model.Settlementview.UserSettlementView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SettlementService {
    @Autowired
    SettlementDao settlementDao;
    @Autowired
    HttpSession session;


    public List<Settlement> displaySettlements(String commune) {
        return settlementDao.dataViewObjectSettlement(commune);
    }

    public Settlement getSettlement(String localitate, String name) {
        Settlement settlement = new Settlement();
        for (Settlement settelement1 : settlementDao.dataViewObjectSettlement(name)) {
            if (settelement1.getName().equals(localitate)) {
                settlement = settelement1;
            }
        }
        return settlement;
    }
    public List<UserSettlementView> getUserSettlementView(){
        return settlementDao.dataUserSettlementView();
    }

    public Settlement getSettlement(String settlement, List<Settlement> settlementList) {
        Settlement settlement1 = new Settlement();
        for (Settlement sett : settlementList) {
            if (sett.getName().equals(settlement)) {
                settlement1.setName(sett.getName());
                settlement1.setId(sett.getId());
            }
        }
        //System.out.println(settlement1);
        return settlement1;
    }
    public int getSettlementId(String settlementName){
        int id = 0;
        for(Settlement settlement:this.displaySettlements(session.getAttribute("selected_commune").toString())){
            if(settlement.getName().equals(settlementName)){
                id=settlement.getId();
            }
        }
        return id;
    }

    public void insertNewSettlement(String localitate,String comuna){
        settlementDao.insertNewSettlement(localitate,comuna);
    }


}
