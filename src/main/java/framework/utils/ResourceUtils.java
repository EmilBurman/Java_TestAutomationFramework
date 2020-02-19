package framework.utils;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class ResourceUtils {
    public static FileReader getResourceFile(URL resource){
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(new File(resource.getFile()));
        } catch (Exception e){
            throw new RuntimeException("No such resource file");
        }
        return fileReader;
    }
}
