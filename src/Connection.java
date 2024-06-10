import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Connection {
    String filename = "data.txt";

    public void writeToFile(String record) throws IOException {
        FileWriter file = new FileWriter(filename, true);
        BufferedWriter writer = new BufferedWriter(file);
        writer.write(record);
        writer.newLine();
        writer.close();
    }

    public void readFile() throws IOException {
        FileReader read = new FileReader(filename);
        int i;
        while ((i = read.read()) != -1) {
            System.out.print((char) i);
        }
        read.close();
    }
}
