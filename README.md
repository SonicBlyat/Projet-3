# Projet 3 par Axel Allain [DA Java]

## LANCEMENT DE L'APPLICATION

Première solution :

- Télécharger le ZIP du projet
- Décompresser le ZIP
- Exécuter "run.bat" présent à la racine du projet

Seconde solution pour les utilisateurs d'IntelliJ IDEA uniquement :

- Cloner le repository ou télécharger le ZIP en cliquant sur "Clone or download"
- Décompresser le ZIP
- Importer le projet sur IntelliJ puis lancer le programme à partir de la classe Main

## CONFIGURATION

Pour modifier les paramètres de l'application :

- Ouvrir le fichier config.properties puis changer les valeurs souhaitées

Vous pouvez aussi activer le mode développeur en passant le paramètre "dev" au lancement de l'application :

- Pour IntelliJ, ouvrir l'onglet "Run" en haut puis cliquer sur "Edit Configurations..."
- Ajouter ceci dans Program arguments : dev

## JEUX

L'application comporte deux jeux, tous deux comportant trois modes différents :

- Mastermind (Les indices sont affichés de la manière suivante : X bien placés, X présents mais mal placés)
- Recherche +/- (Les indices sont affichés de la manière suivante : +=-- suivant la taille choisie dans le fichier de configuration)

## MODES

- Challenger (Vous devez trouver le code secret de l'ordinateur)
- Défenseur (L'ordinateur doit trouver votre code secret)
- Duel (Vous devez trouver le code secret de l'ordinateur avant qu'il ne trouve le votre. L'égalité est possible !)
