package siit.sevices.HandicapPeopleService;

import siit.model.HandicapPeople;

import java.util.List;

public interface SearchablePerSettlementHandicap {
    List<HandicapPeople> search(String search,String settlement);
}
