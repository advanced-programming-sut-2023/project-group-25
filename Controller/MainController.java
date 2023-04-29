package Controller;

import java.io.*;
import java.util.ArrayList;

public class MainController {
    public ArrayList<String> readFileContent(String path) {
        ArrayList<String> content = new ArrayList<>();
        File Users = new File(path);
        try {
            FileReader fileReader = new FileReader(Users);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                content.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return content;
    }


    public void writeToFileContent(String path, ArrayList<String> content, boolean isAppend) {
        File Users = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(Users, isAppend);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter writer = new PrintWriter(bufferedWriter);
            for (int i = 0; i < content.size(); i++) {
                writer.println(content.get(i));
            }
            writer.close();
        } catch (Exception e) {
        }
    }

}
