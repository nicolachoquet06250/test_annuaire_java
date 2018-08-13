package fr.nicolas.choquet.annuaire.utils;

import java.io.*;

public class File {
    private String path = "src/main/resources/";
    private static String staticPath = "src/main/resources/";
    private String fileName;

    public File(String file) {
        this.setFileName(file);
    }

    @org.jetbrains.annotations.Contract(pure = true)
    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean write(String text) throws IOException {
        String oldContent = read();
        FileWriter fw1 = new FileWriter(path + getFileName());
        BufferedWriter bw1 = new BufferedWriter(fw1);
        bw1.write(text);
        bw1.close();
        String newContent = read();

        return !oldContent.equals(newContent);
    }

    public String read() {
        java.io.File file = new java.io.File(path + getFileName());
        String retour = "";
        try {
            int car;
            StringBuilder builder = new StringBuilder();
            FileInputStream ftemp = null;
            ftemp = new FileInputStream(file);
            while( (car = ftemp.read()) != -1)
                builder.append((char)car);
            ftemp.close();
            retour = builder.toString();
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        return retour;
    }

    public String getPath() {
        return path;
    }

    public static String getStaticPath() {
        return staticPath;
    }
}
