# Conception du jeu ICMon

Nous détaillons les choix de concéption que nous avons fait à chaque étape du projet ainsi que pour la partie 
extensions.

## Etape 1

Nous avons suivi la marche dictée par l'énoncé. 

## Etape 2

Nous avons suivi la marche dictée par l'énoncé. 

## Etape 3

### Ajout d'une interface : icmon.message.PauseMenuEventMessage. 

Cette interface nous permet de répondre aux éxigences de l'énoncé en effectuant, dans la classe ICMon, des traitements
spécifiques sur les messages de type PauseMenuEventMessage s'ils arrivent dans la boîte aux lettres du jeu.

En effet, les messages qui implémentent cette interface doivent pouvoir redéfinir une méthode getEvent() qui a pour type 
de retour un event de type PauseMenuEvent (final). Nous avons opté pour l'interface en supposant 
qu'un message pourrait contenir plusieurs évènements. 

Ainsi, on s'assure qu'un tel message **possède** bien un évènement qui implique un menu de pause. 

### Ajout d'une classe abstraite : icmon.events.PauseMenuEvent

Cette classe abstraite doit être implémentée par tous les évènements impliquant un menu de Pause. Ces évènements 
doivent pouvoir communiquer au jeu le menu de pause (final) qui les caractérisent.

Ils doivent donc redéfinir la méthode abstraite getPauseMenu() qui a pour type de retour un Menu de Pause.

## Etape 4

non traitée. 

## Extensions, partie fonctionnalités

### Impossible de combattre si le player ne posède aucun Pokemon dans son deck

Dans la classe Arena, nous avons enregistré les Pokemon's créés dans une liste : fightableActorsList. Nous avons 
ensuite défini une méthode Arena.wantsToAvoidFight(ICMonPlayer player) qui scanne si le player passé en paramètre 
se trouve sur la position d'un pokémon. Elle retourne un booléen.

Cette méthode est ensuite appelée par le jeu (classe ICMon) avec la méthode GameState.isCellInteractionWithFightCells()
qui à accès au joueur et à sa position. Dans znotre volonté de ne pas casser l'encapsulation, nous avons décidé de
définir cette méthode dans le GameState, classe à laquelle le player peut accéder. Le GameSate, en tant que classe 
imbriquée de ICMon à accès à tous ses attributs. Il n'est donc pas nécessaire de communiquer toutes les spécificités du 
jeu au player. 

