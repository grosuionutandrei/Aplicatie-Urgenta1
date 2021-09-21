package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import siit.model.IsolatedPeople;
import siit.sevices.Isolatedsettlement.*;


import java.util.List;

@Service
public class IsolatedPeopleService extends  Algorithms {

    @Autowired
    @Qualifier("searchByNameIsolated")
    SearcableIsolated searchByName = new SearchByNameIsolated();
    @Autowired
    @Qualifier("searchByAgeIsolated")
    SearcableIsolated getSearchByAge = new SearchByAgeIsolated();
    @Autowired
    @Qualifier("searchByStateIsolated")
    SearcableIsolated getSearchByState = new SearchByStateIsolated();
    @Autowired
    @Qualifier("searchByAgePerSettlementIsolated")
    SearchableBySettlementIsolated getSearchByAgePerSettlement = new SearchByAgePerSettlementIsolated();
    @Autowired
    @Qualifier("searchByNamePerSettlementIsolated")
    SearchableBySettlementIsolated getSearchByNamePerSettlement = new SearchByNamePerSettlementIsolated();
    @Autowired
    @Qualifier("searchByStatePerSettlementIsolated")
    SearchableBySettlementIsolated getSearchByStatePerSettlement = new SearchByStatePerSettlementIsolated();

    public List<IsolatedPeople> getIsolatedPeople(String name) {
        return peopleDao.getIsolatedPeople(session.getAttribute("selected_commune").toString());
    }

    public List<IsolatedPeople> getIsolatedPeoplePerSettlement(String localitate) {
        return peopleDao.getIsolatedPeoplePerSettlement(localitate);
    }

    public IsolatedPeople displaySelectedPeople(String id) {
        int id1 = Integer.parseInt(id);
        List<IsolatedPeople> isolatedPeople = this.getIsolatedPeople(session.getAttribute("selected_commune").toString());
        int index = indexOfIsolatedPeople(isolatedPeople, 0, isolatedPeople.size() - 1, id1);
        return isolatedPeople.get(index);
    }

    private int indexOfIsolatedPeople(List<IsolatedPeople> isolatedPeople, int start, int end, int value) {
        int mid = (start + end) / 2;
        if (isolatedPeople.get(mid).getId() == value) {
            return mid;
        }
        if (start > end) {
            return -1;
        }
        if (value > isolatedPeople.get(mid).getId()) {
            return indexOfIsolatedPeople(isolatedPeople, mid + 1, end, value);
        } else {
            return indexOfIsolatedPeople(isolatedPeople, start, mid - 1, value);
        }
    }

    public List<IsolatedPeople> displaySearchedIsolatedPeople(String search) {
        if (search.equals("salvat") || search.equals("nesalvat")) {
            return getSearchByState.search(search);
        }
        if (convert(search)) {
            return getSearchByAge.search(search);
        }
        return searchByName.search(search);
    }

    public List<IsolatedPeople> displaySearchedIsolatedPeoplePerSettlement(String search, String settlement) {
        if (search.equals("salvat") || search.equals("nesalvat")) {
            return getSearchByStatePerSettlement.search(search, settlement);
        } else if (convert(search)) {
            return getSearchByAgePerSettlement.search(search, settlement);
        } else {
            return getSearchByNamePerSettlement.search(search, settlement);
        }
    }

    private static boolean convert(String name) {
        boolean check = false;
        for (int i = 0; i <= name.length() - 1; i++) {
            if (Character.isDigit(name.charAt(i))) {
                check = true;
            }
        }
        return check;
    }

    public void updateIsolatedPeople(String state, String observatii){
        int id = extractId(state);
            String extractedState = extraxtState(state);
            if(observatii.equals("")){
                peopleDao.updateStateIsolated(extractedState,id);
            }else{peopleDao.updateState(extractedState,observatii,id);}
    }

}
