package com.example.cachingroom1;

import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DogFact {

    //{
    //  "facts": [
    //    "In 2001, it was estimated that there are approximately 400 million dogs in the world.",
    //    "Why are dogs’ noses so wet? Dogs’ noses secrete a thin layer of mucous that helps them absorb scent. They then lick their noses to sample the scent through their mouth.",
    //    "The term \"dog days of summer\" was coined by the ancient Greeks and Romans to describe the hottest days of summer that coincided with the rising of the Dog Star, Sirius.",
    //    "In ancient Greece, kennels of dogs were kept at the sanctuary of Asclepius at Epidaurus. Dogs were frequently sacrificed there because they were plentiful, inexpensive, and easy to control. During the July 25 celebration of the kunophontis (\"the massacre of dogs\"), dog sacrifices were performed to appease the ancestors of Apollo’s son, Linos, who was devoured by dogs..",
    //    "Did you hear that? Sound frequency is measured in Hertz (Hz). The higher the Hertz, the higher-pitched the sound. Dogs hear best at 8,000 Hz, while humans hear best at around 2,000 Hz."
    //  ],
    //  "success": true
    //}

    List<String> facts;

    public List<String> getFact() {
        return facts;
    }

    public String toString(List<String> f) {
        JSONObject json = new JSONObject();
        if (f != null) {
            for (String a : f) {
                try {
                    json.put(a, a);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return json.toString();
    }
}
