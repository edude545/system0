package net.ethobat.system0.api.progression;

import net.ethobat.system0.api.savedata.AbstractPlayerData;

import java.util.ArrayList;

public class ProgressProfile extends AbstractPlayerData {

    private final ArrayList<ProgressItem> completion;

    public ProgressProfile() {
        this.completion = new ArrayList<ProgressItem>();
    }

    public boolean hasCompleted(ProgressItem stage) {
        return this.completion.contains(stage);
    }

}
