package launcher;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("dev")) {
                // SET modeDev DU CONFIG.PROPERTIES SUR TRUE
                File propertiesFile = new File("C:\\Users\\Admin\\Desktop\\Developpement\\Workspace\\Projet 3\\src\\main\\resources\\config.properties");
                PropertiesConfiguration config = new PropertiesConfiguration(propertiesFile);
                config.setProperty("modeDev", "true");
                config.save();
            }
        }
        if (args.length == 0) {
            // SET modeDev DU CONFIG.PROPERTIES SUR FALSE
            File propertiesFile = new File("C:\\Users\\Admin\\Desktop\\Developpement\\Workspace\\Projet 3\\src\\main\\resources\\config.properties");
            PropertiesConfiguration config = new PropertiesConfiguration(propertiesFile);
            config.setProperty("modeDev", "false");
            config.save();
        }

        Menu menu = new Menu();
        menu.mainMenu();
    }
}