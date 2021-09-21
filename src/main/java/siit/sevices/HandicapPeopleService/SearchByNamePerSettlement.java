package siit.sevices.HandicapPeopleService;

import org.springframework.stereotype.Service;
import siit.model.HandicapPeople;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service ("searchByNamePerSettlementHandicap")
public class SearchByNamePerSettlement extends AlgorithmsHandicap implements SearchablePerSettlementHandicap {

    @Override
    public List<HandicapPeople> search(String search, String settlement) {
            List<HandicapPeople> handicapPeople = handicapPeopleService.displayHandicapPeoplePerSettlement(settlement);
            List<Integer> indexes = indexess(handicapPeople,search);
            List<HandicapPeople> selectedPeople = new LinkedList<>();
            indexes.stream().forEach(e->selectedPeople.add(handicapPeople.get(e)));
            return selectedPeople;
        }

        private List<Integer> indexess(List<HandicapPeople> people, String name) {
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


