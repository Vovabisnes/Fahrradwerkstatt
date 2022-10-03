import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Auftrag> list = new ArrayList<>();
    private static int fertigStellung = 0;
    private static int warteZeit = 0;
    private static int durschnittlicheWartezeit = 0;
    private static int maximaleWartezeit = 0;
    private static Time currentTime;

    public static void main(String[] args) {
        String fileName = "C:\\fahrradwerkstatt0.txt";
        readFile(fileName);
        work();
    }

    public static void readFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String read;
            while((read = reader.readLine()) != null){
                String [] array = read.split(" ");
                list.add(new Auftrag(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
            }
        } catch (Exception e){
            System.out.println("File does not exist");
        }
    }

    public static void work(){
        currentTime = new Time();

        for (Auftrag auftrag: list) {
            currentTime.setDays(auftrag.getEingangsZeitPunkt() / 1440);
            currentTime.setMinutes(auftrag.getEingangsZeitPunkt() - currentTime.getDays() * 1440);
            int atWork = auftrag.getAuftragsDauer();
            warteZeit = 0;
            while (atWork > 0) {
                if (currentTime.getMinutes() >= 540 && currentTime.getMinutes() <= 1020) {
                    int temp = atWork;
                    atWork = atWork - 1020 + currentTime.getMinutes();
                    if (atWork < 0) {
                        currentTime.setMinutes(currentTime.getMinutes() + temp);
                        warteZeit += temp;
                    } else {
                        warteZeit += 1020 - currentTime.getMinutes() + 960;
                        currentTime.setMinutes(540);
                        currentTime.setDays(currentTime.getDays() + 1);

                    }
                } else {
                    if (currentTime.getMinutes() > 1020 && currentTime.getMinutes() <= 1440) {
                        warteZeit += 1440 - currentTime.getMinutes() + 540;
                        currentTime.setMinutes(540);
                        currentTime.setDays(currentTime.getDays() + 1);
                    } else {
                        warteZeit += 540 - currentTime.getMinutes();
                        currentTime.setMinutes(540);
                    }
                }
            }
            if (auftrag.getEingangsZeitPunkt() > fertigStellung){
                auftrag.setWarteZeit(warteZeit);
                auftrag.setFertigStellung(auftrag.getEingangsZeitPunkt() + warteZeit);
            } else {
                auftrag.setWarteZeit(fertigStellung - auftrag.getEingangsZeitPunkt() + warteZeit);
                auftrag.setFertigStellung(auftrag.getWarteZeit() + auftrag.getEingangsZeitPunkt());
            }
            fertigStellung = auftrag.getFertigStellung();
            if (maximaleWartezeit < auftrag.getWarteZeit()) maximaleWartezeit = auftrag.getWarteZeit();
            durschnittlicheWartezeit+=auftrag.getWarteZeit();

            System.out.println(auftrag.toString() + " " + currentTime.getDays() + " " + currentTime.getMinutes());
        }
        durschnittlicheWartezeit /= list.size();
        System.out.println(durschnittlicheWartezeit + " " + maximaleWartezeit);
    }
}