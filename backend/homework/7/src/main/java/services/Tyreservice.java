package services;

import constants.Constants;
import entitities.Tyre;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Tyreservice {
    public Tyre getMRFTyre(){
        return new Tyre(Constants.tyreBrand.MRF,Constants.mrfPrice);
    }
    public Tyre getBridgestoneTyre(){
        return new Tyre(Constants.tyreBrand.BRIDGESTONE,Constants.bridgetonePrice);
    }
    public Tyre getTyre() {
        List<Tyre> tyres = Arrays.asList(getMRFTyre(), getBridgestoneTyre());
        return tyres.get((int) (Math.random() * tyres.size()));
    }
}

