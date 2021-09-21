package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.People;

import java.util.ArrayList;
import java.util.List;
@Service("searchByNamePerSettlement")

public class SearchByNamePerSettlement implements SearchableBySettlement {
    @Autowired
    PeopleService peopleService;

    @Override
    public List<People> search(String name, String settlement) {
        List<People> peoplesSelected = peopleService.getPeoplesPerSettlement(settlement);
        List<People> getSelectedPeople = new ArrayList<>();
        List<Integer> indexes = indexess(peoplesSelected, name);
        indexes.stream().forEach(e -> getSelectedPeople.add(peoplesSelected.get(e)));
        return getSelectedPeople;
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

    private boolean contain(String[] V, String name) {
        for (String str : V) {
            if (str.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
