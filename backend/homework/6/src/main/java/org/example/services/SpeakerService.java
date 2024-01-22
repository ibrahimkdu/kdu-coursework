package org.example.services;

import org.example.bean.Speaker;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class SpeakerService {

    public Speaker getSpeaker() {
        List<Speaker> speakers = Arrays.asList(speaker1(), speaker2());
        return speakers.get((int) (Math.random() * speakers.size()));
    }

    private Speaker speaker1() {
        Speaker speaker1 = new Speaker();
        speaker1.setName("Sony");
        speaker1.setPrice(1000);
        return speaker1;
    }

    private Speaker speaker2() {
        Speaker speaker2 = new Speaker();
        speaker2.setName("Bose");
        speaker2.setPrice(800);
        return speaker2;
    }
}
