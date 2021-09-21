package siit.sevices;
import siit.model.People;

import java.util.List;

public interface SearchableBySettlement {
    List<People> search(String name, String settlement);
}
