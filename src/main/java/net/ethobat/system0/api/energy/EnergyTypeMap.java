package net.ethobat.system0.api.energy;

import java.util.HashMap;
import java.util.Set;

public class EnergyTypeMap extends HashMap<EnergyType, Long> {

    public EnergyTypeMap sum(EnergyTypeMap map) {
        Set<EnergyType> types = this.keySet();
        types.addAll(map.keySet());

        EnergyTypeMap ret = new EnergyTypeMap();

        for (EnergyType type : types) {
            ret.put(type, this.sumEnergyValues(map, type));
        }

        return ret;
    }

    public long sumEnergyValues(EnergyTypeMap map, EnergyType type) {
        if (!this.containsKey(type)) {
            return map.get(type);
        }
        else if (!map.containsKey(type)) {
            return this.get(type);
        }
        else {
            return this.get(type) + map.get(type);
        }
    }

}