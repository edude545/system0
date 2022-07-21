package net.ethobat.system0.api.energy;

import net.ethobat.system0.api.nbt.NBTHandler;
import net.ethobat.system0.api.registry.S0Registry;
import net.ethobat.system0.registry.S0EnergyTypes;
import net.minecraft.nbt.NbtCompound;

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

    public static EnergyTypeMap maxAll() {
        EnergyTypeMap ret = new EnergyTypeMap();
        for (EnergyType energyType : S0Registry.ENERGY_TYPE.getAll()) {
            ret.put(energyType, Long.MAX_VALUE);
        }
        return ret;
    }

    /*
    NBT structure:
    {
        "system0:skeintillating" : 70_000_000,
        "system0:blakronic" : 300_000,
        "system0:liminesque" : 20_000
    }
     */
    public NbtCompound toNBT() {
        NbtCompound ret = new NbtCompound();
        for (EnergyType type : this.keySet()) {
            NBTHandler.genericPut(ret, type.getRegistryIDString(), this.get(type));
        }
        return ret;
    }

    public static EnergyTypeMap fromNBT(NbtCompound nbt) {
        EnergyTypeMap ret = new EnergyTypeMap();
        for (String typeName : nbt.getKeys()) {
            ret.put(S0Registry.ENERGY_TYPE.get(typeName), nbt.getLong(typeName));
        }
        return ret;
    }

}