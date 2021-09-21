package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.CommuneDao;
import siit.db.SettlementDao;
import siit.model.CommuneSettlements;

@Service
public class CommuneSettlementsService {
    @Autowired
    CommuneDao communeDao;
    @Autowired
    SettlementDao settlementDao;

    public CommuneSettlements getCommuneSettlements(String name){
        CommuneSettlements  cm = new CommuneSettlements();
        cm.setCommune(communeDao.viewCommune(name));
        cm.setSettlementList(settlementDao.dataViewObjectSettlement(name));
        return cm;
    }




}
