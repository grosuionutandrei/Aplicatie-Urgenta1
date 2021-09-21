package siit.sevices.Alimente;

import org.springframework.stereotype.Service;
import siit.model.Alimente.Alimente;
import siit.model.Alimente.Apa;
import siit.model.Alimente.TipAliment;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("water")
public class WaterNecessary implements WaterSupplies {
    public int getSumOfLitri(List<Apa> water){
        return   water.stream().map(e->e.getLitri()).reduce(0,(a,b)->a+b);
    }

    public Map<Apa,Integer> getNecessaryWater(List<Apa> water) {
        double targetLitri = 2.0;
        double currentLitri = getSumOfLitri(water);
        quick(water,0,water.size()-1);
        System.out.println(water);
        Map<Apa, Integer> values = populateMap(water);
        if(currentLitri>targetLitri){
            return values;
        }else{
            int litriMap = 0;
            while(litriMap<=targetLitri) {
                for (int i = 0; i <= water.size() - 1; i++) {
                    litriMap = sumLitri(values);
                    if (litriMap >= targetLitri) {
                        break;
                    }
                    if (values.containsKey(water.get(i))) {
                        values.put(water.get(i), values.get(water.get(i)) + 1);
                    } else {
                        values.put(water.get(i), 1);
                    }
                    currentLitri += water.get(i).getLitri();
                }
            }

        }
        System.out.println(values);
        return values;
    }
    private static Map<Apa,Integer> populateMap(List<Apa>water){
        Map<Apa,Integer> values = new LinkedHashMap<>();
        for(Apa apa: water){
            if (values.containsKey(apa)) {
                values.put(apa,values.get(apa)+1);
            }else{
                values.put(apa,1);
            }
        }
        return values;
    }



    private  void quick(List<Apa> water,int start, int end){
        if(start<end){
            int piv = getPivot(water,start,end);
            quick(water,piv+1,end);
            quick(water, start,piv-1 );


        }
    }
    private  int getPivot(List<Apa>water,int start,int end){
        Apa piv = new Apa();
        piv.setName(water.get(end).getName());
        piv.setLitri(water.get(end).getLitri());
        int j = start-1;
        for(int i = start;i<=end-1;i++){
            if(water.get(i).getLitri()<piv.getLitri()){
                j++;
                swap(water,i,j);
            }
        }
        swap(water,j+1,end);
        return  j+1;
    }

    public  void swap(List<Apa> swap, int index1, int index2) {
        Apa temp = new Apa();
        temp.setLitri(swap.get(index2).getLitri());
        temp.setName(swap.get(index2).getName());
        swap.set(index2,swap.get(index1));
        swap.set(index1, temp);
    }
    private static int sumLitri(Map<Apa,Integer> values){
        int sum = 0;
        for(Map.Entry<Apa,Integer> entry: values.entrySet()){
            sum += (entry.getKey().getLitri()*entry.getValue());
        }
        return sum;
    }
}
