package launcher;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static launcher.Menu.mainMenu;

public class Main {

    public static void main(String[] args) throws Exception {

        Logger logger = LogManager.getLogger();
        logger.info("LANCEMENT DE L'APPLICATION..");

        PropertiesConfiguration config = new PropertiesConfiguration("src/main/resources/config.properties");

        if (args.length > 0 && args[0].equals("dev")){
            logger.info("Mode développeur activé");
            config.setProperty("devMode", "true");
            config.save();
        }

        mainMenu();
    }
}