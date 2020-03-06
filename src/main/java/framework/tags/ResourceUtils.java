package framework.tags;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class ResourceUtils {
    public static FileReader getResourceFile(String resource){
        FileReader fileReader = null;
        URL urlOfResource = ResourceUtils.class.getClassLoader().getResource(resource);
        try{
            fileReader = new FileReader(new File(urlOfResource.getFile()));
        } catch (Exception e){
            throw new RuntimeException("No such resource file");
        }
        return fileReader;
    }
}
