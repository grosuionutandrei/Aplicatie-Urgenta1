package siit.sevices.HandicapPeopleService;
import org.springframework.stereotype.Service;
import siit.model.HandicapPeople;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service("searchByStateHandicap")
public class SearchByStateHandicap  extends AlgorithmsHandicap implements SearchableHandicap {


    @Override
    public List<HandicapPeople> search(String search, HttpSession session) {
        List<HandicapPeople> handicapPeople = handicapPeopleService.displayHandicapPeople(session);
        List<HandicapPeople> getSelectedPeople = new ArrayList<>();
                int end = getEndIndex(handicapPeople);
                quickSort(handicapPeople, 0, end);
                int index = BinarrySearch(handicapPeople,search , 0, end);
                if (index == (-1)) {
                    return null;
                }
                getRes(handicapPeople, index, search).stream().forEach(e -> getSelectedPeople.add(handicapPeople.get(e)));
                getRes(handicapPeople, index, search).stream().forEach(e -> System.out.println(handicapPeople.get(e)));
                return getSelectedPeople;
            }

            private void quickSort(List<HandicapPeople> swap, int start, int end) {
                if (start < end) {
                    int piv = getPivot(swap, start, end);
                    quickSort(swap, start, piv - 1);
                    quickSort(swap, piv + 1, end);
                }
            }
            private int getPivot(List<HandicapPeople> swap, int start, int end) {
                HandicapPeople piv = new HandicapPeople();
                piv.setName(swap.get(end).getName());
                piv.setId(swap.get(end).getId());
                piv.setAge1(swap.get(end).getAge());
                piv.setState(swap.get(end).getState());
                piv.setType(swap.get(end).getType());

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

            private int BinarrySearch(List<HandicapPeople> sorted, String str, int start, int end) {
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


            private void addUP(List<Integer> ints, List<HandicapPeople> people, int index, String str) {
                for (int i = index; i <= people.size() - 1; i++) {
                    if (!hashCodeComparison(str, people.get(i).getState())) {
                        break;
                    }
                    if (hashCodeComparison(str, people.get(i).getState())) {
                        ints.add(i);
                    }
                }
            }

            private void addDown(List<Integer> ints, List<HandicapPeople> dogs, int index, String str) {
                for (int i = index; i >= 0; i--) {
                    if (!hashCodeComparison(str, dogs.get(i).getState())) {
                        break;
                    }
                    if (hashCodeComparison(str, dogs.get(i).getState())) {
                        ints.add(i);
                    }
                }
            }

            private List<Integer> getRes(List<HandicapPeople> peopleList, int index, String str) {
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







