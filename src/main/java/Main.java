

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        EventRecord.FunctionalityTSV functionalityTSV = new EventRecord.FunctionalityTSV();
        functionalityTSV.readFromTSV();
        System.out.println(functionalityTSV.getEvents());

        System.out.print("Enter a number: ");
        int minInfluence = scanner.nextInt();

        functionalityTSV.showHerosWithBiggerInfluence( minInfluence);

        functionalityTSV.showEventsAfterDate();
    }
}
