package launcher;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        Logger logger = LogManager.getLogger();
        logger.info("LANCEMENT DE L'APPLICATION..");

        Menu menu = new Menu();
        menu.mainMenu();
    }
}