package net.ethobat.system0.api.progression;

import java.util.ArrayList;

public class ProgressProfile {

    private final ArrayList<ProgressItem> completion;

    public ProgressProfile() {
        this.completion = new ArrayList<ProgressItem>();
    }

    public boolean hasCompleted(ProgressItem stage) {
        return this.completion.contains(stage);
    }

}
