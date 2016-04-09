package cn.annpeter.insurance.entities.configs;

import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by annpeter on 3/25/16.
 */
@Repository
public class NavigaterTree {
    private Navigaters navigatersRoot;

    public NavigaterTree(){
        initTree();
        deepTree(navigatersRoot.getNavigater(), null);
    }

    private void deepTree(List<Navigater> navigaterList, Navigater tempRoot){
        if(navigaterList == null){
            return;
        }

        Iterator<Navigater> it = navigaterList.iterator();
        while (it.hasNext()){
            Navigater navigater = it.next();

            navigater.setParent(tempRoot);

            if(navigater.getNavigaters() != null){
                deepTree(navigater.getNavigaters().getNavigater(), navigater);
            }
        }
    }

    private void initTree(){
        try {
            String classPath = this.getClass().getResource("/").getPath();

            File file = new File(classPath+ "navigater.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Navigaters.class);

            Unmarshaller um = jaxbContext.createUnmarshaller();
            StreamSource streamSource = new StreamSource(file);

            navigatersRoot = (Navigaters) um.unmarshal(streamSource);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Navigaters getNavigatersRoot() {
        return navigatersRoot;
    }

    public void setNavigatersRoot(Navigaters navigatersRoot) {
        this.navigatersRoot = navigatersRoot;
    }
}
