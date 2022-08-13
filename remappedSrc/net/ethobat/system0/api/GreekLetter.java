package net.ethobat.system0.api;

import java.util.Arrays;

public enum GreekLetter {

    ALPHA,      BETA,       GAMMA,      DELTA,
    EPSILON,    ZETA,       ETA,        THETA,
    IOTA,       KAPPA,      LAMBDA,     MU,
    NU,         XI,         OMICRON,    PI,
    RHO,        SIGMA,      TAU,        UPSILON,
    PHI,        CHI,        PSI,        OMEGA;

    public static final GreekLetter[] ALL = values();

    public int asInt() {
        return Arrays.binarySearch(ALL, this);
    }

    public static GreekLetter asGreekLetter(int n) {
        return ALL[n];
    }

    public String asString() {
        return this.name().substring(0,1) + this.name().toLowerCase();
    }

}
