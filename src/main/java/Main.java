

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FunctionalityTSV functionalityTSV = new FunctionalityTSV();
        functionalityTSV.readFromTSV();
        System.out.println(functionalityTSV.getEvents());
    }
}
