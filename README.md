# Revue du sprint effectué et principes SOLID appliqués lors du SPRINT

Ce projet a pour objectif de développer un jeu dit de "Concours de beauté". 

# Table of contents

1. Revue du sprint
2. Principes SOLID
3. Principe S
4. Principe O
5. Principe L
6. Principe I
7. Principe D


## Revue du sprint

Ce sprint s'attache à faire fonctionner le jeu en mode 2 joueurs uniquement, avec uniquement la stratégie de jeu propre au mode 2 joueurs. Dans les sprints suivant, on essaiera d'étendre le fonctionnement à un nombre supérieur (voir fixé) de joueurs. 

## Principes SOLID

Afin de garantir la robustesse du code, différents principes SOLID ont été mis en oeuvre pour la modélisation objet. 

* Files : contient les fichiers nécessaires à l'éxécution de **main**. 
    * Le fichier repertoire_banque qui corre

## Principe S

Single Responsability Principle. Une classe correspond à une seule responsabilité. On peut vérifier cela par la lecture des cartes CRC. Ou chaque classe possède des missions propores. Par exemple, la classe Joueur lit les messages reçus par le clientTCP pour notifier le JoueurGUI des modifications, permettant ainsi la mise à jour de l'interface graphique.

## Principe O

Open/close principle. 
Il existe le Protocole2Joueur, mais il pourrait aussi exister un Protocole3Joueurs et un Protocole4Joueurs etc. Chaque classe implémente l'interface IProtocole, ce qui permet le principe 0. Si l'on veut rajouter une manière de jouer, on rajoute un protocole, mais on ne modifie pas les autres. 

## Principe I

Interface segregation principle.
Avec l'approche une interface par rôle, il n'existe pas d’interfaces regroupant toutes les dépendances vers différents modules. 

## Principe D

D : Dependency inversion principle.
