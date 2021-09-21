package siit.sevices.HandicapPeopleService;

import org.springframework.beans.factory.annotation.Autowired;
import siit.model.HandicapPeople;
import siit.sevices.ComunneService;


import javax.servlet.http.HttpSession;
import java.util.List;

public class AlgorithmsHandicap  {

        @Autowired
        HandicapPeopleService handicapPeopleService;
        @Autowired
        HttpSession session;
        protected  int getEndIndex(List<?> elements) {
            return elements.size() - 1;
        }

        protected void swap(List<HandicapPeople> swap, int index1, int index2) {
            HandicapPeople temp = new HandicapPeople();
            temp.setName(swap.get(index2).getName());
            temp.setId(swap.get(index2).getId());
            temp.setAge1(swap.get(index2).getAge());
            temp.setState(swap.get(index2).getState());
            temp.setType(swap.get(index2).getType());
            swap.set(index2, swap.get(index1));
            swap.set(index1, temp);
        }

        protected boolean contain(String[] V, String name) {
            for (String str : V) {
                if (str.equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }

        protected boolean hashCodeComparison(Object str, Object str1) {
            return str.hashCode() == str1.hashCode();
        }

        protected int extractId(String state) {
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

        protected String extraxtState(String state) {
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
    protected static boolean convert(String name) {
        boolean check = false;
        for (int i = 0; i <= name.length() - 1; i++) {
            if (Character.isDigit(name.charAt(i))) {
                check = true;
            }
        }
        return check;
    }




}
