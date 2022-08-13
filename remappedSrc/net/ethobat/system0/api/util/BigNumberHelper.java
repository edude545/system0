package net.ethobat.system0.api.util;

import net.ethobat.system0.api.util.S0Math;

import java.math.BigInteger;

public class BigNumberHelper {

    //private static HashMap<Integer,String> shortPrefixMap = new HashMap<Integer,String>();
    //private static HashMap<Integer,String> longPrefixMap = new HashMap<Integer,String>();

    public static String getPrefix(BigInteger n) {
        switch (getPowerOfTen(n)) {
            case 0: return "";
            case 3: return "k";
            case 6: return "M";
            case 9: return "G";
            case 12: return "T";
            case 15: return "P";
            case 18: return "E";
            case 21: return "Z";
            case 24: return "Y";
            case 27: return "R";
            case 30: return "Q";
            case 33: return "H";
            default: return "?";
        }
    }

    public static String getLongPrefix(BigInteger n) {
        switch (getPowerOfTen(n)) {
            case 0: return "";
            case 3: return "kilo";
            case 6: return "Mega";
            case 9: return "Giga";
            case 12: return "Tera";
            case 15: return "Peta";
            case 18: return "Exa";
            case 21: return "Zetta";
            case 24: return "Yotta";
            case 27: return "Ronna";
            case 30: return "Quecca";
            case 33: return "Hella";
            default: return "?";
        }
    }

    public static int getPowerOfTen(BigInteger n) {
        //return S0Math.roundToNearestMultiple((int) Math.floor(Math.log10(n.doubleValue())), 3);
        return S0Math.floorToNearestMultiple(n.toString().length()-1, 3);
    }

//    public static BigInteger truncateForDisplay(BigInteger n) {
//        return n.divide(
//                BigInteger.valueOf((long) Math.pow(
//                        10,
//                        BigInteger.valueOf(getPowerOfTen(n)).doubleValue())
//                )
//        );
//    }
    // 5,874,011
    public static String getDecimalDisplay(BigInteger n) {
        int pwr = getPowerOfTen(n);
        String str = n.toString();
        if (pwr < 3) {
            return str;
        }

        int integerDigits = str.length() % 3;
        if (integerDigits == 0) {integerDigits = 3;}
        String integerDigitsString = str.substring(0,integerDigits);

        int decimalDigits = Math.min(str.length()-integerDigits, 3);
        String decimalDigitsString = str.substring(integerDigits,integerDigits+decimalDigits);

        //if (decimalDigitsString.equals("000")) {return integerDigitsString;}
        return integerDigitsString + "." + decimalDigitsString;
    }

    public static String getPrefixedDisplay(BigInteger n) {
        return getDecimalDisplay(n) + "" + getPrefix(n) + "W";
    }

    public static String getLongPrefixedDisplay(BigInteger n) {
        return getDecimalDisplay(n) + "" + getLongPrefix(n) + "watts";
    }

}
