
import java.io.File;


public interface DiretoryVisitor {

    public void enterDirectory(File dir);

    public void leaveDirectory(File dir);

    public void visitFile(File file);
}
