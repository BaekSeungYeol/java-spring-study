package me.seungyeol.tobisp.Chapter1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

    @Bean
    public ConnectionMaker getConnection() {
        return new CountingConnectionMaker(getRealConnectionMaker());
    }

    private DConnectionMaker getRealConnectionMaker() {
        return new DConnectionMaker();
    }
}
