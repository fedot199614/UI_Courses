package md.automation.core.utils;

import md.automation.core.enums.Language;
import md.automation.core.exceptions.IllegalConfigException;
import md.automation.core.properties.GeneralProperties;
import md.automation.core.properties.LinksProperties;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class FileManager {
    public static void writeToFile(String content, String filePath) throws IOException {
        BufferedWriter myWriter = new BufferedWriter(new FileWriter(filePath));

        myWriter.write(content);
        myWriter.close();
    }

    public static String  readFromFile(String filePath) throws IOException {
        BufferedReader myReader = new BufferedReader(new FileReader(filePath));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = myReader.readLine() )!= null){
            result.append(line).append("\n");
        }
        myReader.close();
        return result.toString();
    }

    public static String  readFromFileBasedOnLang(String lang) throws IOException {
        if (Language.RO.getFullName().equals(lang)) {
            return readFromFile(Language.RO.getDataFilePath());
        } else if (Language.RU.getFullName().equals(lang)) {
            return readFromFile(Language.RU.getDataFilePath());
        } else if (Language.EN.getFullName().equals(lang)) {
            return readFromFile(Language.EN.getDataFilePath());
        } else {
            throw new IllegalStateException("Unexpected value: " + lang);
        }
    }

}
