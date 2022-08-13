package net.ethobat.system0.api.nbt;

import net.ethobat.system0.api.energy.EnergyTypeMap;
import net.minecraft.nbt.*;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;

/*
Use NBTHandler.path to get an ElementPointer
then use .set or .get on that to read or write nbt.
say nbt == {"foo" : {}}
...then NBTHandler.path(nbt, "foo") will return the ElementPointer for the {} bit
or, NBTHandler.path(nbt, "foo", "bar") will return a new ElementPointer that represents a hypothetical object inside of the {} bit
with the key "bar". This will not actually create new NBT data, but you can call .set on this ElementPointer to write new data there.
You can nest this further, so NBTHandler.path(nbt, "foo", "bar", "baz").set(...) will recursively create all tags.
*/
public class NBTHandler {

    //public static final String S0_NBT_KEY = "system0";
    public static final String WIDGET_NBT_KEY = "widgets";

    // ~~~ Main functions ~~~

    public static ElementPointer path(NbtCompound nbt, String... keys) {
        NbtCompound currentTag = nbt;
        String[] tagKeys = Arrays.copyOf(keys, keys.length-1);
        String elementKey = keys[keys.length-1];
        for (String tagKey : tagKeys) {
            if (!currentTag.contains(tagKey, 10)) { // if tag doesn't contain a compound here...
                currentTag.put(tagKey, new NbtCompound()); // ...make one
            }
            currentTag = currentTag.getCompound(tagKey);
        }
        return new ElementPointer(currentTag, elementKey);
    }

    static class ElementPointer {

        NbtCompound nbt;
        String key;
        NbtElement element;

        public ElementPointer(NbtCompound nbt, String key) {
            this.nbt = nbt;
            this.key = key;
            this.element = nbt.get(key);
        }

        // Assumes val is a valid type for nbt data
        public void set(Object val) {
            genericPut(this.nbt, this.key, val);
        }

        public NbtElement get() {
            return this.element;
        }

    }

    // ~~~ ~~~~~~~~~~~~~~ ~~~

    public static Byte getNBTTypeNumber(Class<?> cls) {
             if (cls.isAssignableFrom(NbtByte          .class)) {return NbtElement.BYTE_TYPE;       }
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
//        else if (cls.isAssignableFrom(AbstractNbtNumber.class)) {return NbtElement.???;             }
        else                                                    {return null;}
    }

//    public static NbtCompound getOrCreateCompound(NbtCompound nbt, String key) {
//        return nbt.getCompound(key);
//    }

//    public static @NotNull NbtCompound getOrCreateS0NBT(NbtCompound nbt) {
//        if (nbt.getKeys().contains(S0_NBT_KEY)) {
//            if (nbt.getCompound(S0_NBT_KEY) != null) {
//                return nbt.getCompound(S0_NBT_KEY);
//            }
//        }
//        NbtCompound ret = new NbtCompound();
//        nbt.put(S0_NBT_KEY, ret);
//        return ret;
//    }

    // BUF
    //  NBT
    //  <
    //      {
    //          "widgets":
    //          {
    //              "myPowerBar": {...},
    //              "mySlotGrid": {...}
    //          }
    //      }
    //  >

    public static void writeWidgetNBT(NbtCompound nbt, String name, NbtCompound newNBT) {
        path(nbt, WIDGET_NBT_KEY, name).set(newNBT);
    }

//    todo
//    public static void mergeBufNBT(PacketByteBuf buf, NbtCompound newNBT) {
//        NbtCompound oldNBT = readBufNBTSafe(buf);
//    }

    public static byte getByte(NbtCompound nbt, String key) {return nbt.getByte(key);}
    public static short getShort(NbtCompound nbt, String key) {return nbt.getShort(key);}
    public static int getInt(NbtCompound nbt, String key) {return nbt.getInt(key);}
    public static long getLong(NbtCompound nbt, String key) {return nbt.getLong(key);}
    public static float getFloat(NbtCompound nbt, String key) {return nbt.getFloat(key);}
    public static double getDouble(NbtCompound nbt, String key) {return nbt.getDouble(key);}
//    public static byte[] getByteArray(NbtCompound nbt, String key) {return nbt.getByteArray(key);}
    public static String getString(NbtCompound nbt, String key) {return nbt.getString(key);}
//    public static List<> getList(NbtCompound nbt, String key) {return nbt.getList(key);}
    public static NbtCompound getCompound(NbtCompound nbt, String key) {return nbt.getCompound(key);}
//    public static int[] getIntArray(NbtCompound nbt, String key) {return nbt.getIntArray(key);}
//    public static long[] getLongArray(NbtCompound nbt, String key) {return nbt.getLongArray(key);}

