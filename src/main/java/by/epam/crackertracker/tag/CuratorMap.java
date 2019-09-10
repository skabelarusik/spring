/**
 * map curator for my tag
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.tag;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("serial")
public class CuratorMap {
    private Map<String, Double> map;
    private Iterator<Map.Entry<String, Double>> it;
    public CuratorMap(Map<String, Double> map){
        this.map =  map;
        it = map.entrySet().iterator();
    }

    public int getSize() {
        return map.size();
    }

    public String getCurator() {
        if(it.hasNext()){
            Map.Entry<String, Double> m = it.next();
            String str = m.getValue() + "  " + m.getKey();
            return str;
        } else {
            it = map.entrySet().iterator();
            return getCurator();
        }
    }
}
