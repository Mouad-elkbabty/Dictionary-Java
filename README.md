# Shrine #

* Mouad ElKbabty
* Gaétan Combe
* Fabien Vial
* Cyril Pigeyre
* Célia Segura
* Higo Medeiros
* Valentin Deffaugt

## Bienvenue sur le projet logiciel INF353

![Floppa](https://i.imgur.com/sW754gk.jpg)

Cette page est une ébauche de fichier README pour présenter le projet logiciel INF3 du groupe.

Vos enseignants vous demandent d'utiliser cette page pour tenir le journal de groupe de votre activité de développement logiciel. 
Un journal doit être tenu **au fur et à mesure** de l'avancement du travail. Il doit comporter a minima :

* les tâches envisagées lors de chaques séances, 
* la répartition des tâches entre les membres du groupe, 
* les difficultés rencontrées lors de l'avancement du projet,
* les modalités mises en oeuvre au sein du groupe pour résoudre les difficultés

Ne vous censurez pas, vous ne serez pas évalués sur vos difficultés, mais plutôt sur votre capacité à avoir consciences de ces difficultés et votre initiative pour mettre en oeuvre des solutions. Un groupe qui n'a pas de difficultés, cela n'existe pas, cela veut juste dire qu'il n'a pas forcément conscience des difficultés auquel il fait face.

La suite est un exemple de mise en forme, pour vous donner des éléments pour rédiger votre journal en [langage MarkDown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet).

## Semaine 0 - Découverte de Git

### Objectifs de la semaine

Vous pouvez utiliser :

1. des listes numérotées, 
    * des sous listes
    * avec différents points
1. le numéro étant géré par MarkDown
2. il suffit que ça commence par un chiffre...

### Faits marquants

Tous les faits marquants de cette semaine !

## Semaine 1 - MatriceIndexNaive et LecteurDocumentNaif

### Objectifs de la semaine

#### MatriceIndexNaive

##### Répartition

- constructeurs - Valentin, Gaétan : TESTÉS
- sauver() - Valentin, Gaétan, Cyril : TESTÉE
- val() - Gaétan, Cyril : TESTÉE
- incremente() - Gaétan, Cyril : TESTÉE
- affecte() - Gaétan, Cyril : TESTÉE

#### LecteurDocumentNaif

Voir semaine 2

### Faits marquants

Bonne concertation en début de séance pour s'organiser et préparer les classes, bonne répartition des tâches et bonne participation de chacun dans ce qui lui a été confié. Nous n'étions pas au courant du travail en plus à fournir.

## Semaine 2 - LecteurDocumentNaif et Indexation

### Objectifs de la semaine

#### LecteurDocumentNaif

##### Répartition

Par Valentin, Higo, Fabien, Mouad
- constructeur - TESTÉ
- demarrer() - TESTÉE
- avancer() - TESTÉE
- estSeperateur() - TESTÉE
- finDeSequence() - TESTÉE
- elementCourant() - TESTÉE

#### Indexation

##### Répartition

Par Valentin, Gaétan, Cyril
- constructeur - TESTÉS
- changerMatrice() - TESTÉE
- ajouterMot() - TESTÉE
- ajouterDocument() - TESTÉE
- affecte() - TESTÉE
- incremente() - TESTÉE
- val() - TESTÉE
- compter() - TESTÉE
- sauver() - TESTÉE
- charger() - TESTÉE

#### Recherche

###### Répartition

Par Fabien, Gaétan, Valentin
- constructeurs - À TESTER
- score() - À TESTER
- presentation() - À TESTER

#### MainRecherche et MainIndexation

###### Répartition

Par Fabien, Gaétan, Valentin

Tout a été testé et tout semble bien fonctionner

### Faits marquants

Bonne concertation, organisation des tâches et participation  de chacun dans ce qui lui a été confié lors du dernier cours.   

## Semaine 3 - Implémentation des classes en Hashcode

### Objectifs de la semaine

#### DictionnaireHash

Par Valentin, Célia, Gaétan, Fabien
- constructeur - TESTÉS
- vider() - TESTÉE
- indiceMot() - TESTÉE
- motIndice() - TESTÉE
- contient() - TESTÉE
- nbMots() - TESTÉE
- contientPrefixe() - TESTÉE
- plusLongPrefixeDe() - TESTÉE

#### MatriceHash

Par Fabien, Gaétan
- constructeurs - DONE
- sauver() - DONE
- val() - DONE
- incremente() - DONE
- affecte() - DONE

#### Recherche

Par Fabien
- constructeur - DONE
- score() - A MODIFIER (NOUVELLE TECHNIQUE DE CALCULE)
- presentation() - DONE
- 

#### MainRecherche et MainIndexation

- Première version fonctionnelle meme sur le corpus complet. Recherche d'environ 25 Seconde dependant de la recherche.
- Nécessité de re-Indexer pour prendre en compte la nouvelle version du lecteur

### Commentaire et specification

Ajout des commentaires  détailés pour tous les testes , ainsi que specifier tous les méthodes déja faites .

### Faits marquants

Très bonne communication, la répartition semble bonne.

## Semaine de projet - Lundi

### Répartition

Gaétan s'est occupé des calculs de similarité, notamment les facteurs log.
Il a aussi ajouté l'attribut occ dans la CelluleDictio et a donc changé les méthodes sauver, charger et ajouterMot. 

Célia a aussi aidé dans les calculs de similarité. Elle a ajouté l'attribut nbDoc dans la CelluleDictio et a aussi changer les méthodes sauver, charger, ajouterMot et incrementer en conséquence.

Fabien a commencé la construction du MainEval et a apporté plusieurs améliorations dans le MainRecherche. Il a aidé dans plusieurs pondérations locales et globales.

Higo et Mouad se sont mis à jour sur la compréhension du projet suite aux gros changements qui ont eu lieu sur le dictionnaire et la matrice. Ils ont aidé le groupe à la compréhension des formules de calcul de similarité et ont lancé les indexations.

Valentin a changé les méthodes de la MatriceHash suite à une spécification oubliée qui permet d'accélérer la recherche de la méthode val. Il a aussi contribué au MainEval, MainRecherche et Recherche précédemment commencé par Fabien.

En général, chaque personne du groupe s'est organisée pour continuer à travailler lorsqu'un calcul était en cours.

### Difficultés

Gaétan et Célia ont eu certains problèmes lors des calculs, notamment sur les points critiques du log qui renvoyaient donc des infinis.

Fabien a oublié d'avancer dans certains de ses schémas de recherche et a donc créé des boucles infinis. 

Valentin a fait de même et a donc perturbé les méthodes de MatriceHash.

Higo et Mouad ont toujours des problèmes à comprendre le projet, mais grâce à l'aide de Fabien et de Gaétan, ils se sentent mieux investis et comprennent mieux les enjeux.

### Faits marquants

Chaque problème a été étudié en petit groupe et personne n'est resté sans rien faire. Nous espérons continuer comme cela le reste de la semaine.
Grâce à un calcul ltn-ltn, nous sommes arrivés à une précision d'environ 15%.

## Semaine de projet - Mardi

### Répartition

Fabien, Gaétan et Higo se sont occupés de la normalisation cosinus. Ils cherchent à utiliser un calcul de similarité pouvant donner de bons résultats.

Valentin a maintenu le README (que vous voyez actuellement !).

Célia s'est intéressée à la stoplist.


Mouad et higo se sont plutôt dirigé vers les troncatures,ils ont alors aussi changer quelques méthodes en LecteurDocumentNaif et indexation  apres chacun a fait des testes sur les differents corpus , aprés ils sont dirigé vers les synonymes et  normalisation.

Bilan : les troncatures fonctionnent, la recherches fonctionnent. Il reste a mettre en place les autres calculs et les testés.
### Difficultés

Complexité sur les calculs de similarité. Le scoring était long ce qui a conduit a une refonte de la methode de score.

### Faits marquants
 
Taux de réussite sur le push précedent de 27,8% sur le corpus complet avec 500 element renvoyés par requete.


## Semaine de projet - Mercredi

 ### Répartition :
   
