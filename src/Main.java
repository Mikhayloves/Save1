import java.io.*;
import java.util.List;
import java.util.function.ToDoubleBiFunction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        List<String> list = List.of("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save.dat",
                "C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save1.dat",
                "C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save2.dat");
        GameProgress gameProgress = new GameProgress(94, 10, 2, 254.3);
        GameProgress gameProgress1 = new GameProgress(80, 20, 30, 200);
        GameProgress gameProgress2 = new GameProgress(91, 35, 10, 158);
        saveGames("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save.dat", gameProgress);
        saveGames("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save1.dat", gameProgress1);
        saveGames("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save2.dat", gameProgress2);
        zipFiles("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/zip_out.zip", list);
        deleteFile("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save.dat");
        deleteFile("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save1.dat");
        deleteFile("C:/Users/PC USER/IdeaProjects/Zagruzka1/Games/savegames/save2.dat");


    }

    public static void saveGames(String way, GameProgress gameProgress) throws IOException {
        var file = new File(way);
        if (file.createNewFile()) ;
        try (FileOutputStream fileOutputStream = new FileOutputStream(way);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(gameProgress);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void zipFiles(String outputZipFilePath, List<String> filePaths) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputZipFilePath))) {
            for (int i = 0; i < filePaths.size(); i++) {
                File file = new File(filePaths.get(i));
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    static void deleteFile(String delete) {
        File file = new File(delete); {
            for (int i = 0; i < file.length(); i++) {
                if (file.delete()){
                    System.out.println("Файлы вне папки удалены");
                } else {
                    System.out.println("Файлов нет");
                }
            }
        }
    }
}
