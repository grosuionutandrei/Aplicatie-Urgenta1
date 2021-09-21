package siit.sevices.Isolatedsettlement;
import siit.model.IsolatedPeople;
import java.util.List;

public interface SearchableBySettlementIsolated {
    List<IsolatedPeople> search(String name, String settlement);
}
