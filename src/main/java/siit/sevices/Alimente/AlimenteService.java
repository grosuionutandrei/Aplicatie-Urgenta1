package siit.sevices.Alimente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import siit.db.CommuneDao;
import siit.db.TipAlimentDao;
import siit.model.Alimente.Alimente;
import siit.model.Alimente.Apa;
import siit.model.Alimente.PerecheNumeInteger;
import siit.model.Alimente.TipAliment;
import siit.model.Commune;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.logging.Logger;

@Service
public class AlimenteService {
    @Autowired
    TipAlimentDao tipAlimentDao;
    @Autowired
    HttpSession session;
    @Autowired
    CommuneDao communeDao;
    @Autowired
    @Qualifier("aliments")
    Surviveable aliments = new AlimentsNecessary();
    @Autowired
    @Qualifier("water")
    WaterSupplies water = new WaterNecessary();
    private Logger LOGGER = Logger.getLogger(aliments.getClass().getName());


    public List<Alimente> getList() {
        Commune commune = communeDao.viewCommune(session.getAttribute("selected_commune").toString());
        return tipAlimentDao.getAliments(commune.getId());
    }

    public List<PerecheNumeInteger> displayWaterNecessary(List<Apa> apa) {
        Map<Apa, Integer> waterNecessary = this.water.getNecessaryWater(apa);
        List<PerecheNumeInteger> pairs = new ArrayList<>();
        PerecheNumeInteger temp = new PerecheNumeInteger();
        for (Map.Entry<Apa, Integer> water : waterNecessary.entrySet()) {
            temp.setName(water.getKey().getName());
            temp.setValue(water.getValue());

            pairs.add(temp);
        }
        pairs.add(temp);

        System.out.println(pairs);
        return pairs;
    }

    public List<PerecheNumeInteger> displayFoodNecessary(List<Alimente> aliments, Integer days) {
        Map<Alimente, Integer> necessaryAliments = this.aliments.getNecessaryProducts(aliments);
        List<PerecheNumeInteger> pairs = new ArrayList<>();
        for (Map.Entry<Alimente, Integer> aliment : necessaryAliments.entrySet()) {
            LOGGER.info(aliment.toString());
            PerecheNumeInteger temp = new PerecheNumeInteger();
            if (aliment.getKey() instanceof TipAliment) {
                temp.setName(((TipAliment) aliment.getKey()).getTipAliment());
                temp.setValue(aliment.getValue());
            }
            pairs.add(temp);
        }
        if (days > 1) {
            numberAlimentsPerDays(pairs, days);
        }
        System.out.println(pairs);
        return pairs;
    }

    public List<PerecheNumeInteger> totalAlimentsNecessary(List<PerecheNumeInteger> aliments, Integer days, int personsNumber) {
        List<PerecheNumeInteger> temp = new ArrayList<>();
        for(PerecheNumeInteger perecheNumeInteger:aliments){
            temp.add(new PerecheNumeInteger(perecheNumeInteger.getName(),perecheNumeInteger.getValue()));
        }
        temp.stream().forEach(e->e.setValue(e.getValue()*personsNumber));
        temp.stream().forEach(e->LOGGER.info(" " + e.getName() + " " + e.getValue()));
        return temp;
    }


    private void numberAlimentsPerDays(List<PerecheNumeInteger> aliments, Integer days) {
        for (PerecheNumeInteger perecheNumeInteger : aliments) {
            perecheNumeInteger.setValue(perecheNumeInteger.getValue() * days);
        }
    }

    public List<Integer> displayDays() {
        Integer[] numbers = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 25, 30};
        return Arrays.asList(numbers);
    }

    public void deleteAliment(int alimentId){
        tipAlimentDao.deleteAliment(alimentId);
    }
    public void updateAliment(int alimentId,int quantity){
        tipAlimentDao.updateAlimenteCantitate(alimentId,quantity);
    }

    public void  addNewAliment(TipAliment tipAliment){
        Commune commune = communeDao.viewCommuneList().stream()
                          .filter(e->e.getName().equals(session.getAttribute("selected_commune").toString()))
                          .findFirst().get();
        System.out.println(tipAliment);
        tipAlimentDao.insertNewAliment(tipAliment,commune.getId());
    }


}
