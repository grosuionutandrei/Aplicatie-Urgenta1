package siit.sevices.HandicapPeopleService;

import org.springframework.stereotype.Service;
import siit.model.HandicapPeople;
import siit.web.HandicapPeopleController;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface SearchableHandicap {
    List<HandicapPeople> search(String search, HttpSession session);

}