    public static BlockPos getBlockPos(NbtCompound nbt, String key) {
        return new BlockPos(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"));
    }

    public static UUID getUUID(NbtCompound nbt, String key) {
        return UUID.fromString(nbt.getString(key));
    }
    public static EnergyTypeMap getEnergyTypeMap(NbtCompound nbt, String key) {
        return EnergyTypeMap.fromNBT(nbt.getCompound(key));
    }

    public static void putNBTForItem(NbtCompound nbt, String key, Object obj) {
        genericPut(nbt, key, obj);
    }

    public static void genericPut(NbtCompound nbt, String key, Object obj) {
             if (obj instanceof Byte         ) { putNBT(nbt, key, (Byte          ) obj); }
        else if (obj instanceof Short        ) { putNBT(nbt, key, (Short         ) obj); }
        else if (obj instanceof Integer      ) { putNBT(nbt, key, (Integer       ) obj); }
        else if (obj instanceof Long         ) { putNBT(nbt, key, (Long          ) obj); }
        else if (obj instanceof Float        ) { putNBT(nbt, key, (Float         ) obj); }
        else if (obj instanceof Double       ) { putNBT(nbt, key, (Double        ) obj); }
//        else if (obj instanceof Byte[]       ) { putNBT(nbt, key, (Byte[]        ) obj); }
        else if (obj instanceof String       ) { putNBT(nbt, key, (String        ) obj); }
        //else if (obj instanceof NbtElement[]) { putNBT(nbt, key, (Byte) obj); }
        else if (obj instanceof NbtCompound  ) { putNBT(nbt, key, (NbtCompound   ) obj); }
//        else if (obj instanceof Integer[]    ) { putNBT(nbt, key, (Integer[]     ) obj); }
//        else if (obj instanceof Long[]       ) { putNBT(nbt, key, (Long[]        ) obj); }
        else if (obj instanceof UUID         ) { putNBT(nbt, key, obj.toString());       }
        else if (obj instanceof EnergyTypeMap) { putNBT(nbt, key, ((EnergyTypeMap) obj).toNBT()); }
        else {
            throw new IllegalArgumentException("Can't write object of type \"" + obj.getClass().toString() + "\" to NBT!");
        }
    }

//    public static void putNBT(NbtCompound nbt, String key) {
//        putNBTForItem(nbt, key, null);}

    public static void putNBT(NbtCompound nbt, String key, Byte b           ) { nbt.put(key, NbtByte.of(b));        }
    public static void putNBT(NbtCompound nbt, String key, Short n          ) { nbt.put(key, NbtShort.of(n));       }
    public static void putNBT(NbtCompound nbt, String key, Integer n        ) { nbt.put(key, NbtInt.of(n));         }
    public static void putNBT(NbtCompound nbt, String key, Long n           ) { nbt.put(key, NbtLong.of(n));        }
    public static void putNBT(NbtCompound nbt, String key, Float n          ) { nbt.put(key, NbtFloat.of(n));       }
    public static void putNBT(NbtCompound nbt, String key, Double n         ) { nbt.put(key, NbtDouble.of(n));      }
//    public static void putNBT(NbtCompound nbt, String key, List<Byte> ba) {putNBT(nbt, key, new NbtByteArray(ba));}
    public static void putNBT(NbtCompound nbt, String key, String str       ) { nbt.put(key, NbtString.of(str));    }
//    public static void putNBT(NbtCompound nbt, String key, List<? extends NbtElement> list) {return putNBT(nbt, key, new NbtList(list, getNBTTypeNumber((Class<T>) null)));}
    public static void putNBT(NbtCompound nbt, String key, NbtCompound nbt_ ) { nbt.put(key, nbt_);                 }
//    public static void putNBT(NbtCompound nbt, String key, List<Integer> na) {putNBT(nbt, key, new NbtIntArray(na));}
//    public static void putNBT(NbtCompound nbt, String key, List<Long> na) {putNBT(nbt, key, new NbtLongArray(na));}

}
