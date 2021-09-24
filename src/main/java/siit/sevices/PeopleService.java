package siit.sevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import siit.db.PeopleDao;
import siit.model.People;
import siit.model.PeopleState;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


@Service
public class PeopleService {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    HttpSession session;
    @Autowired
    @Qualifier("searchByAgePerSettlement")
    SearchableBySettlement searchableByAgePerSettlement = new SearchByAgePerSettlement();
    @Autowired
    @Qualifier("searchByNamePerSettlement")
    SearchableBySettlement searchableByNamePerSettlement = new SearchByNamePerSettlement();
    @Autowired
    @Qualifier("searchByStatePerSettlement")
    SearchableBySettlement searchableByStatePerSettlement = new SearchByStatePerSettlement();
    @Autowired
    @Qualifier("searchByName")
    Searchable searchable = new SearchByName();
    @Autowired
    @Qualifier("searchByAge")
    Searchable searchableByAge = new SearchByAge();
    @Autowired
    @Qualifier("searchByState")
    Searchable searchableByState = new SearchByState();



    public List<People> getPeoples(HttpSession session) {
        return peopleDao.getPeoples(session.getAttribute("selected_commune").toString());
    }
    public List<People> getPeoples1(HttpSession session){
        return peopleDao.getPeoples(session.getAttribute("selected_commune").toString());
    }

    public List<PeopleState> getState() {
        return Arrays.asList(PeopleState.values());
    }

    public List<People> getPeoplesPerSettlement(String localitate) {
        return peopleDao.getPeoplesPersettlement(localitate);
    }

    public List<People> getSearchResultForSettlement(String name, String settlement) {
        if (name.equals("salvat") || name.equals("nesalvat")) {
            return searchableByStatePerSettlement.search(name, settlement);
        } else if (convert(name)) {
            return searchableByAgePerSettlement.search(name, settlement);
        } else {
            return searchableByNamePerSettlement.search(name, settlement);
        }
    }

    public List<People> getSelectedPeoples(String name) {
        if (name.equals("salvat") || name.equals("nesalvat")) {
            return searchableByState.search(name);
        }
        if (convert(name)) {
            return searchableByAge.search(name);
        }
        return searchable.search(name);
    }


    public People details(String id) {
        int peopleId = Integer.parseInt(id);
       List<People> peopleList = peopleDao.getPeoples(session.getAttribute("selected_commune").toString());
        quickSortDetails(peopleList, 0, peopleList.size() - 1);
        peopleList.stream().forEach(e-> System.out.println(e + " details"));
        int peopleIndex = BinarrySearchDetails(peopleList, peopleId, 0, peopleList.size() - 1);

        return peopleList.get(peopleIndex);
    }

    public void deletePeople(int localniId){
        peopleDao.deleteFromIsolated(localniId);
        peopleDao.deleteFromHandicap(localniId);
        peopleDao.deleteFromLocalniciLocalitati(localniId);
        peopleDao.deleteFromLocalnici(localniId);
    }

    private static void swap(List<People> swap, int index1, int index2) {
        People temp = new People();
        temp.setName(swap.get(index2).getName());
        temp.setId(swap.get(index2).getId());
        temp.setAge1(swap.get(index2).getAge());
        temp.setState(swap.get(index2).getState());
        temp.setObservatii(swap.get(index2).getObservatii());
        temp.setImage(swap.get(index2).getImage());
        swap.set(index2, swap.get(index1));
        swap.set(index1, temp);
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

    private static void quickSortDetails(List<People> people, int start, int end) {
        if (start < end) {
            int piv = getPivotDetails(people, start, end);
            quickSortDetails(people, start, piv - 1);
            quickSortDetails(people, piv + 1, end);
        }
    }


    private static int getPivotDetails(List<People> people, int start, int end) {
        People piv = new People();
        piv.setName(people.get(end).getName());
        piv.setId(people.get(end).getId());
        piv.setAge1(people.get(end).getAge());
        piv.setState(people.get(end).getState());
        piv.setObservatii(people.get(end).getObservatii());
        piv.setImage(people.get(end).getImage());

        int j = start - 1;
        for (int i = start; i <= end - 1; i++) {
            if (people.get(i).getId() < piv.getId()) {
                j++;
                swap(people, j, i);
            }
        }
        swap(people, j + 1, end);
        return j + 1;
    }

    private static int BinarrySearchDetails(List<People> sorted, int str, int start, int end) {
        int mid = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (str == sorted.get(mid).getId()) {
            return mid;
        }
        if (str < sorted.get(mid).getId()) {
            return BinarrySearchDetails(sorted, str, start, mid - 1);
        }
        return BinarrySearchDetails(sorted, str, mid + 1, end);
    }

    public void updatePeople(String state, String observatii ){
        int id = extractId(state);
        String extractedState = extraxtState(state);
        if(observatii.equals("")){
            peopleDao.updateState(extractedState,id);
        }else{peopleDao.updateState(extractedState,observatii,id);}

    }

    private int extractId(String state){
        StringBuilder strb = new StringBuilder();
        for(int i = 0;i<=state.length()-1;i++){
            if(Character.isDigit(state.charAt(i))){
                for(;Character.isDigit(state.charAt(i));i++){
                    strb.append(state.charAt(i));
                    if(i==state.length()-1){
                        break;
                    }
                }
            }
        }
        String id = strb.toString();
        int id1 = Integer.parseInt(id);
        return  id1;
    }
    private String extraxtState(String state){
        StringBuilder strb= new StringBuilder();
        for(int i = 0;i<=state.length()-1;i++){
            if(Character.isLetter(state.charAt(i))){
                for(;Character.isLetter(state.charAt(i));i++){
                    strb.append(state.charAt(i));
                    if(i==state.length()-1){
                        break;
                    }
                }
            }
        }
        String extractedState = strb.toString();
        return extractedState;
    }

}
