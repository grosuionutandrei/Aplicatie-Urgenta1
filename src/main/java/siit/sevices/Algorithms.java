package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import siit.db.PeopleDao;
import siit.model.IsolatedPeople;
import siit.model.People;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class Algorithms {
    @Autowired
    PeopleDao peopleDao;
    @Autowired
    HttpSession session;


    public int getEndIndex(List<?> elements) {
        return elements.size() - 1;
    }

    public void swap(List<IsolatedPeople> swap, int index1, int index2) {
        IsolatedPeople temp = new IsolatedPeople();
        temp.setName(swap.get(index2).getName());
        temp.setId(swap.get(index2).getId());
        temp.setAge1(swap.get(index2).getAge());
        temp.setState(swap.get(index2).getState());
        temp.setMap(swap.get(index2).getMap());
        temp.setMijlocDeAcces(swap.get(index2).getMijlocDeAcces());
        swap.set(index2, swap.get(index1));
        swap.set(index1, temp);
    }

    public boolean contain(String[] V, String name) {
        for (String str : V) {
            if (str.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean hashCodeComparison(Object str, Object str1) {
        return str.hashCode() == str1.hashCode();
    }

    public int extractId(String state) {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i <= state.length() - 1; i++) {
            if (Character.isDigit(state.charAt(i))) {
                for (; Character.isDigit(state.charAt(i)); i++) {
                    strb.append(state.charAt(i));
                    if (i == state.length() - 1) {
                        break;
                    }
                }
            }
        }
        String id = strb.toString();
        int id1 = Integer.parseInt(id);
        return id1;
    }

    public String extraxtState(String state) {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i <= state.length() - 1; i++) {
            if (Character.isLetter(state.charAt(i))) {
                for (; Character.isLetter(state.charAt(i)); i++) {
                    strb.append(state.charAt(i));
                    if (i == state.length() - 1) {
                        break;
                    }
                }
            }
        }
        String extractedState = strb.toString();
        return extractedState;
    }


}
