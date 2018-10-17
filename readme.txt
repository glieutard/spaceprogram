Travail restant à effectuer :

Passer par une base de données virtuelle pour les tests ?
=> Pour le moment, nécessite de recréer la base avant de lancer les tests .... 




Prérequis :

Modification du .properties :
=> Si jar : src/main/resources
=> Si Run as .. Spring Boot APp : à la racine

Modifier le nom d'hôte
Modifier le port
Modifier l'url d'accès à la bdd
Modifier l'utilisateur et mot de passe si souhaité
=> Les scripts ont été prévus pour une base de données SqlServer. Aucune garantie n'est donnée pour les autres bases de données.
La variable speed.ratio=1 permet de modifier la vitesse de déplament des vaisseau en ajoutant un ratio.


Création de la base de données :

Créer la base avec le nom spécifié dans l'url
Créer l'utilisateur tel que dans le .properties en lui donnant l'accès db_owner à la nouvelle base de données.
=> Les tables et un premier jeu de données seront créés lors du lancement de l'application.
=> Si exécuté par l'option Run as .. Spring Boot App de Java, il faut activer flyway dans le .properties : flyway.enabled=true




Documentation :

Celle-ci est accessible à l'url : http://[nom_d_hote]:[port]/jsondoc-ui.html
Il faut ensuite saisir l'url suivante dans l'encadré [Insert here the JSONDoc URL] : http://[nom_d_hote]:[port]/jsondoc

JSON :
Des exemples sont fournis dans le dossier json.
Les descriptions peuvent être retrouvées dans la documentation.




Informations Générales :

Lorsque tout n'est pas enregistré, aucune erreur n'est renvoyée en dehors des erreurs SQL.
Il faut comparer la liste entrante et sortante pour determiner les anomalies.

POST : Il n'est possible d'enregistrer que des nouveaux enregistrements
PUT : Il n'est possible que de mettre à jour

Il est nécessaire de renseigner tous les champs à l'exception (en mise à jour) :
- Spaceships : crews, engines, modules
- Missions : spaceships
Pour ces deux entités, les listes sont conservées en mise à jour ssi à null;

Il n'est pas possible de supprimer un élément utilisé (ex: si un Job est associé à un Crew, on ne peut pas le supprimer).
Il n'est pas possible de créer ou modifier un Vaisseau qui a moins de deux moteurs.
Il n'est pas possible d'affecter un membre d'équipage à deux vaisseaux.
Il n'est pas possible d'envoyer un vaisseau dans deux missions différentes.
Il n'est pas possible d'affecter plusieurs fois le même membre d'équipage, un moteur ou un module à un même vaisseau.
Il n'est pas possible d'affecter plusieurs fois un vaisseau à une même mission.
Il n'est pas possible d'affecter des elements inexistants aux vaisseaux (crews, engines, modules, type) et aux missions (spaceships, type)

Il n'y a pas d'option de restauration d'un vaisseau à un état antérieur en tant que tel afin de rester dans la philosophie du RESTful.
Pour se faire, il suffit simplement de faire une mise à jour du vaisseau avec les informations de l'état antérieur.

Les status de mission sont figés. Il n'est donc possible que de les récupérer.
Ces statuts sont testés pour gérer les vaisseaux :
1 - Les vaisseaux peuvent être attachés mais restent à la base
2 - Les vaisseaux partent en mission et leurs coordonnées évoluent
3&4 - La mission est terminée, les vaisseaux sont détachés (simulant le retour à la base)

Les coordonnées des vaisseaux ne sont calculées que s'il y a un pilote dans le vaisseau (id 1 en dur).

Pour Spaceships et Missions, il est possible d'ajouter l'option detail == full pour retourner plus d'informations (get et get/id)
- spaceships : retourne les modules, engines et crews
- missions : retourne les vaisseaux (full without coordinates)
- missions/id/spaceships : retourne les coordonnées





















