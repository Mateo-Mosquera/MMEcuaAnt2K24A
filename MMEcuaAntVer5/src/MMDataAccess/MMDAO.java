package MMDataAccess;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MMDAO {

    private static final String FILE_PATH = "MMData/MMHormiguero.csv";

    public void mmSave(MMDTO hormiga) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(hormiga.toCSV());
            writer.newLine();
        }
    }

    
}
