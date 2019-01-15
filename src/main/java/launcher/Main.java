package launcher;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe principale qui permet le lancement de l'application
 * Modification du fichier de configuration en cas de passage d'un argument dev
 *
 * @Author Axel Allain
 */
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

        Menu menu = new Menu();
        menu.mainMenu();
    }
}