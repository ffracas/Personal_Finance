# personal-finance

## That claims this project

### Diagram ERM

![imagine](finance-diagram-erm.png)

### Sequence Diagram

```mermaid
sequenceDiagram
    participant Utente
    participant Backend
    participant Database
    Utente ->> Backend: Richiesta GET /dashboard/prospetto
    Backend ->> Database: Recupera dati patrimoniali
    Database -->> Backend: Restituisce dati patrimoniali
    Backend -->> Utente: Restituisce prospetto sintetico
    Utente ->> Backend: Richiesta GET /dashboard/obiettivi
    Backend ->> Database: Recupera obiettivi finanziari
    Database -->> Backend: Restituisce obiettivi finanziari
    Backend -->> Utente: Restituisce dati obiettivi
    Utente ->> Backend: Richiesta GET /dashboard/spese
    Backend ->> Database: Recupera spese nel lasso di tempo specificato
    Database -->> Backend: Restituisce dati spese
    Backend -->> Utente: Restituisce totale spese per categoria
    Utente ->> Backend: Richiesta GET /dashboard/entrate
    Backend ->> Database: Recupera entrate nel lasso di tempo specificato
    Database -->> Backend: Restituisce dati entrate
    Backend -->> Utente: Restituisce totale entrate per categoria
    Utente ->> Backend: Richiesta GET /budget/impostazione
    Backend ->> Database: Recupera impostazioni attuali del budget
    Database -->> Backend: Restituisce impostazioni budget
    Backend -->> Utente: Restituisce confronto spese attuali vs budget previsto
    Utente ->> Backend: Richiesta POST /budget/spese-ricorrenti
    Backend ->> Database: Registra nuova spesa ricorrente
    Database -->> Backend: Conferma registrazione
    Backend -->> Utente: Conferma avvenuta registrazione
    Utente ->> Backend: Richiesta POST /budget/spese-mensili
    Backend ->> Database: Registra nuova spesa mensile
    Database -->> Backend: Conferma registrazione
    Backend -->> Utente: Conferma avvenuta registrazione
    Utente ->> Backend: Richiesta POST /budget/investimenti
    Backend ->> Database: Registra nuova operazione di investimento
    Database -->> Backend: Conferma registrazione
    Backend -->> Utente: Conferma avvenuta registrazione
    Utente ->> Backend: Richiesta GET /movimenti
    Backend ->> Database: Recupera lista movimenti
    Database -->> Backend: Restituisce dati movimenti
    Backend -->> Utente: Restituisce lista movimenti
    Utente ->> Backend: Richiesta POST /movimenti
    Backend ->> Database: Registra nuovo movimento
    Database -->> Backend: Conferma registrazione
    Backend -->> Utente: Conferma avvenuta registrazione
    Utente ->> Backend: Richiesta POST /spese-ricorrenti
    Backend ->> Database: Aggiunge nuova spesa ricorrente
    Database -->> Backend: Conferma aggiunta
    Backend -->> Utente: Conferma avvenuta aggiunta
    Utente ->> Backend: Richiesta POST /spese-ricorrenti/debito-credito
    Backend ->> Database: Crea spesa ricorrente con debito/credito
    Database -->> Backend: Conferma creazione
    Backend -->> Utente: Conferma avvenuta creazione
```

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/personal-finance-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Mutiny ([guide](https://quarkus.io/guides/mutiny-primer)): Write reactive applications with the modern Reactive
  Programming library Mutiny
- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and
  Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on
  it.
- Reactive MySQL client ([guide](https://quarkus.io/guides/reactive-sql-clients)): Connect to the MySQL database using
  the reactive pattern

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

