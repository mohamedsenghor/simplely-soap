# Architecture SOAP Microservice & Gateway

## 0. Technologies

Le projet est généré avec JHipster version 8.11.0 et est un microservice SOAP tournant sur le port 8085.

## 1\. 🏗️ Architecture et Flux de Requêtes

Ce projet met en œuvre une architecture de microservices JHipster, où le service SOAP est exposé au monde extérieur via une Gateway tournant sur le port 8080.

### A. Composants Clés

| Composant                               | Rôle                                                                                          | Technologie                                                                                             |
| :-------------------------------------- | :-------------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------------ | ---------------------------------- |
| **Gateway** (`localhost:8080`)          | Point d'entrée unique. Gère la sécurité (JWT), le routage dynamique et le proxy des requêtes. | JHipster (Spring Cloud Gateway)                                                                         |
| **Registry** (`localhost:8761`)         | Serveur de découverte de services (Eureka). Maintient l'état des microservices.               | JHipster Registry                                                                                       |
| **Microservice SOAP** (`locahost:8085`) |                                                                                               | Contient la logique métier (CRUD sur `Client`, `Transfer`, `Payment`) et expose les fonctions via SOAP. | JHipster (Spring Boot + Spring-WS) |

### B. Flux d'une Requête SOAP via la Gateway

1.  **Client (ex: SoapUI)** envoie une requête SOAP (XML) à la Gateway, en utilisant le chemin du service découvert
2.  La **Gateway** intercepte la requête, valide le token JWT (si la sécurité n'est pas contournée pour `/services/simplelysoap/soap/**`), et utilise le Service ID (`simplelysoap`) pour localiser le microservice via le Registry.
3.  La Gateway forwarde la requête (XML) au microservice
4.  Le **Microservice SOAP** reçoit la requête via le `MessageDispatcherServlet`.
5.  La classe `BanqueResource`, annotée `@Endpoint`, mappe le message SOAP (`{http://www.black.dev/banque}addClientRequest`) à la méthode Java correspondante (`addClient`) grâce à l'annotation `@PayloadRoot`.
6.  La logique métier est exécutée (ex: `clientService.save(clientDTO)`).
7.  La réponse est renvoyée en sens inverse.

---

## 2\. Documentation des Tests (SoapUI)

Les tests sont réalisés en utilisant **SoapUI** mais aussi Postman pour l'obtention du WSDL avec la Gateway et directement avec le Microservice.

### A. URL de Configuration

{WSDL URL} = http://localhost:8080/services/simplelysoap/soap/banque.wsdl

### Récupération du WSDL depuis la Gateway sur Postman

![Récupération du WSDL depuis la Gateway Postman](/images/gateway_wsdl.png)

#### Récupération du WSDL depuis le Microservice sur Postman

![Récupération du WSDL depuis le Microservice Postman](/images/internal_wsdl.png)

### B. Exemple de Test : `addClientRequest`

Cette opération est utilisée pour créer un nouveau client.

![Création d'un client sur SOAP UI](/images/create_client.png)

### C. Exemple de Test : `getSoldeRequest`

Cette opération permet de récupérer le solde d'un client existant.

![Récupération du solde du client nouvellement créé sur SOAP UI](/images/get_solde.png)
