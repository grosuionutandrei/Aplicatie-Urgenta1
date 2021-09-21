package siit.sevices.Alimente;

import siit.model.Alimente.Apa;

import java.util.List;
import java.util.Map;

public interface WaterSupplies {
    public Map<Apa,Integer> getNecessaryWater(List<Apa> water);
}
