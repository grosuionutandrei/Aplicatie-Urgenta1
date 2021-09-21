package siit.sevices.Isolatedsettlement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.IsolatedPeople;
import siit.sevices.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service("searchByNamePerSettlementIsolated")
public class SearchByNamePerSettlementIsolated extends Algorithms implements SearchableBySettlementIsolated {
    @Autowired
    IsolatedPeopleService isolatedPeopleService;

    @Override
    public List<IsolatedPeople> search(String name, String settlement) {
        List<IsolatedPeople> peoplesSelected = new LinkedList<>();
        List<IsolatedPeople> people = isolatedPeopleService.getIsolatedPeoplePerSettlement(settlement);
        List<Integer> indexes = indexess(people, name);
        for (int i = 0; i <= indexes.size() - 1; i++) {
            peoplesSelected.add(people.get(indexes.get(i)));
        }
        return peoplesSelected;
    }

    private List<Integer> indexess(List<IsolatedPeople> people, String name) {
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


