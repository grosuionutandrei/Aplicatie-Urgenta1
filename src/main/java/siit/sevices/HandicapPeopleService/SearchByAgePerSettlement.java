package siit.sevices.HandicapPeopleService;

import org.springframework.stereotype.Service;
import siit.model.HandicapPeople;

import java.util.ArrayList;
import java.util.List;

@Service ("searchByAgePerSettlementHandicap")
public class SearchByAgePerSettlement extends AlgorithmsHandicap implements SearchablePerSettlementHandicap {
    @Override
    public List<HandicapPeople> search(String search, String settlement) {
        int age = Integer.parseInt(search);
            List<HandicapPeople> peoplesSelected = handicapPeopleService.displayHandicapPeoplePerSettlement(settlement);
            List<HandicapPeople> getSelectedPeople = new ArrayList<>();
            int end = getEndIndex(peoplesSelected);
            quickSortAge(peoplesSelected, 0, end);
            int index = searchAge(peoplesSelected, age, 0, end);
            if (index == (-1)) {
                return null;
            }
            getRes(peoplesSelected, index, age).stream().forEach(e -> getSelectedPeople.add(peoplesSelected.get(e)));
            return getSelectedPeople;
        }

        private void quickSortAge(List<HandicapPeople> swap, int start, int end) {
            if (start < end) {
                int piv = getPivotAge(swap, start, end);
                quickSortAge(swap, start, piv - 1);
                quickSortAge(swap, piv + 1, end);
            }
        }

        private int getPivotAge(List<HandicapPeople> swap, int start, int end) {
            HandicapPeople piv = new HandicapPeople();
            piv.setName(swap.get(end).getName());
            piv.setId(swap.get(end).getId());
            piv.setAge1(swap.get(end).getAge());
            piv.setState(swap.get(end).getState());
            piv.setType(swap.get(end).getType());
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

        private int searchAge(List<HandicapPeople> peopleList, int age, int start, int end) {
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

        private void addUPAge(List<Integer> ints, List<HandicapPeople> people, int index, int age) {
            for (int i = index; i <= people.size() - 1; i++) {
                if (age != people.get(i).getAge()) {
                    break;
                }
                if (age == people.get(i).getAge()) {
                    ints.add(i);
                }
            }
        }

        private void addDownage(List<Integer> ints, List<HandicapPeople> people, int index, int age) {
            for (int i = index; i >= 0; i--) {
                if (age != people.get(i).getAge()) {
                    break;
                }
                if (age == people.get(i).getAge()) {
                    ints.add(i);
                }
            }
        }

        private List<Integer> getRes(List<HandicapPeople> peopleList, int index, int age) {
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















