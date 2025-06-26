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
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya1.PNG?raw=true)

### 🔍 Écran de détails – Produit sélectionné
![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya2.PNG?raw=true)

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya3.PNG?raw=true)

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya4.PNG?raw=true)

![Capture de l'application](
https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya5.PNG?raw=true )

![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya7.PNG?raw=true )

![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya6.PNG?raw=true )

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya8.PNG?raw=true )

![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya9.PNG?raw=true  )

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya10.PNG?raw=true )

![Capture de l'application](https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya11.PNG?raw=true  )

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya12.PNG?raw=true)

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya13.PNG?raw=true )

![Capture de l'application]( https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya14.PNG?raw=true)

![Capture de l'application]( 
https://github.com/imanebaider/Projet_mobile_imane_baider/blob/main/ruya16.PNG?raw=true)

![Capture de l'application]( )

## 🚀 Comment exécuter l'application


1. Cloner le dépôt :
```bash
git clone https://github.com/imanebaider/Projet_mobile_imane_baider.git
