package md.automation.core.properties;

import md.automation.core.anotations.Cfg;
import md.automation.core.exceptions.IllegalConfigException;
import md.automation.core.utils.PropertyReader;

import java.io.IOException;

public class GeneralProperties {
    private static GeneralProperties GENERAL_PROPS =null;
    private static final String propFileName = "props/general.properties";
    //Our properties/configs
    @Cfg
    public static String date;
    @Cfg
    public static String val_curs_data_file_location_en;
    @Cfg
    public static String val_curs_data_file_location_ro;
    @Cfg
    public static String val_curs_data_file_location_ru;
    @Cfg
    public static String chrome_driver;


    private GeneralProperties() throws Exception {
        PropertyReader.readProperties(propFileName,this.getClass());
    }



    public static GeneralProperties readProperty() throws Exception {
        if (GENERAL_PROPS == null) {
            //synchronized(GeneralProperties.class) {
            GENERAL_PROPS = new GeneralProperties();
            //}
        }
        return GENERAL_PROPS;
    }
}
