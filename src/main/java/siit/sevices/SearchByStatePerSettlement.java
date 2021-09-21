package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.People;

import java.util.ArrayList;
import java.util.List;
@Service("searchByStatePerSettlement")
public class SearchByStatePerSettlement implements  SearchableBySettlement{
    @Autowired
    PeopleService peopleService;
    @Override
    public List<People> search(String name, String settlement) {
            List<People> peoplesSelected = peopleService.getPeoplesPerSettlement(settlement);
            List<People> getSelectedPeople = new ArrayList<>();
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
    private boolean hashCodeComparison(Object str, Object str1) {
        return str.hashCode() == str1.hashCode();
    }

    private int getEndIndex(List<?> elements) {
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

    private void quickSort(List<People> swap, int start, int end) {
        if (start < end) {
            int piv = getPivot(swap, start, end);
            quickSort(swap, start, piv - 1);
            quickSort(swap, piv + 1, end);
        }
    }


    private int getPivot(List<People> swap, int start, int end) {
        People piv = new People();
        piv.setName(swap.get(end).getName());
        piv.setId(swap.get(end).getId());
        piv.setAge1(swap.get(end).getAge());
        piv.setState(swap.get(end).getState());

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

    private int BinarrySearch(List<People> sorted, String str, int start, int end) {
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


    private void addUP(List<Integer> ints, List<People> people, int index, String str) {
        for (int i = index; i <= people.size() - 1; i++) {
            if (!hashCodeComparison(str, people.get(i).getState())) {
                break;
            }
            if (hashCodeComparison(str, people.get(i).getState())) {
                ints.add(i);
            }
        }
    }

    private void addDown(List<Integer> ints, List<People> dogs, int index, String str) {
        for (int i = index ; i >= 0; i--) {
            if (!hashCodeComparison(str, dogs.get(i).getState())) {
                break;
            }
            if (hashCodeComparison(str, dogs.get(i).getState())) {
                ints.add(i);
            }
        }
    }

    private List<Integer> getRes(List<People> peopleList, int index, String str) {
        List<Integer> indexes = new ArrayList<>();
        if (index == 0) {
            addUP(indexes, peopleList, index, str);
        } else if (index == peopleList.size() - 1) {
            addDown(indexes, peopleList, index, str);
        } else {
            addUP(indexes, peopleList, index, str);
            addDown(indexes, peopleList, index-1, str);
        }
        return indexes;
    }
}
