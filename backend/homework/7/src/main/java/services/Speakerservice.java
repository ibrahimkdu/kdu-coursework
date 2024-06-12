package services;

import constants.Constants;
import entitities.Speaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component(value = "speakerService")
public class Speakerservice {
    public Speaker getSonySpeaker(){
        return new Speaker(Constants.speakerBrand.SONY,Constants.SONYPRICE);
    }
    public Speaker getBoseSpeaker(){
        return new Speaker(Constants.speakerBrand.BOSE,Constants.BOSEPRICE);
    }
    public Speaker getSpeaker() {
        List<Speaker> speakers = Arrays.asList(getSonySpeaker(), getBoseSpeaker());
        return speakers.get((int) (Math.random() * speakers.size()));
    }

}
