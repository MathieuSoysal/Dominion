 # Remarques

## Personnelles

### Andy HOUILLON

Beaucoup de problèmes de connexion internet m'ont parfois empêché de pull / push 
(ce qui explique que certains commits soient de véritables 'packs' de code), et 
le temps passé à faire des "Tests" sur tout et rien via le Dominion Server ne peut être
vu via les commits.

### Amaury JASPAR

Aucune remarque.

### Mathieu SOYSAL

Aucune remarque.

## Code

#### Moat

Pour Moat nous avons crée une nouvelle classe `Attack` dans le package `fr.umontpellier.iut.dominion.cards.Type`
Cette classe hérite de `Card` et est parente de toutes les cartes de type `Attack`. 
Nous avons alors rédéfinis :
```java
public void play(Player p) {}
```

Cette méthode parcours la liste des joueurs de la partie et demande à ceux qui ont une carte `Moat` si ils veulent la
jouer. Si le joueur souhaite dévoiler sa Moat, il est retiré de la liste des joueurs affectés par l'attaque. Cette
dernière liste est ensuite utilisée par chaque carte attaque.

