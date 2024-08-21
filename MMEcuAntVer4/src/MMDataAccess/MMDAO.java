package MMDataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MMDAO {

    private static final String FILE_PATH = "MMData/MMHormiguero.csv";

    public void mmSave(MMDTO hormiga) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(hormiga.toCSV());
            writer.newLine();
        }
    }

    public void mmFeed(MMDTO hormiga) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                MMDTO existingHormiga = MMDTO.fromCSV(line);
                if (existingHormiga.getRegNro() == hormiga.getRegNro()) {
                    // Actualizar la línea con los nuevos datos de hormiga
                    lines.add(hormiga.toCSV());
                } else {
                    lines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public void mmDelete(int regNro) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                MMDTO hormiga = MMDTO.fromCSV(line);
                if (hormiga.getRegNro() != regNro) {
                    lines.add(line);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
