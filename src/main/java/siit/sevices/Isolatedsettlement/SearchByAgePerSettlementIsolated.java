package siit.sevices.Isolatedsettlement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.IsolatedPeople;
import siit.model.People;
import siit.sevices.*;

import java.util.ArrayList;
import java.util.List;

@Service("searchByAgePerSettlementIsolated")
public class SearchByAgePerSettlementIsolated extends Algorithms implements SearchableBySettlementIsolated {
    @Autowired
    IsolatedPeopleService isolatedPeopleService;


    @Override
    public List<IsolatedPeople> search(String name, String settlement) {
        int age = Integer.parseInt(name);
        List<IsolatedPeople> peoplesSelected = isolatedPeopleService.getIsolatedPeoplePerSettlement(settlement);
        List<IsolatedPeople> getSelectedPeople = new ArrayList<>();
        int end = getEndIndex(peoplesSelected);
        quickSortAge(peoplesSelected, 0, end);
        int index = searchAge(peoplesSelected, age, 0, end);
        if (index == (-1)) {
            return null;
        }
        getRes(peoplesSelected, index, age).stream().forEach(e -> getSelectedPeople.add(peoplesSelected.get(e)));
        return getSelectedPeople;
    }

    private void quickSortAge(List<IsolatedPeople> swap, int start, int end) {
        if (start < end) {
            int piv = getPivotAge(swap, start, end);
            quickSortAge(swap, start, piv - 1);
            quickSortAge(swap, piv + 1, end);
        }
    }

    private int getPivotAge(List<IsolatedPeople> swap, int start, int end) {
        People piv = new People();
        piv.setName(swap.get(end).getName());
        piv.setId(swap.get(end).getId());
        piv.setAge1(swap.get(end).getAge());
        piv.setState(swap.get(end).getState());
        int j = start - 1;
        for (int i = start; i <= end - 1; i++) {
            if (swap.get(i).getAge() < piv.getAge()) {
                j++;
                swap(swap, j, i);
            }
        }
        swap(swap, j + 1, end);
        return j + 1;

    }

    private int searchAge(List<IsolatedPeople> peopleList, int age, int start, int end) {
        int mid = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (age == peopleList.get(mid).getAge()) {
            return mid;
        }
        if (age > peopleList.get(mid).getAge()) {

            return searchAge(peopleList, age, mid + 1, end);
        }
        return searchAge(peopleList, age, start, mid - 1);

    }

    private void addUPAge(List<Integer> ints, List<IsolatedPeople> people, int index, int age) {
        for (int i = index; i <= people.size() - 1; i++) {
            if (age != people.get(i).getAge()) {
                break;
            }
            if (age == people.get(i).getAge()) {
                ints.add(i);
            }
        }
    }

    private void addDownage(List<Integer> ints, List<IsolatedPeople> people, int index, int age) {
        for (int i = index; i >= 0; i--) {
            if (age != people.get(i).getAge()) {
                break;
            }
            if (age == people.get(i).getAge()) {
                ints.add(i);
            }
        }
    }

    private List<Integer> getRes(List<IsolatedPeople> peopleList, int index, int age) {
        List<Integer> indexes = new ArrayList<>();
        if (index == 0) {
            addUPAge(indexes, peopleList, index, age);
        } else if (index == peopleList.size() - 1) {
            addDownage(indexes, peopleList, index, age);

        } else {
            addUPAge(indexes, peopleList, index, age);
            addDownage(indexes, peopleList, index - 1, age);
        }

        return indexes;
    }


}
