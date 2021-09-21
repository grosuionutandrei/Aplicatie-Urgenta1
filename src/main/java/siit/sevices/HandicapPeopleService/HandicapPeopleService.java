package siit.sevices.HandicapPeopleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import siit.db.PeopleDao;
import siit.model.HandicapPeople;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class HandicapPeopleService extends AlgorithmsHandicap {
    private SearchableHandicap searchableHandicap;

    public SearchableHandicap getSearchableHandiacap() {
        return searchableHandicap;
    }

    public void setSearchableHandiacap(SearchableHandicap searchableHandicap) {
        this.searchableHandicap = searchableHandicap;
    }

    @Autowired
    PeopleDao peopleDao;
    @Autowired
    @Qualifier("searchByStateHandicap")
    SearchableHandicap searchByState = new SearchByStateHandicap();
    @Autowired
    @Qualifier("searchByNameHandicap")
    SearchableHandicap searchByName = new SearchByNameHandicap();
    @Autowired
    @Qualifier("searchByAgeHandicap")
    SearchableHandicap searchByAgeHandicap = new SearchByAgeHandicap();
    @Autowired
    @Qualifier("searchByAgePerSettlementHandicap")
    SearchablePerSettlementHandicap searchByAgePerSettlement = new SearchByAgePerSettlement();
    @Autowired
    @Qualifier("searchByNamePerSettlementHandicap")
    SearchablePerSettlementHandicap searchByNamePerSettlement = new SearchByNamePerSettlement();
    @Autowired
    @Qualifier("searchByStatePerSettlementHandicap")
    SearchablePerSettlementHandicap searchByStatePerSettlement = new SearchByStatePerSettlement();


    public List<HandicapPeople> displayHandicapPeople(HttpSession session) {
        peopleDao.getHandicapPeople(session.getAttribute("selected_commune").toString()).stream().forEach(e -> System.out.println(e + "handicap"));
        return peopleDao.getHandicapPeople(session.getAttribute("selected_commune").toString());
    }

    public List<HandicapPeople> displayHandicapPeoplePerSettlement(String settlement) {
        return peopleDao.getHandicapPeoplePerSettlement(settlement);
    }


    public List<HandicapPeople> displaySearchedHandicapPeople(String search, HttpSession session) {
        if (search.equals("salvat") || search.equals("nesalvat")) {

            return searchByState.search(search, session);
        } else if (convert(search)) {
            return searchByAgeHandicap.search(search, session);
        } else {
            return searchByName.search(search, session);
        }
    }

    public List<HandicapPeople> displaySearchedHandicapPerSettlement(String search, String settlement) {
        if (convert(search)) {
            return searchByAgePerSettlement.search(search, settlement);
        } else if (search.equals("salvat") || search.equals("nesalvat")) {
            return searchByStatePerSettlement.search(search, settlement);
        } else {
            return searchByNamePerSettlement.search(search, settlement);
        }

    }


    public HandicapPeople displayDetailsPerHandicapPeople(String id, HttpSession session) {
        int id1 = Integer.parseInt(id);
        List<HandicapPeople> handicapPeople = displayHandicapPeople(session);
        int index = index(handicapPeople, 0, handicapPeople.size() - 1, id1);
        return handicapPeople.get(index);
    }

    private int index(List<HandicapPeople> handicapPeople, int start, int end, int value) {
        int mid = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (handicapPeople.get(mid).getId() == value) {
            return mid;
        }
        if (value > handicapPeople.get(mid).getId()) {
            return index(handicapPeople, mid + 1, end, value);
        } else {
            return index(handicapPeople, start, mid - 1, value);
        }
    }

    public void updateHandicapState(String state, String observatii) {
        int id = extractId(state);
        String extractedState = extraxtState(state);
        if (observatii.equals("")) {
            peopleDao.updateStateHandicap(extractedState, id);
        } else {
            peopleDao.updateStateObservatiiHandicap(extractedState, observatii, id);
        }
    }


}
