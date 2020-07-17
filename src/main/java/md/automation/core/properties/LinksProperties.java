package md.automation.core.properties;

import md.automation.core.anotations.Cfg;
import md.automation.core.enums.Language;
import md.automation.core.exceptions.IllegalConfigException;
import md.automation.core.utils.PropertyReader;

import java.io.IOException;

public class LinksProperties {
    private static LinksProperties LINKS_PROPERTIES_INSTANCE =null;
    private static final String propFileName = "props/links.properties";
    //Our properties/configs
    @Cfg
    public static String bnm_ro_official_exchange_link;
    @Cfg
    public static String bnm_ru_official_exchange_link;
    @Cfg
    public static String bnm_en_official_exchange_link;
    @Cfg
    public static String home_page;


    public static String getURLBasedOnLang(String lang){
        if (Language.RO.getFullName().equals(lang)) {
            return bnm_ro_official_exchange_link;
        } else if (Language.RU.getFullName().equals(lang)) {
            return bnm_ru_official_exchange_link;
        } else if (Language.EN.getFullName().equals(lang)) {
            return bnm_en_official_exchange_link;
        } else {
            throw new IllegalStateException("Unexpected value: " + lang);
        }
    }


    private LinksProperties() throws Exception {
        PropertyReader.readProperties(propFileName,this.getClass());
    }

    public static LinksProperties readProperty() throws Exception {
        if (LINKS_PROPERTIES_INSTANCE == null) {
            //synchronized(LinksProperties.class) {
                LINKS_PROPERTIES_INSTANCE = new LinksProperties();
            //}
        }
        return LINKS_PROPERTIES_INSTANCE;
    }

}

