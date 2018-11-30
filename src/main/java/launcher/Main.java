package launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static launcher.Menu.mainMenu;

public class Main {

    public static void main(String[] args) throws Exception {

        Logger logger = LogManager.getLogger();
        logger.info("LANCEMENT DE L'APPLICATION..");

        mainMenu();
    }
}