Si le player se trouve sur l'emplacement d'un pokemon, un dialog s'affichera lui demandant de sortir de la zone de 
combat. Il pourra ensuite fermer ce dialogue seulement après être sorti de la zone de combat. Enfin, une fois sorti de 
la zone de combat, si le dialogue est encore ouvert, il ne peut plus se déplacer (évite de pouvoir se déplacer dans 
l'aire avec un dialog affiché). Pour se déplacer à nouveau, il doit fermer le dialogue. 

### Ouverture de dialog lors de changements d'aires

Lorsque le personnage change d'aire, un message spécifique à l'aire d'arrivée s'affiche et son déplacement est stoppé. 
Une fois que le player appui sur la touche espace, il peut à nouveau se déplacer. 

### Choix des pokémons au hasard 

Pour éviter de donner une chance au player de ne choisir que le meilleur pokémon pour chaque combat, à chaque fois 
qu'un combat se déclenche, le pokemon utilisé par le player pour combattre est choisi au hasard dans sa collection 
courante. Pour cela, nous avons recours à l'objet Random fournit par java.util


### Mécanique des combats

Pour donner une dynamique aux combats, un petit algorithme a été mis en place à l'état : OPPONENT_ACTION de 
l'automate à états finis présenté par l'énoncé. 

Nous avons pris soins de commenter directement chaque étape dans la classe ICMonFight.

Description synthétique :

Chaque Pokémon peut éfféctué 3 type d'actions : Une Action d'attaque, une action d'attaque Super et une action de
fuite (run away). Si le player choisi d'effectuer une Action qui n'est pas de type fuite, alors c'est à son adversaire 
de répondre. C'est ici que notre algorithme rentre en jeu.

Ce dernier dispose de 2 paramètres : ATTACK_ACTION_PROBABILITY et SUPER_ATTACK_PROBABILITY par défaut réglés à 0.9 et 0.18
respectivement. La contre-attaque du pokemon adverse possède donc 90% de chance d'être une action d'attaque (permet
de continuer le combat) et, s'il s'agit bien d'une action d'attaque, cette dernière possède 18% de chances d'être une
action d'attaque super. L'action de contre-attaque est ainsi choisi au hasard parmi les actions du pokémon adverse,
mais en ajoutant des paramètres sur ce choix. 
Nous avons opté pour l'utilisation d'objets de type Random pour réalisé cela.

A tritre d'exemple, si on veut que le pokémon adverse effectue tout le temps des actions d'attaques, il faut régler
les paramètres ATTACK_ACTION_PROBABILITY et SUPER_ATTACK_PROBABILITY sur 1.0 et 0.0 respectivement

L'utilisation, de la part du player, d'une attaque Super devient d'autant plus intéressante puisque le 
pokémon adverse à des chances de s'en aller avant la fin du combat ! Pour contrer une utilisation massive des 
attaques de type super, ces dernières infligent un "malus" d'utilisation au player et diminuent d'une unité sa barre 
de vie.

Ce dernier peut aussi être tenté d'utiliser une action de fuite s'il voit sa barre de vie diminuer puisqu'il existe une 
probabilité de 0.18 pour que le pokemon adverse inflige une attaque de type super au prochain tour !

Ce petit algorithme permet l'exploitation de toutes les actions proposées. 

Enfin, si le pokémon du player perd le combat, il est enlever de sa collection. 
L'arène est un lieu hostile. Si le player rentre dans l'arène, fait disparâitre tous les pokémon's et sort de l'aire. 
Alors les pokémon's réapparaissent une fois qu'il rentre à nouveau dans l'aire. 

### Attaques de type Super

Chaque pokémon est doté des attaques de bases (Classes AttackAction et RunAwayAction) dont les paramètres varient d'un pokemon
à l'autre, mais ils disposent aussi d'une Attaque de type Super qui leurs est spécifique. Nous avons pour cela définit 
la classe abstraite SuperAttackAction dont chaque classe SuperAttack spécifique à un pokémon hérite. 


Chaque classe "pokemon"+"superAttack" dispose d'un attribut qui lui est spécifique et qui représente soit les dégats que
le pokemon peut infliger en plus, soit les soins qu'il peut, le cas échéant, utilisé. 

### Disparition de la PokeBall

Si le Player passe devant la balle, ne l'attrape pas et sort de l'aire, alors, cette dernière disparaît. Il n'a pas 
saisi sa chance. On essaye, à défaut de temps, de garder le coté rare de la PokeBall.


## Extensions, partie Pokemon's

Nous avons ajouter 5 pokemon's.

Chaque Pokemon possède 3 caractéristiques : HPmax (ses HPs initiaux), Damage (les dégâts qu'il peut infliger) et une 
attaque de type Super qui lui est spécifique. 

| Pokemon    | HPmax  | Damage | Super Attack |
|------------|--------|--------|--------------|
| Bulbizarre | 20     | 1      | Damage = 3   |
| Latios     | 5      | 5      | Damage = 8   |
| Nidoqueen  | 15     | 2      | Damage = 4   |
| Ampharos   | 10     | 4      | Damage = 6   |
| Aron       | 45     | 0      | Damage = 3   |
| Azumarill  | 15     | 1      | Health +2    |
| Pikachu    | 10     | 2      | Damage = 8   |
| Qwilfish   | 10     | 2      | Health +3    |


Nous remercions toute l'équipe pédagogique pour ce semestre.

Ulysse Forest
Roman Guillemin


































