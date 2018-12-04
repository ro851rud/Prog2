import java.io.File;

public class DirectoryPrintVisitor implements DiretoryVisitor {
    private int einrückungstiefe = 0;
    @Override
    public void enterDirectory(File dir) {
        einrückungstiefe += 1;
        System.out.println(dir.getPath());
    }

    @Override
    public void leaveDirectory(File dir) {
        einrückungstiefe -= 1;
    }

    @Override
    public void visitFile(File file) {
        for (int i = 0; i <= einrückungstiefe; i++) {
            System.out.printf("\t");
        }
            System.out.println(file.getName());
    }
}
