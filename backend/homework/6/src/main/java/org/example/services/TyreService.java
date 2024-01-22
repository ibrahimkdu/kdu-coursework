package org.example.services;

import org.example.bean.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TyreService {

    @Bean(name = "bridgestone")
    public Tyre tyre1() {
        Tyre tyre1 = new Tyre();
        tyre1.setName("Bridgestone");
        tyre1.setPrice(1000.00);
        return tyre1;
    }

    @Bean(name = "mrf")
    public Tyre tyre2() {
        Tyre tyre2 = new Tyre();
        tyre2.setName("MRF");
        tyre2.setPrice(1200.00);
        return tyre2;
    }

    public Tyre getTyre() {
        List<Tyre> tyres = Arrays.asList(tyre1(), tyre2());
        return tyres.get((int) (Math.random() * tyres.size()));
    }
}
