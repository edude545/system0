package net.ethobat.system0.api.energy;

import java.util.HashMap;
import java.util.Set;

public class EnergyTypeMap extends HashMap<EnergyType, Long> {

    // Sum all of this map's energy values
    public long sumSelf() {
        long ret = 0;
        for (long n : this.values()) {
            ret += n;
        }
        return ret;
    }

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

    public static EnergyTypeMap singleType(EnergyType type, long amt) {
        EnergyTypeMap ret = new EnergyTypeMap();
        ret.put(type, amt);
        return ret;
    }

}