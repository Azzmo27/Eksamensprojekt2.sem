
# Eksamensprojekt for AlphaSolutions

## Introduktion
Dette er eksamensprojekt som er udviklet for AlphaSolutions. 

Intention for projekt er at skabe et kalkulationsværktøj, der giver brugerne mulighed for at opret,redigere og administrere projekter,subprojekter og opgaver. 



## Badges

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


## Funktioner 

- Opret, rediger og administrer projekter
- Oprette subprojekter og opgaver for at nedbryde større  projekter
- Sæt deadline for projekter, subprojekter og opgaver.



## Installation

Her kan projektet afprøves: https://examprojectalphasolution.azurewebsites.net/

Hvis der opleves problemer med at køre projektet ved blot at klikke på linket, eller hvis man ønsker at køre projektet på egen database, så skal der konfigureres databaseforbindelse manuelt. Her er en vejledning for dette:



Installer kalkulationsværktøj med github cloning 

```bash
  git clone https://github.com/Azzmo27/Eksamensprojekt2.sem.git
  cd Eksamensprojekt2.sem
```


Der skal haves en MySQL-database kørende på egen lokale maskine.
Vi anbefaler brugen af en kompatibel version af MySQL. 

Åben application.properties-fil i projektet


Tilpas application.properties-fil med din egne databaseforbindelsesoplysninger.


Sørg for, at  ConnectionManager-klasse bruger disse properties til at oprette forbindelse til databasen. 

Hvis disse trin er fulgt korret så kan projektet køre og forbindelse til databasen er vellykket.


## Brug af vores kalkulationsværktøj

Hvis man skal oprette et projekt med subprojekt og opgaver, så kan man følge denne guide for nemmere brug. 


1. Først bliver man mødt af tilmeldingssiden, hvor man enten kan registrere sig selv eller gå til loginsiden.
![Billede 29 05 2024 kl  06 14](https://github.com/Azzmo27/Eksamensprojekt2.sem/assets/144421512/0d743daf-efd8-469b-8065-d99eee514d74)

3. Efter tilmeldingsiden/login kan man oprette et projekt ved at tryk på `opret projekt`. 
![Billede 29 05 2024 kl  06 14 (1)](https://github.com/Azzmo27/Eksamensprojekt2.sem/assets/144421512/bab56a3b-0da4-4499-8323-26431ae1259c)
4. Efter at have trykket på knappen kommer du ind på oprettelsessiden, hvor du kan oprette dit projekt. Her skal du indtaste projektets navn, en beskrivelse, startdato og slutdato, og derefter trykke på `Opret projekt` for at færdiggøre oprettelsen.

5. Efter oprettelsen ledes du videre til en liste over alle dine oprettede projekter. For at tilføje et subprojekt til det valgte projekt, skal du trykke på `vælg projekter`

6. Så bliver du ledt hen til det pågældende projekt, hvor du kan se alle underprojekter og opgaver tilknyttet projektet. Her har du mulighed for at oprette et nyt underprojekt ved at trykke på "Opret nyt underprojekt" `Opret nyt underprojekt` 
 

7. I `Opret underprojekt` skal du indtaste navnet på dit subprojekt, en beskrivelse, start- og slutdato, og derefter trykke på "Opret nyt subprojekt". Herefter bliver du ført tilbage til det pågældende projekt, hvor du kan se dit nye subprojekt tilknyttet projektet.


8. For at oprette en opgave til det specifikke subprojekt skal du trykke på `Opret ny opgave`. Her skal du indtaste opgavens navn, en beskrivelse samt start- og slutdato, og derefter trykke på "Opret ny opgave". Efter oprettelsen bliver du ført tilbage til det pågældende projekt med det tilhørende subprojekt og de tilsvarende opgaver.
# kalkulationsværktøj API 

#### Forsiden

GET /

## Projekt API
API for opret,redigere og slet et projekt

### Opret et nyt projekt

POST /create

GET /create

### Slet et projekt

POST /{projectName}/delete

### Rediger projekt

GET /{projectName}/editProject

POST /editProject

## Subprojekt API

API for opret,redigere og slet et subprojekt

### Opret et subprojekt

GET /createSubProject/{projectId}

POST /createdSubProject

### Slet subprojekt

POST /deleteSubProject/{subProjectId}

### Rediger subprojekt

GET /editSubProject/{subProjectId}

POST /editSubProject/{subProjectId}

## Opgaver API

### Opret opgave

GET /createTask/{projectId}

POST /createTask/{projectId}

### Slet opgave

POST /tasks/{taskName}/delete

### Rediger opgave

GET /tasks/{taskName}/editTask

POST /editTask
## Bidrag

Vi værdsætter enhver form for bidrag til projektet!

Se `contributing.md` for måder at bidrage til projektet på.

Tak for interessen i at forbedre dette projekt!
## Forfattere

- [@NatashM](https://github.com/NatashM)
- [@Azzmo27](https://github.com/Azzmo27)
- [@Imsh0001](https://github.com/Imsh0001)
- [@abmo0002](https://github.com/abmo0002)



## Licens

 Dette projekt er licenseret under MIT-licensen - se her for detaljer og rettigheder: [MIT](https://choosealicense.com/licenses/mit/)

