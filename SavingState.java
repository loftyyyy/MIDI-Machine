import java.io.*;
import java.sql.SQLOutput;

public class SavingState implements Serializable {
    private String name = "Rho";

    public static void main(String[] args){
        try {
            SavingState currentState = new SavingState();
            FileOutputStream file = new FileOutputStream("save.ser");
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(currentState);
            os.close();

            FileInputStream fileInputStream = new FileInputStream("save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Object state = objectInputStream.readObject();

            SavingState test = (SavingState) state;

            System.out.printf(test.name);

            objectInputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}