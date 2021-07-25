package net.ethobat.system0.api.psionics;

import net.ethobat.system0.api.registry.S0Registry;
import net.ethobat.system0.api.savedata.AbstractPlayerData;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.HashSet;

public class PsionicProfile extends AbstractPlayerData {

    private final PlayerEntity player;
    private final HashSet<PsionicSchema> programmed;
    private final HashMap<PsionicSchema,Long> experience;

    public boolean isInDebugMode;

    public static PsionicProfile TEMP_INSTANCE;

    public PsionicProfile(PlayerEntity player) {
        this.player = player;
        this.programmed = new HashSet<PsionicSchema>();
        this.experience = newExperienceMap();
        this.isInDebugMode = false;
    }

    public static PsionicProfile ofPlayer(PlayerEntity player) {
        // TODO
        if (TEMP_INSTANCE == null) {
            TEMP_INSTANCE = new PsionicProfile(player);
            //TEMP_INSTANCE.maximize();
        }
        return TEMP_INSTANCE;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder("Psionic profile of " + this.player.getEntityName() + ":");
        for (PsionicSchema schema : S0Registry.SCHEMA.getAll()) {
            ret
                    .append("\n\t[")
                    .append(this.isProgrammed(schema) ? " " : "x")
                    .append("] ")
                    .append(schema.NAME)
                    .append(" Lvl.")
                    .append(Long.toString(this.getExp(schema)));
            System.out.println("addign to output....");
        }
        return ret.toString();
    }

    private static HashMap<PsionicSchema, Long> newExperienceMap() {
        HashMap<PsionicSchema,Long> ret = new HashMap<PsionicSchema,Long>();
        for (PsionicSchema schema : S0Registry.SCHEMA.getAll()) {
            ret.put(schema, 0L);
        }
        return ret;
    }

    public boolean program(PsionicSchema schema) {
        if (this.isInDebugMode) {
            return false;
        }
        return this.programmed.add(schema);
    }

    public void deprogramAll() {
        this.programmed.clear();
    }

    public boolean isProgrammed(PsionicSchema schema) {
        if (this.isInDebugMode) {
            return true;
        }
        return this.programmed.contains(schema);
    }

    public long increase(PsionicSchema schema, long exp) {
        if (this.isInDebugMode) {
            return Long.MAX_VALUE;
        }
        long newExp = this.experience.get(schema) + exp;
        this.experience.replace(schema, newExp);
        return newExp;
    }

    public long getExp(PsionicSchema schema) {
        if (this.isInDebugMode) {
            return Long.MAX_VALUE;
        }
        return this.experience.get(schema);
    }

    public void maximize() {
        for (PsionicSchema schema : S0Registry.SCHEMA.getAll()) {
            this.experience.replace(schema, Long.MAX_VALUE);
            this.program(schema);
        }
    }

    public void reset(PsionicSchema schema) {
        this.experience.replace(schema, 0L);
    }

    public void resetAll() {
        for (PsionicSchema schema : S0Registry.SCHEMA.getAll()) {
            this.experience.replace(schema, 0L);
        }
    }

}
