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