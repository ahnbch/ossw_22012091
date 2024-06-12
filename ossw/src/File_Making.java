import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class File_Making {
    public void saveFile(List<Info> infoList, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Info info : infoList) {
                writer.write(info.toString());
                writer.newLine();
            }
        }
    }
}
