package net.ethobat.system0.api.nbt;

import net.minecraft.nbt.*;

import java.util.List;

public class ItemNBTHandler {

    public static final String S0_NBT_KEY = "system0";

    public static <T extends NbtElement> byte getNBTTypeNumber(T el) {
        return getNBTTypeNumber(el.getClass());
    }

    public static <T extends NbtElement> byte getNBTTypeNumber(Class<T> cls) {
             if (cls.isAssignableFrom(NbtNull          .class)) {return NbtElement.NULL_TYPE;       }
        else if (cls.isAssignableFrom(NbtByte          .class)) {return NbtElement.BYTE_TYPE;       }
        else if (cls.isAssignableFrom(NbtShort         .class)) {return NbtElement.SHORT_TYPE;      }
        else if (cls.isAssignableFrom(NbtInt           .class)) {return NbtElement.INT_TYPE;        }
        else if (cls.isAssignableFrom(NbtLong          .class)) {return NbtElement.LONG_TYPE;       }
        else if (cls.isAssignableFrom(NbtFloat         .class)) {return NbtElement.FLOAT_TYPE;      }
        else if (cls.isAssignableFrom(NbtDouble        .class)) {return NbtElement.DOUBLE_TYPE;     }
        else if (cls.isAssignableFrom(NbtByteArray     .class)) {return NbtElement.BYTE_ARRAY_TYPE; }
        else if (cls.isAssignableFrom(NbtString        .class)) {return NbtElement.STRING_TYPE;     }
        else if (cls.isAssignableFrom(NbtList          .class)) {return NbtElement.LIST_TYPE;       }
        else if (cls.isAssignableFrom(NbtCompound      .class)) {return NbtElement.COMPOUND_TYPE;   }
        else if (cls.isAssignableFrom(NbtIntArray      .class)) {return NbtElement.INT_ARRAY_TYPE;  }
        else if (cls.isAssignableFrom(NbtLongArray     .class)) {return NbtElement.LONG_ARRAY_TYPE; }
//        else if (cls.isAssignableFrom(AbstractNbtNumber.class)) {return NbtElement.NUMBER_TYPE;     }
        else                            {throw new IllegalArgumentException("No NBT type number available for " + cls.getClass().getName());}
    }

    public static NbtCompound getS0NBT(NbtCompound nbt) {
        if (nbt.getKeys().contains(S0_NBT_KEY)) {
            return nbt.getCompound(S0_NBT_KEY);
        }
        return (NbtCompound) nbt.put(S0_NBT_KEY, new NbtCompound());
    }

    public static void putNBT(NbtCompound nbt, String key, NbtElement element) {
        getS0NBT(nbt).put(key, element);
    }

    public static void putNBT(NbtCompound nbt, String key) {putNBT(nbt, key, NbtNull.INSTANCE);}

    public static void putNBT(NbtCompound nbt, String key, byte b) {putNBT(nbt, key, NbtByte.of(b));}
    public static void putNBT(NbtCompound nbt, String key, short n) {putNBT(nbt, key, NbtShort.of(n));}
    public static void putNBT(NbtCompound nbt, String key, int n) {putNBT(nbt, key, NbtInt.of(n));}
    public static void putNBT(NbtCompound nbt, String key, long n) {putNBT(nbt, key, NbtLong.of(n));}
    public static void putNBT(NbtCompound nbt, String key, float n) {putNBT(nbt, key, NbtFloat.of(n));}
    public static void putNBT(NbtCompound nbt, String key, double n) {putNBT(nbt, key, NbtDouble.of(n));}
    public static void putNBT(NbtCompound nbt, String key, byte[] ba) {putNBT(nbt, key, new NbtByteArray(ba));}
    public static void putNBT(NbtCompound nbt, String key, String str) {putNBT(nbt, key, NbtString.of(str));}
//    public static void putNBT(NbtCompound nbt, String key, List<? extends NbtElement> list) {return putNBT(nbt, key, new NbtList(list, getNBTTypeNumber((Class<T>) null)));}
//    public static void putNBT(NbtCompound nbt, String key, NbtCompound nbt_) {putNBT(nbt, key, nbt_);}
    public static void putNBT(NbtCompound nbt, String key, int[] na) {putNBT(nbt, key, new NbtIntArray(na));}
    public static void putNBT(NbtCompound nbt, String key, long[] na) {putNBT(nbt, key, new NbtLongArray(na));}

    public static long setEnergy(NbtCompound nbt, long n) {putNBT(nbt, "energy", n); return n;}
    public static long getEnergy(NbtCompound nbt) {return getS0NBT(nbt).getLong("energy");}

}
