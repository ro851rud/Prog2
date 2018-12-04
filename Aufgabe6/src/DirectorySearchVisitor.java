import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectorySearchVisitor implements DiretoryVisitor {

    private String extension;

    private List<String> searchList = new ArrayList<String>();

    public void setExtension(String s) {
        this.extension = s;
    }

    public void clearSearchList() {
        searchList = null;
    }

    public List<String> getSearchList() {
        return searchList;
    }

    @Override
    public void enterDirectory(File dir) {

    }

    @Override
    public void leaveDirectory(File dir) {

    }

    @Override
    public void visitFile(File file) {
        if (file.toString().endsWith(extension)){
            searchList.add(file.toString());
        }
    }
}
