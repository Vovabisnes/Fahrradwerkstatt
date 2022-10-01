import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Auftrag> list = new ArrayList<>();
    private static int fertigStellung = 0;
    private static int durschnittlicheWartezeit = 0;
    private static int maximaleWartezeit = 0;

    public static void main(String[] args) {
        String fileName = "C:\\fahrradwerkstatt0.txt";
        readFile(fileName);
        work();
        System.out.println(durschnittlicheWartezeit + " " + maximaleWartezeit);
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
        for (Auftrag auftrag : list){
            if (auftrag.getEingangsZeitPunkt() > fertigStellung){
                auftrag.setWarteZeit(auftrag.getAuftragsDauer());
                auftrag.setFertigStellung(auftrag.getEingangsZeitPunkt() + auftrag.getAuftragsDauer());
            } else {
                auftrag.setWarteZeit(fertigStellung - auftrag.getEingangsZeitPunkt() + auftrag.getAuftragsDauer());
                auftrag.setFertigStellung(auftrag.getWarteZeit() + auftrag.getEingangsZeitPunkt());
            }
            fertigStellung = auftrag.getFertigStellung();
            if (maximaleWartezeit < auftrag.getWarteZeit()) maximaleWartezeit = auftrag.getWarteZeit();
            durschnittlicheWartezeit+=auftrag.getWarteZeit();
            System.out.println(auftrag.toString());
        }
        durschnittlicheWartezeit /= list.size();
    }
}