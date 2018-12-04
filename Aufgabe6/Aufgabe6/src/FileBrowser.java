// Robin Rudorf
// 08.05.2018

import java.io.File;
import java.util.List;

public class FileBrowser {


    public static void main(String[] args) {
        File dir = new File("C:\\Users\\robin\\Desktop\\Studium AIN\\2. Semester\\Programmiertechnik II\\Skript");
        System.out.println("DirectoryPrintVisitor: \n");
        traverse(dir, new DirectoryPrintVisitor());

        /*File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isDirectory())
                System.out.println("Verzeichnis: " + f.getParent() + "/" + f.getName());
            else
                System.out.println("Datei: " + f.getParent() + "/" + f.getName());
        }
        */
        // Ihr Code: ....
        /*List<String> list = new LinkedList<>();
        dirSearch(dir, ".pdf", list);
        for (String pfad : list) {
            System.out.println(pfad);
        }*/
        DirectorySearchVisitor v = new DirectorySearchVisitor();
        v.setExtension(".pdf");
        traverse(dir, v);
        List<String> list = v.getSearchList();
        System.out.println("DirectorySearchVisitor: \n");
        for (String s : list) {
            System.out.println(s);
        }

    }

    public static void traverse(File dir, DiretoryVisitor visitor) {
        File[] files = dir.listFiles();
        visitor.enterDirectory(dir);
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    //System.out.println(f.getAbsolutePath());
                    traverse(f,visitor);
                } else if (f.isFile()) {
                    visitor.visitFile(f);

                }
            }
        }
        visitor.leaveDirectory(dir);
    }

    /*public static void dirPrint(File file) {
     	// Ihr Code: ....

        File dir = new File ("C:\\Users\\RobinRudorf\\Desktop\\Rie Schrank");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getPath());
                if (f.isDirectory()) {
                    System.out.println(f.getAbsolutePath());
                    dirPrint(f);
                } else if (f.isFile()) {

                    System.out.println(f.getName());
                }
            }
        }

    }

    public static void dirSearch(File file, String extension, List<String> list) {
    	// Ihr Code: ....
        File dir = new File ("C:\\Users\\RobinRudorf\\Desktop\\Rie Schrank");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    dirPrint(f);
                } else if (f.isFile()) {
                    if (f.toString().endsWith(extension)) {
                        double fileSize = f.length() / 1024d;
                        list.add(f.toString() + "\nGroese der Datei: " + fileSize + "kb");
                    }
                }
            }
        }

    }*/

}