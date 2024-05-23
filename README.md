# Description du jeu ICMon

Le jeu dispose de 3 aires : Town (Aire principale), Lab (Laboratoire) et Arena (l'arène de combat).

Le scénario est similaire à celui suggéré par l'énoncé (hors partie 4).

Lorsque le jeu est lancé, le player doit d'abord intéragir avec l'assistant. Ce dernier lui dit de collecter la
balle. Ensuite, si la balle est colléctée, il peut revenir vers l'assistant et ce dernier lui dira qu'il a réussi.

Le player est alors libre d'aller combattre dans l'arène. Il ne peut cependant pas combattre s'il possède aucun 
pokémon dans son deck. Pour déclencher un combat, il faut marcher sur le pokémon adverse. 

Si ce dernier ne saisit pas la balle et qu'il sort de l'aire (town), alors la balle disparaît. Il est contraint 
de relancer le jeu pour pouvoir à nouveau collecter la balle. 

## Combats

L'accent a été mis sur la dynamique des combats. Les contres-attaques de l'adversaire sont choisi de manière aléatoire 
parmi les attaques disponibles (voir fichier conception pour plus de détail).

Pour éviter de ne choisir que le meilleur pokémon dans le deck du player, celui-ci est choisi aléatoirement parmi 
les pokémon's disponibles. 

Si le pokemon du player perd un combat, alors ce dernier disparaît de la collection de pokemon du player. 

Enfin, l'arène est un lieu hostile. Pour chaque pokémon adverse, s'il est vaincu par le player (donc enlever de l'arène), 
et si le player sort et rentre à nouveau dans l'arène, alors le pokemon en question réapparait. 


### Contrôle des touches

Déplacement & séléction d'attaque : Flèches directionnelles
Fermer le dialogue : Space
Intéragir : L 
Déclencher un combat : Marcher sur le pokémon




