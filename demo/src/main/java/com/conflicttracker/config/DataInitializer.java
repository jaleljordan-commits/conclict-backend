package com.conflicttracker.config;

import com.conflicttracker.model.Country;
import com.conflicttracker.model.Conflict;
import com.conflicttracker.model.ConflictStatus;
import com.conflicttracker.model.Faction;
import com.conflicttracker.model.Event;
import com.conflicttracker.repository.CountryRepository;
import com.conflicttracker.repository.ConflictRepository;
import com.conflicttracker.repository.FactionRepository;
import com.conflicttracker.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CountryRepository countryRepository;
    private final ConflictRepository conflictRepository;
    private final FactionRepository factionRepository;
    private final EventRepository eventRepository;

    public DataInitializer(CountryRepository countryRepository, ConflictRepository conflictRepository,
                          FactionRepository factionRepository, EventRepository eventRepository) {
        this.countryRepository = countryRepository;
        this.conflictRepository = conflictRepository;
        this.factionRepository = factionRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize countries
        if (countryRepository.count() == 0) {
            Country[] countries = {
                new Country("Ukraine", "UA"),
                new Country("Russia", "RU"),
                new Country("Israel", "IL"),
                new Country("Palestine", "PS"),
                new Country("Syria", "SY"),
                new Country("Turkey", "TR"),
                new Country("Iraq", "IQ"),
                new Country("Iran", "IR"),
                new Country("Yemen", "YE"),
                new Country("China", "CN")
            };
            
            for (Country country : countries) {
                countryRepository.save(country);
            }

            // Initialize conflicts
            Conflict conflict1 = new Conflict("Russia-Ukraine War", LocalDate.of(2022, 2, 24), 
                ConflictStatus.ACTIVE, "Military conflict between Russia and Ukraine");
            Conflict conflict2 = new Conflict("Israel-Hamas War", LocalDate.of(2023, 10, 7), 
                ConflictStatus.ACTIVE, "Conflict in Gaza between Israel and Hamas");
            Conflict conflict3 = new Conflict("Syrian Civil War", LocalDate.of(2011, 3, 15), 
                ConflictStatus.FROZEN, "Civil war in Syria");
            Conflict conflict4 = new Conflict("Iraq War", LocalDate.of(2003, 3, 20), 
                ConflictStatus.ENDED, "International military conflict in Iraq");

            conflict1 = conflictRepository.save(conflict1);
            conflict2 = conflictRepository.save(conflict2);
            conflict3 = conflictRepository.save(conflict3);
            conflict4 = conflictRepository.save(conflict4);

            // Initialize factions
            Faction faction1 = new Faction("Russia Military", conflict1);
            Faction faction2 = new Faction("Ukraine Military", conflict1);
            Faction faction3 = new Faction("Israeli Defense Force", conflict2);
            Faction faction4 = new Faction("Hamas", conflict2);
            Faction faction5 = new Faction("Syrian Government", conflict3);
            Faction faction6 = new Faction("Syrian Rebels", conflict3);

            factionRepository.save(faction1);
            factionRepository.save(faction2);
            factionRepository.save(faction3);
            factionRepository.save(faction4);
            factionRepository.save(faction5);
            factionRepository.save(faction6);

            // Initialize events
            Event event1 = new Event(LocalDate.of(2022, 2, 24), "Kyiv", "Russian invasion begins", 
                conflict1);
            Event event2 = new Event(LocalDate.of(2023, 10, 7), "Gaza", "Hamas launches major attack", 
                conflict2);
            Event event3 = new Event(LocalDate.of(2023, 11, 15), "Gaza", "Ceasefire negotiations begin", 
                conflict2);
            Event event4 = new Event(LocalDate.of(2011, 3, 15), "Damascus", "Syrian uprising begins", 
                conflict3);
            Event event5 = new Event(LocalDate.of(2013, 8, 21), "Damascus", "Chemical weapons attack", 
                conflict3);
            Event event6 = new Event(LocalDate.of(2003, 3, 20), "Baghdad", "US-led invasion begins", 
                conflict4);
            Event event7 = new Event(LocalDate.of(2011, 12, 18), "Baghdad", "US combat troops withdraw", 
                conflict4);

            eventRepository.save(event1);
            eventRepository.save(event2);
            eventRepository.save(event3);
            eventRepository.save(event4);
            eventRepository.save(event5);
            eventRepository.save(event6);
            eventRepository.save(event7);
        }
    }
}
