# Chat-Application
Dans mon projet j'ai réalisé deux package un pour le client et l'autre pour le serveur.
Le package client :
On a une class ClientThread qui crée pour chaque client un thread
et la classe Envoi : dans cette classe on a trois méthode envoiConnecter , envoiMessage  et send .
envoiConnecter elle récupéré le nom du client connecter puis  l’envoi au serveur pour détecter le port et l'adresse IP .
envoiMessage à pour argument le message envoyé et le non du client qui l'envoie et celui qu'on veut envoie ce message
send c'est elle qui transforme le packet au serveur

On a aussi trois formes la première pour login, deuxième pour l'enregistrement et la dernière pour le chat et la connexion.

Le package server :

Classe client qui regroupe les informations des clients connecter.
responder il reçois les message puis il le traite pour savoir si on veut se connecter ou bien envoyer un message.
Puis la classe ServerThread qui reçois le packet du client.
Partie Exécution :

Pour exécuter mon projet pour la partie server run la classe Server et la partie Client run la classe LoginForm.

