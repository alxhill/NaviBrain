package com.lemonslice.navibrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alexander on 11/11/2013.
 */
public class BrainData {

    /**
     * Static method to get a reference to the brain data
     */
    private static HashMap<String,BrainData> globalBrainData;
    public static BrainData getBrainData(String section) {
        if (globalBrainData == null) {
            globalBrainData = new HashMap<String, BrainData>();

            BrainData frontal = new BrainData("Frontal Lobe", "associated with reasoning, planning, parts of speech, movement, emotions, and problem solving");
            BrainData parietal = new BrainData("Parietal Lobe", "associated with movement, orientation, recognition, perception of stimuli");
            BrainData occipital = new BrainData("Occipital Lobe", "associated with visual processing");
            BrainData temporal = new BrainData("Temporal Lobe", "associated with perception and recognition of auditory stimuli, memory, and speech");

            globalBrainData.put("Frontal Lobe", frontal);
            globalBrainData.put("Parietal Lobe", parietal);
            globalBrainData.put("Occipital Lobe", occipital);
            globalBrainData.put("Temporal Lobe", temporal);
        }
        return globalBrainData.get(section);
    }

    private String name;
    private String description;

    private List<BrainData> subsections;

    public BrainData(String name, String description) {
        subsections = new ArrayList<BrainData>();
        this.name = name;
        this.description = description;
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
}
