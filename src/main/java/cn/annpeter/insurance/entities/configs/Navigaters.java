package cn.annpeter.insurance.entities.configs;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by annpeter on 3/11/16.
 *
 */
@Component
@XmlRootElement(name="navigaters")
public class Navigaters {
    List<Navigater> navigater;

    public List<Navigater> getNavigater() {
        if (navigater == null) {
            navigater = new ArrayList<Navigater>();
        }
        return navigater;
    }

    public void setNavigater(List<Navigater> navigater) {
        this.navigater = navigater;
    }


    @Override
    public String toString() {
        return "NavigaterList [navigater=" + navigater + "]";
    }
}