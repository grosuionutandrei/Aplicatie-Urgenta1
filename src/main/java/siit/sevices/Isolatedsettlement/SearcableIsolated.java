package siit.sevices.Isolatedsettlement;
import siit.model.IsolatedPeople;
import java.util.List;

public interface SearcableIsolated {
    List<IsolatedPeople> search(String name);
}
