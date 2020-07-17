package md.automation.core.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement(name="ValCurs")

public class ValCurs {
    @XmlAttribute(name = "Date")
    private String date;

    @XmlElement(name="Valute")
    private List<Valute> Valute;


    public String getDate() {
        return date;
    }

    public List<md.automation.core.dto.xml.Valute> getValute() {
        return Valute;
    }


}
