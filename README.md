# ☕ Projet_mobile_imane_baider - Application Rüya

Ce dépôt contient l'application mobile **Rüya**, une boutique en ligne spécialisée dans la vente de tasses à café, développée en **Kotlin** avec **Jetpack Compose**. L’application suit l’architecture **MVI (Model - View - Intent)** pour garantir une organisation claire du code et une facilité de maintenance.

---

## 🏫 FACULTÉ POLYDISCIPLINAIRE À LARACHE  
### 📚 Projet de Fin de Module Développement Mobile

- 👩‍💻 **Réalisé par :** BAIDER Imane  
- 👨‍🏫 **Encadré par :** Pr. KOUISSI Mohamed  

---

## 🌟 Présentation du projet

**Rüya** est un mot turc qui signifie **"le rêve"**.  
L'idée derrière cette application est que chaque tasse de café vous transporte vers un **nouveau rêve**.  
L'application Rüya offre une **expérience utilisateur fluide, moderne et élégante**, mettant en valeur chaque produit de façon raffinée.

---

## 🎯 Objectifs du projet

- ✅ Afficher une liste de produits (tasses à café)  
- ✅ Permettre la consultation des détails de chaque produit  
- ✅ Intégrer une navigation fluide entre les écrans  
- ✅ Ajouter une barre de navigation personnalisée (**MardinNavBar**)  
- ✅ Offrir une expérience utilisateur élégante, claire et intuitive  

---

## 🛠️ Technologies utilisées

- ⚙️ **Kotlin**  
- 🎨 **Jetpack Compose**  
- 🧠 **Architecture MVI (Model - View - Intent)**  
- 🔧 **Gradle**  

---

## 🧠 Architecture MVI

J'ai utilisé l'architecture **MVI (Model-View-Intent)** pour assurer un **flux de données unidirectionnel** dans l'application.

- Le **ViewModel** joue le rôle de **gestionnaire des intentions (Intents)** envoyées par la vue.
- Il gère également **l'état de l'interface (State)** à l’aide de `MutableStateFlow`.

🔄 **Fonctionnement du flux** :

1. Lorsqu’un utilisateur effectue une action (par exemple : chargement des produits), une **intention** est envoyée au `ViewModel`.
2. Le `ViewModel` appelle alors le **Repository** pour récupérer les données.
3. Selon le résultat, il émet un **nouvel état** :
   - `Loading` : chargement en cours  
   - `Success` : données chargées avec succès  
   - `Error` : erreur lors de la récupération  

👁️ La **vue (UI)** observe uniquement cet **état** pour afficher le contenu approprié.

✅ Ce modèle permet de structurer le code de manière :
- claire  
- prévisible  
- facilement maintenable et extensible  

![Capture de l'application](https://raw.githubusercontent.com/imanebaider/Projet_mobile_imane_baider/refs/heads/main/mvi.webp)


## 📸 Aperçu de l'application

### 🛍️ Écran principal – Liste des produits
Cette page représente l'écran d'accueil de l'application Rüya, un e-commerce dédié aux tasses à café. Elle est construite avec Jetpack Compose et suit une architecture moderne basée sur MVI. Elle permet aux utilisateurs de rechercher, visualiser et ajouter des produits à leur panier ou à leurs favoris. Une barre supérieure, un menu latéral personnalisable, une bannière d’accueil et une grille responsive de produits rendent l'expérience utilisateur agréable. Le code prend également en charge les interactions comme l’ajout au panier, la gestion des favoris et la navigation entre les différentes pages via un drawer. Le design suit une identité visuelle élégante aux tons roses et violets, ciblant principalement un public féminin.
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya1.PNG?raw=true)

### 🔍 recherche un tasse
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya2.PNG?raw=true)
##  Menu latéral de l’application Rüya – Navigation principale
![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya3.PNG?raw=true)
## Écran d’inscription – Création de compte Rüya
Interface épurée permettant aux utilisateurs de créer un compte avec vérification du mot de passe
![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya4.PNG?raw=true)
## Écran d'authentification – Connexion à l’application Rüya
Interface élégante permettant aux utilisateurs de se connecter avec validation des champs
![Capture de l'application](
https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya5.PNG?raw=true )
## Écran du profil utilisateur
Affiche les informations de l’utilisateur connecté avec un design doux et épuré, incluant un avatar circulaire et un bouton de déconnexion.
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya7.PNG?raw=true )
## Écran de détail produit 
Présente les informations détaillées d’un produit avec image, nom, prix, description 
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya6.PNG?raw=true )
## Gestion des favoris et du panier – Ajout de produits dans Rüya
![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya8.PNG?raw=true )
## Écran des favoris – Gestion des produits favoris dans Rüya
Affiche la liste des produits ajoutés aux favoris avec possibilité de consulter les détails ou de supprimer un produit
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya9.PNG?raw=true  )
## Retirer un produit de la liste des favoris
![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya10.PNG?raw=true )
## Gestion du panier
Cette page affiche les produits ajoutés au panier avec leurs quantités. L’utilisateur peut sélectionner un ou plusieurs produits, ajuster les quantités, supprimer des articles, visualiser le total des articles sélectionnés, et passer la commande. L’interface offre un contrôle simple et intuitif pour gérer son panier avant la validation finale de la commande.
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya11.PNG?raw=true  )
## Validation de la commande et choix du paiement

Cette page permet à l'utilisateur de saisir ses informations personnelles (nom, adresse de livraison, téléphone) et de choisir une méthode de paiement parmi plusieurs options (carte bancaire, PayPal, paiement à la livraison). Le bouton de confirmation est activé uniquement lorsque tous les champs obligatoires sont remplis, facilitant ainsi la finalisation de la commande.
![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya12.PNG?raw=true)
## Écran de paiement

Cette page permet à l'utilisateur de saisir les informations de sa carte bancaire (numéro, nom du titulaire, date d'expiration, CVV) pour effectuer un paiement sécurisé. Une fois le paiement confirmé, un message de succès s'affiche avec la confirmation que la commande est en cours de traitement.
![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya13.PNG?raw=true )

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya14.PNG?raw=true)

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya15.PNG?raw=true)
## gestion des commandes
Cette page affiche toutes les commandes passées par l’utilisateur. Pour chaque commande, on voit la date, les détails des produits achetés avec la quantité et le prix unitaire, ainsi que le total par produit. Il est aussi possible de supprimer une commande de la liste.


![Capture de l'application]( 
https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya16.PNG?raw=true)



## 🚀 Comment exécuter l'application


1. Cloner le dépôt :
```bash
git clone https://github.com/imanebaider/Projet_mobile_imane_baider.git
