import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

public class inputFile {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("output.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
             
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        String filePath = "output.txt";
        try (FileWriter fw = new FileWriter(filePath, true);  // Mở ở chế độ "append"
             BufferedWriter bw = new BufferedWriter(fw)) {
             
            bw.write("Đây là dòng mới sẽ được thêm vào cuối file.");
            bw.newLine();  // Thêm dòng mới
            bw.write("Đây là một dòng khác nữa.");
            System.out.println("Ghi thêm vào file thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

