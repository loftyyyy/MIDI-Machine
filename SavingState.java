import java.io.*;
import java.sql.SQLOutput;

public class SavingState implements Serializable {
    private String name = "Rho";

    public static void main(String[] args){
        try {
            SavingState currentState = new SavingState();
            FileOutputStream file = new FileOutputStream("save.txt");
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(currentState);
            os.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}