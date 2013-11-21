package com.lemonslice.navibrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * BrainData stores name, description and subsections of the brain.
 *
 * Subsections of the brain are stored as other BrainData objects. This is not yet used.
 */
public class BrainData {

    /**
     * Static method to get BrainData for a specific section of the brain - e.g Frontal Lobe
     */
    private static HashMap<String,BrainData> globalBrainData;
    public static BrainData getBrainData(String section) {
        // set the global brain data if it hasn't been created yet.
        // this may be stored in xml (please no) or a database in future.
        if (globalBrainData == null) {
            globalBrainData = new HashMap<String, BrainData>();

            BrainData frontal = new BrainData("Frontal Lobe", R.drawable.frontal, "associated with reasoning, planning, parts of speech, movement, emotions, and problem solving");
            BrainData parietal = new BrainData("Parietal Lobe", R.drawable.parietal, "associated with movement, orientation, recognition, perception of stimuli");
            BrainData occipital = new BrainData("Occipital Lobe", R.drawable.occipital, "associated with visual processing");
            BrainData temporal = new BrainData("Temporal Lobe", R.drawable.temporal, "associated with perception and recognition of auditory stimuli, memory, and speech");

            globalBrainData.put("Frontal Lobe", frontal);
            globalBrainData.put("Parietal Lobe", parietal);
            globalBrainData.put("Occipital Lobe", occipital);
            globalBrainData.put("Temporal Lobe", temporal);
        }
        return globalBrainData.get(section);
    }

    private String name;
    private int imageResource;
    private String description;

    private List<BrainData> subsections;

    public BrainData(String name, int imageResource, String description) {
        subsections = new ArrayList<BrainData>();
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public List<BrainData> getSubsections() {
        return subsections;
    }

    public void addSubsection(BrainData section) {
        subsections.add(section);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {return imageResource; }
}
