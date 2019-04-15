**Date de rendu : 15 mai 2019 à 23h59**  

### Consignes pour le fork
Les étapes à suivre par chaque équipe pour organiser correctement votre projet :
  1. Pour forker votre projet vous devez avoir consitué une équipe. __Un seul membre de l'équipe__ fera le fork (càd acceptera l'affectation du projet sur GitHub Classroom) : https://classroom.github.com/g/q0uQgVlr
  2. Au moment de l'acceptation de l'invitation, ce membre d'équipe choisira un nom d'équipe. Ce nom sera constitué des noms de famille de chaque membre accolés par ordre alphabétique. Par exemple, si les noms sont Dupont, Martin, Durand et Cornet, alors l'équipe s'appellera : `CornetDupontDurantMartin`.
  3. Ensuite, une fois le projet forké, l'étudiant invitera les autres membres de l'équipe un par un comme collaborateurs extérieurs (_Outside collaborator_) à ce dépôt. À vous de choisir si vous voulez qu'un d'entre vous (ou tous) ait les droits d'administration. À partir de ce moment vous pourrez travailler de façon collaborative sur votre dépôt.
    
### Consignes générales
* Pour que le projet soit fini, vous devez implémenter correctement l'ensemble de méthodes lévant une exception avec l'instruction `throw new RuntimeException("Not Implemented")`.
* Sauf indication contraire, il est interdit de _modifier_ les méthodes dont le code vous est fourni (celles qui n'ont pas de `throw new RuntimeException("Not Implemented")`).
* Vous pouvez _ajouter_ des fonctions utilitaires qui vous parraissent nécessaires. 
* Vous respecterez les bonnes pratiques en programmation objet vues en cours  
* Le respect des conventions de nommage du langage Java est **impératif**. Pour garantir le respect de ces aspects 
esthétiques, les IDE vous fournissent des outils de nettoyage, veillez à bien les utiliser avant le moindre partage de code.
* Votre base de code **doit être en permanence testé** et donc toujours être dans un état sain (le code compile et les tests passent). Comment tester votre programme ? Voici quelques consignes :

    1. En écrivant vos propres tests unitaires.  
    2. En exécutant les tests unitaires qui vous ont été fournis dans le repertoire `src/test/java` :
        * Tous ces tests sont annotés `@Disabled` et donc pour l'instant ils sont ignorés.
        * Au fur et à mesure de l'avancement de votre projet vous activerez chaque test (en supprimant l'annotation `@Disabled`) et vérifierez que ce test passe.
        * **Attention** : n'essayez pas de passer tous les tests en même temps ((principe _BabySteps_)
        * Ne faites pas de add/commit tant que des tests non-annotés `@Disabled` ne passent pas.
        * **Remarque** : soyez vigilants même si votre programme passe tous les tests car en règle générale un programme ne peut **jamais** être suffisamment testé...

* Certaines précisions ou consignes pourront être ajoutées ultérieurement et vous en serez informés.
* Surveillez l'activité sur [le forum](https://piazza.com/class/jpv7gf0lltk4kc), les nouvelles informations y seront mentionnées. N'hésitez pas à y poser également des questions surtout si vous pensez que ça peut intéresser les autres équipes.

### Conseils concernant la gestion de version
* Chaque commit devrait être accompagné d'un message permettant de comprendre l'objet de la modification.
* Vos _commits_ doivent être les plus petits possibles. Un commit qui fait 10 modifications de code sans lien entre elles, devra être découpé en 10 _commits_.
* On vous conseille d'utiliser des _branches_ différentes (cf. le tutoriel vu en [TP1](https://github.com/IUTInfoMontp-M2103/TP1)) lors du développement d'une fonctionnalité importante. Le nom de la branche doit être au maximum porteur de sens. Une fois que vous pensez que le code de la fonctionnalité est fini (tous les tests associés à celle-ci passent), vous fusionerez le code de sa branche avec la branche `master`.
