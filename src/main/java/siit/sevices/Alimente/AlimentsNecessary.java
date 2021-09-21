package siit.sevices.Alimente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.Alimente.Alimente;
import siit.model.Alimente.TipAliment;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("aliments")
public class AlimentsNecessary implements Surviveable {
    public int getSumOfCalories(List<Alimente> alimente){
        return   alimente.stream().map(e->e.getCalories()).reduce(0,(a,b)->a+b);
    }

    public Map<Alimente,Integer> getNecessaryProducts(List<Alimente> aliments) {
        int targetCalories = 2000;
        int currentCalories = getSumOfCalories(aliments);
        quick(aliments,0,aliments.size()-1);
       // System.out.println(aliments);
        Map<Alimente, Integer> values = populateMap(aliments);
        if(currentCalories>targetCalories){
            return values;
        }else{
            int calloriesMap = 0;
            while(calloriesMap<=targetCalories) {
                for (int i = 0; i <= aliments.size() - 1; i++) {
                    calloriesMap = sumCalories(values);
                    if (calloriesMap >= targetCalories) {
                        break;
                    }
                    if (values.containsKey(aliments.get(i))) {
                        values.put(aliments.get(i), values.get(aliments.get(i)) + 1);
                    } else {
                        values.put(aliments.get(i), 1);
                    }
                    currentCalories += aliments.get(i).getCalories();
                }
            }
        }
        //System.out.println(values);
        return values;
    }
    private static Map<Alimente,Integer> populateMap(List<Alimente>aliments){
        Map<Alimente,Integer> values = new LinkedHashMap<>();
        for(Alimente aliment: aliments){
            if (values.containsKey(aliment)) {
                values.put(aliment,values.get(aliment)+1);
            }else{
                values.put(aliment,1);
            }
        }
        return values;
    }
    private  void quick(List<Alimente> aliments,int start, int end){
        if(start<end){
            int piv = getPivot(aliments,start,end);
            quick(aliments,piv+1,end);
            quick(aliments, start,piv-1 );

        }
    }
    private  int getPivot(List<Alimente>aliments,int start,int end){
        TipAliment piv = new TipAliment();
        if (aliments.get(end)instanceof TipAliment){
            piv.setTipAliment(((TipAliment) aliments.get(end)).getTipAliment());
        }
        piv.setCalories(aliments.get(end).getCalories());
        piv.setQuantity(aliments.get(end).getQuantity());
        int j = start-1;
        for(int i = start;i<=end-1;i++){
            if(aliments.get(i).getCalories()<piv.getCalories()){
                j++;
                swap(aliments,i,j);
            }
        }
        swap(aliments,j+1,end);
        return  j+1;
    }

    public  void swap(List<Alimente> swap, int index1, int index2) {
        TipAliment temp = new TipAliment();
        if(swap.get(index2)instanceof TipAliment){
            temp.setTipAliment(((TipAliment) swap.get(index2)).getTipAliment());
        }
        temp.setQuantity(swap.get(index2).getQuantity());
        temp.setCalories(swap.get(index2).getCalories());
        swap.set(index2,swap.get(index1));
        swap.set(index1, temp);
    }
    private static int sumCalories(Map<Alimente,Integer> values){
        int sum = 0;
        for(Map.Entry<Alimente,Integer> entry: values.entrySet()){
            sum += (entry.getKey().getCalories()*entry.getValue());
        }
        return sum;
    }



}
