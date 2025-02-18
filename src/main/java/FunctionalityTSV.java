

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

// Enum for Houses
enum Confrontation {
    INDIVIDUELL , TEAM, GALAKTISCH ,  MULTIVERSAL;

    public static Confrontation fromString(String value) {
        return Confrontation.valueOf(value.toUpperCase());
    }
}

// Model class for event records
@Getter
@Setter
class EventRecord {
    private int id;
    private String held;
    private String antagonist;
    private Confrontation confrontation;
    private String location;
    private String datum;
    private double influence;

    public EventRecord(int id, java.lang.String held, java.lang.String antagonist, Confrontation confrontation, java.lang.String location, String datum, double influence) {
        this.id = id;
        this.held = held;
        this.antagonist = antagonist;
        this.confrontation = confrontation;
        this.location = location;
        this.datum=datum;
        this.influence = influence;
    }

    @Override
    public java.lang.String toString() {
        return "EventRecord{" +
                "id=" + id +
                ", held='" + held + '\'' +
                ", antagonist='" + antagonist + '\'' +
                ", confrontation=" + confrontation +
                ", location='" + location + '\'' +
                ", datum=" + datum  + '\'' +
                ", influence=" + influence +
                '}';
    }


    // Functionality class to read from the TSV file
public static class FunctionalityTSV {
    private final List<EventRecord> events = new ArrayList<>();

    /**
     * Reads event records from a TSV file
     * @throws IOException if the file cannot be read
     */
    public void readFromTSV() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\pc\\IdeaProjects\\RESCozacRaluca731Aufgabe1\\src\\main\\java\\marvel_konfrontationen.tsv"));
        for (String line : lines.subList(1, lines.size())) { // Skip header row
            String[] parts = line.split("\t");
            if (parts.length == 7) {
                events.add(new EventRecord(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        Confrontation.fromString(parts[3]),
                        parts[4],
                        parts[5],
                        Double.parseDouble(parts[6])
                ));
            }
        }
    }

    public List<EventRecord> getEvents() {
        return events;
    }



    public void showHerosWithBiggerInfluence(double minInfluece) {
        events.stream()
                .filter(event -> event.getInfluence() >= minInfluece)
                .forEach(System.out::println);

    }

    public void showEventsAfterDate() {
        LocalDate thresholdDate = LocalDate.parse("2021-11-13");

        events.stream()
                .filter(event -> event.getLocation().equals("GALAKTISCH"))
                .filter(event -> LocalDate.parse(event.getDatum()).isAfter(thresholdDate)) //  Filter by date
                .sorted(Comparator.comparing(EventRecord::getDatum)) // Sort ascending
                .forEach(event -> System.out.println(event.getDatum() + ": " + event.getHeld() + " vs " + event.getAntagonist()));
    }

//        public void saveConfruntationCounts() throws IOException {
//            Map<String, Integer> hospitalCasesCounts = new HashMap<>();
//
//            // Step 1: Count events for each hospital
//            for (EventRecord event : events) {
//                Confrontation hospital = event.getConfrontation();
//
//                // If hospital exists in map, increase count
//                if (hospitalCasesCounts.containsKey(hospital)) {
//                    int currentCount = hospitalCasesCounts.get(hospital);
//                    hospitalCasesCounts.put(hospital, currentCount + 1);
//                }
//                // If hospital is not in map, add it with count 1
//                else {
//                    hospitalCasesCounts.put(hospital, 1);
//                }
//            }
//
//            // Step 2: Sort hospitals by event count (descending)
//            List<Map.Entry<String, Integer>> sortedHospitals = new ArrayList<>(hospitalCasesCounts.entrySet());
//            sortedHospitals.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort by count descending
//
//            // Step 3: Write results to "ergebnis.txt"
//            List<String> outputLines = new ArrayList<>();
//            for (Map.Entry<String, Integer> entry : sortedHospitals) {
//                outputLines.add(entry.getKey() + "#" + entry.getValue());
//            }
//            Files.write(Paths.get("src/main/java/xml/ergebnis.txt"), outputLines);
//        }

}}