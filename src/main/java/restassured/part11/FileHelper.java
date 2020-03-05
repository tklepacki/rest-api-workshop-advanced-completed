package restassured.part11;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class FileHelper {

    private static final String folderPath = "src/test/resources/json/";

    public static String generateStringFromResource(String jsonFile) {
        String jsonBody = null;
        try {
            jsonBody = new String(readAllBytes(Paths.get(folderPath + jsonFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonBody;
    }

}