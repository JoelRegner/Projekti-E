# Projektin nimi TicketGuru

Tiimi: Aitanova Azaliia, Karppinen-van Drongelen Riikka, Kumar Tejinder, Nguyen Khoa, Paajaste Maximus, Regnér Joel

## Johdanto

Johdantoon kirjoitetaan lyhyt, ytimekäs kuvaus siitä, mikä on projektin aihe,
kuka on asiakas (käyttäjä), mitä hän haluaa ja saa järjestelmältä, mitä
tekniikoita käytetään ja mitä konkreettisesti on valmiina, kun projekti päättyy.

-   Järjestelmän tarkoitus ja tiivis kuvaus siitä, mistä on kyse ja kenelle järjestelmä on tarkoitettu.
-   Toteutus- ja toimintaympäristö lyhyesti:  
    -   Palvelinpuolen ratkaisut ja teknologiat (esim. palvelinteknologia, mikä tietokantajärjestelmä on käytössä)
    -   Käyttöliittymäratkaisut ja teknologiat (esim. päätelaitteet: puhelin,
    täppäri, desktop)

## Järjestelmän määrittely

## Käyttäjäryhmät (roolit)

Lipunmyyjä
* Myy lippuja asiakkaille myyntipisteessä
* Tulostaa liput
* Näkee tapahtumien lipputilanteen

Ovitarkastaja
* Tarkistaa lipun koodin
* Merkitsee lipun käytetyksi
* Näkee lipun voimassaolon

Ylläpitäjä (Admin)
* Luo ja hallinnoi tapahtumia
* Määrittää lipputyypit ja hinnat
* Hallitsee käyttäjiä
* Tarkastelee myyntitietoja

## Käyttäjätarinat
Lipunmyyjä
* Lipunmyyjänä haluan myydä lipun asiakkaalle, jotta asiakas pääsee tapahtumaan.
* Lipunmyyjänä haluan tulostaa lipun, jotta asiakas saa fyysisen lipun.
* Lipunmyyjänä haluan nähdä jäljellä olevat liput, jotta voin seurata myyntitilannetta.

Ovitarkastaja
* Ovitarkastajana haluan skannata lipun koodin, jotta voin tarkistaa lipun aitouden.
* Ovitarkastajana haluan merkitä lipun käytetyksi, jotta samaa lippua ei voi käyttää uudelleen.

Ylläpitäjä
* Adminina haluan luoda tapahtumia, jotta lippuja voidaan myydä eri tapahtumiin.
* Adminina haluan määrittää lipun hinnat, jotta myynti voidaan hinnoitella oikein.






## Käyttöliittymä
Järjestelmä on selainpohjainen ja sitä käytetään ensisijaisesti myyntipisteen työasemalla. Käyttöliittymä toteutetaan yksinkertaiseksi ja nopeaksi, jotta lipunmyynti sujuu myös ruuhkatilanteissa.

### Päänäkymät:
* Kirjautuminen: käyttäjä kirjautuu sisään (lipunmyyjä / ovitarkastaja / admin).
* Tapahtumien valinta: lipunmyyjä näkee listan tapahtumista ja valitsee tapahtuman, johon myydään lippuja.
* Lipunmyynti: lipunmyyjä valitsee lipputyypin ja määrän, ja tekee myynnin.
* Tulostus: järjestelmä tulostaa lipun (tai liput) ja jokaisessa lipussa on yksilöllinen koodi.
* Ovitarkastus: ovitarkastaja syöttää tai skannaa lipun koodin ja järjestelmä näyttää onko lippu voimassa ja onko se jo käytetty.
* Admin-näkymä: admin hallinnoi tapahtumia, lipputyyppejä, hintoja ja käyttäjiä sekä tarkastelee myyntitietoja.

![alt text](Käyttöliittymäkaavio.png)

