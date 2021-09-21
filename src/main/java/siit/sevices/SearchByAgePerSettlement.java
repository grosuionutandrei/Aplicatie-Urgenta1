package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.People;

import java.util.ArrayList;
import java.util.List;
@Service("searchByAgePerSettlement")
public class SearchByAgePerSettlement implements SearchableBySettlement {
    @Autowired
    private PeopleService peopleService;

    @Override
    public List<People> search(String name, String settlement) {
            int age = Integer.parseInt(name);
            List<People> peoplesSelected = peopleService.getPeoplesPerSettlement(settlement);
            List<People> getSelectedPeople = new ArrayList<>();
            int end = getEndIndex(peoplesSelected);
            quickSortAge(peoplesSelected, 0, end);
            int index = searchAge(peoplesSelected, age, 0, end);
            if (index == (-1)) {
                return null;
            }
            getRes(peoplesSelected, index, age).stream().forEach(e -> getSelectedPeople.add(peoplesSelected.get(e)));
            getRes(peoplesSelected, index, age).stream().forEach(e -> System.out.println(peoplesSelected.get(e)));
            return getSelectedPeople;

    }
    private  int getEndIndex(List<?> elements) {
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

    private  void quickSortAge(List<People> swap, int start, int end) {
        if (start < end) {
            int piv = getPivotAge(swap, start, end);
            quickSortAge(swap, start, piv - 1);
            quickSortAge(swap, piv + 1, end);
        }
    }

    private  int getPivotAge(List<People> swap, int start, int end) {
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
    private  void addUPAge(List<Integer> ints, List<People> people, int index, int age) {
        for (int i = index; i <= people.size() - 1; i++) {
            if (age != people.get(i).getAge()) {
                break;
            }
            if (age == people.get(i).getAge()) {
                ints.add(i);
            }
        }
    }

    private  void addDownage(List<Integer> ints, List<People> people, int index, int age) {
        for (int i = index ; i >= 0; i--) {
            if (age != people.get(i).getAge()) {
                break;
            }
            if (age == people.get(i).getAge()) {
                ints.add(i);
            }
        }
    }

    private  List<Integer> getRes(List<People> peopleList, int index, int age) {
        List<Integer> indexes = new ArrayList<>();
        if (index == 0) {
            addUPAge(indexes, peopleList, index, age);
        } else if (index == peopleList.size() - 1) {
            addDownage(indexes, peopleList, index, age);
        } else {
            addUPAge(indexes, peopleList, index, age);
            addDownage(indexes, peopleList, index-1, age);
        }

        return indexes;
    }
    private  int searchAge(List<People> peopleList, int age, int start, int end) {
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





}
