package siit.sevices.Alimente;

import siit.model.Alimente.Alimente;

import java.util.List;
import java.util.Map;

public interface Surviveable {
    public Map<Alimente,Integer> getNecessaryProducts(List<Alimente> alimente);
}
