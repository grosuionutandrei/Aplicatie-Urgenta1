package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import siit.model.HandicapPeople;
import siit.model.People;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Service("searchByName")
public class SearchByName implements Searchable {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    HttpSession session;



    @Override
    public List<People> search(String name) {
        List<People> peoplesSelected = new LinkedList<>();
        List<People> people = peopleService.getPeoples(session);
        List<Integer> indexes = indexess(people, name);
        for (int i = 0; i <= indexes.size() - 1; i++) {
            peoplesSelected.add(people.get(indexes.get(i)));
        }
        return peoplesSelected;
    }

    private static int getEndIndex(List<?> elements) {
        return elements.size() - 1;
    }

    private void swap(List<People> swap, int index1, int index2) {
        People temp = new People();
        temp.setName(swap.get(index2).getName());
        temp.setId(swap.get(index2).getId());
        temp.setAge1(swap.get(index2).getAge());
        temp.setState(swap.get(index2).getState());
        swap.set(index2, swap.get(index1));
        swap.set(index1, temp);
    }

    private boolean contain(String[] V, String name) {
        for (String str : V) {
            if (str.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> indexess(List<People> people, String name) {
        List<Integer> integers = new ArrayList<>();
        int i = 0;
        int j = people.size() - 1;
        int mid = people.size() / 2;
        int temp = 0;
        String[] arrI;
        String[] arrJ;
        while (i < mid && j > mid) {
            arrJ = people.get(j).getName().split("\\s+");
            arrI = people.get(i).getName().split("\\s+");
            if (contain(arrI, name) && contain(arrJ, name)) {
                integers.add(i);
                integers.add(j);
            } else if (contain(arrJ, name)) {
                integers.add(j);
            } else if (contain(arrI, name)) {
                integers.add(i);
            }

            i++;
            j--;
            temp = i;
        }
        while (temp <= mid) {
            arrI = people.get(temp).getName().split("\\s+");

            if (contain(arrI, name)) {
                integers.add(temp);
            }
            temp++;
        }
        return integers;
    }


}
