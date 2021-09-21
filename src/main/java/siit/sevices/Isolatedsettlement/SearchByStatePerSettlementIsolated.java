package siit.sevices.Isolatedsettlement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.IsolatedPeople;
import siit.sevices.*;

import java.util.ArrayList;
import java.util.List;

@Service("searchByStatePerSettlementIsolated")
public class SearchByStatePerSettlementIsolated extends Algorithms implements SearchableBySettlementIsolated {
    @Autowired
    IsolatedPeopleService isolatedPeopleService;
    @Autowired
    ComunneService comunneService;

    @Override
    public List<IsolatedPeople> search(String name, String settlement) {
        List<IsolatedPeople> peoplesSelected = isolatedPeopleService.getIsolatedPeoplePerSettlement(settlement);
        List<IsolatedPeople> getSelectedPeople = new ArrayList<>();
        int end = getEndIndex(peoplesSelected);
        quickSort(peoplesSelected, 0, end);
        int index = BinarrySearch(peoplesSelected, name, 0, end);
        if (index == (-1)) {
            return null;
        }
        getRes(peoplesSelected, index, name).stream().forEach(e -> getSelectedPeople.add(peoplesSelected.get(e)));
        getRes(peoplesSelected, index, name).stream().forEach(e -> System.out.println(peoplesSelected.get(e)));
        return getSelectedPeople;
    }


    private void quickSort(List<IsolatedPeople> swap, int start, int end) {
        if (start < end) {
            int piv = getPivot(swap, start, end);
            quickSort(swap, start, piv - 1);
            quickSort(swap, piv + 1, end);
        }
    }

    private int getPivot(List<IsolatedPeople> swap, int start, int end) {
        IsolatedPeople piv = new IsolatedPeople();
        piv.setName(swap.get(end).getName());
        piv.setId(swap.get(end).getId());
        piv.setAge1(swap.get(end).getAge());
        piv.setState(swap.get(end).getState());
        piv.setMap(swap.get(end).getMap());
        piv.setMijlocDeAcces(swap.get(end).getMijlocDeAcces());

        int j = start - 1;
        for (int i = start; i <= end - 1; i++) {
            if (swap.get(i).getState().hashCode() < piv.getState().hashCode()) {
                j++;
                swap(swap, j, i);
            }
        }
        swap(swap, j + 1, end);
        return j + 1;
    }

    private int BinarrySearch(List<IsolatedPeople> sorted, String str, int start, int end) {
        int mid = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (str.hashCode() == sorted.get(mid).getState().hashCode()) {
            return mid;
        }
        if (str.hashCode() < sorted.get(mid).getState().hashCode()) {
            return BinarrySearch(sorted, str, start, mid - 1);
        }
        return BinarrySearch(sorted, str, mid + 1, end);

    }

    private void addUP(List<Integer> ints, List<IsolatedPeople> people, int index, String str) {
        for (int i = index; i <= people.size() - 1; i++) {
            if (!hashCodeComparison(str, people.get(i).getState())) {
                break;
            }
            if (hashCodeComparison(str, people.get(i).getState())) {
                ints.add(i);
            }
        }
    }

    private void addDown(List<Integer> ints, List<IsolatedPeople> dogs, int index, String str) {
        for (int i = index; i >= 0; i--) {
            if (!hashCodeComparison(str, dogs.get(i).getState())) {
                break;
            }
            if (hashCodeComparison(str, dogs.get(i).getState())) {
                ints.add(i);
            }
        }
    }

    private List<Integer> getRes(List<IsolatedPeople> peopleList, int index, String str) {
        List<Integer> indexes = new ArrayList<>();
        if (index == 0) {
            addUP(indexes, peopleList, index, str);
        } else if (index == peopleList.size() - 1) {
            addDown(indexes, peopleList, index, str);
        } else {
            addUP(indexes, peopleList, index, str);
            addDown(indexes, peopleList, index - 1, str);
        }
        return indexes;
    }


}
