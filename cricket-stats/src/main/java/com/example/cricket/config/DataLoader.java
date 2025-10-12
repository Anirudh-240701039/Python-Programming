package com.example.cricket.config;

import com.example.cricket.model.Cricketer;
import com.example.cricket.model.Role;
import com.example.cricket.repository.CricketerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(CricketerRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Cricketer c1 = new Cricketer();
                c1.setName("Virat Kohli");
                c1.setCountry("India");
                c1.setRole(Role.BATSMAN);
                c1.setMatches(500);
                c1.setRuns(26000);
                c1.setWickets(4);
                c1.setBattingAverage(54.0);
                c1.setBowlingAverage(0.0);
                c1.setStrikeRate(92.0);

                Cricketer c2 = new Cricketer();
                c2.setName("Babar Azam");
                c2.setCountry("Pakistan");
                c2.setRole(Role.BATSMAN);
                c2.setMatches(250);
                c2.setRuns(12000);
                c2.setWickets(0);
                c2.setBattingAverage(50.0);
                c2.setBowlingAverage(0.0);
                c2.setStrikeRate(88.0);

                Cricketer c3 = new Cricketer();
                c3.setName("Rashid Khan");
                c3.setCountry("Afghanistan");
                c3.setRole(Role.ALL_ROUNDER);
                c3.setMatches(200);
                c3.setRuns(3000);
                c3.setWickets(400);
                c3.setBattingAverage(22.0);
                c3.setBowlingAverage(18.0);
                c3.setStrikeRate(130.0);

                repository.save(c1);
                repository.save(c2);
                repository.save(c3);
            }
        };
    }
}