## Rajaukset (mitä ei tehdä tässä versiossa)
Tämän projektin nykyisessä toteutuksessa keskitytään lipunmyyntiin fyysisessä myyntipisteessä ja ovitarkastukseen. Seuraavat ominaisuudet eivät kuulu ensimmäiseen versioon:

* Maksujärjestelmien integraatiot (esim. korttimaksu- tai verkkopankkimaksut)
* Asiakkaiden käyttäjätilit ja kirjautuminen
* Sähköpostilipun lähetys (tässä versiossa liput tulostetaan myyntipisteessä)


## Tietokanta
TicketGuru-järjestelmä tallentaa tietokantaan tapahtumat, lipputyypit, yksittäiset liput, myyntitapahtumat sekä järjestelmän käyttäjät. Tietokannan avulla voidaan hallita lipunmyyntiä, tarkistaa lippujen aitous ja seurata myyntitietoja.

Tietokantamalli perustuu rautalankamalleihin, joissa esitetään lipunmyynti, tapahtumien hallinta ja myyntiraportointi.

![alt text](Tietokantakaavio.jpg)

### Event 

Event-taulu sisältää järjestelmän tapahtumat. Yksi tapahtuma voi sisältää useita lipputyyppejä. 


| Kenttä      | Tyyppi     | Kuvaus                     |
|-------------|------------|----------------------------|
| Id          | Int (PK)   | Tapahtuman id              |
| Name        | varchar    | Tapahtuman nimi            |
| venue       | varchar    | Tapahtuman paikka          |
| City        | varchar    | Kaupunki                   |
| start_time  | Datetime   | Tapahtuman alkamisaika     |

### Ticket_Type  
Ticket_Type-taulu sisältää tapahtumien lipputyypit ja hinnat. Lipputyyppi kuuluu aina yhdelle tapahtumalle. 

| Kenttä      | Tyyppi      | Kuvaus                                   |
|-------------|-------------|-------------------------------------------|
| Id          | Int (PK)    | Lipputyypin id                            |
| Event_id    | Int (FK)    | Viittaus Event-tauluun                    |
| description | Varchar     | Lipputyypin nimi (esim. Aikuinen)         |
| price       | Decimal     | Lipun hinta                               |

### Ticket  
Ticket-taulu sisältää yksittäiset liput ja niiden tarkastuskoodit. Yksi lippu kuuluu aina yhdelle lipputyypille. 


| Kenttä          | Tyyppi      | Kuvaus                                      |
|-----------------|-------------|----------------------------------------------|
| Id              | Int (PK)    | Lipun id                                     |
| ticket_type_id  | int (FK)    | Viittaus Ticket_Type-tauluun                 |
| sale_id         | int (FK)    | Viittaus Sale-tauluun (voi olla tyhjä)       |
| code            | varchar     | Lipun tarkastuskoodi                         |
| status          | varchar     | Lipun tila (VALID / USED)                    |
| used_at         | datetime    | Aika jolloin lippu käytetty                  |

### Sale / Order 
Sale-taulu sisältää myyntitapahtumat. Yksi myyntitapahtuma voi sisältää useita lippuja. 

| Kenttä       | Tyyppi      | Kuvaus                     |
|--------------|-------------|-----------------------------|
| Id           | Int (PK)    | Myyntitapahtuman id         |
| created_at   | Datetime    | Myynnin ajankohta           |
| total_amount | decimal     | Myynnin kokonaissumma       |
| seller_id    | int (FK)    | Viittaus User-tauluun       |

### User 
User-taulu sisältää järjestelmän käyttäjät. Käyttäjä voi olla lipunmyyjä, ovitarkastaja tai ylläpitäjä. 

| Kenttä        | Tyyppi     | Kuvaus              |
|---------------|------------|---------------------|
| Id            | Int (PK)   | Käyttäjän id        |
| username      | varchar    | Käyttäjätunnus      |
| password_hash | varchar    | Salasanan hash      |
| role          | varchar    | Käyttäjän rooli     |
