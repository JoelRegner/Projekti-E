# TicketGuru API -dokumentaatio

## Johdanto

Tämä dokumentti kuvaa TicketGuru-järjestelmän REST-rajapintaa tapahtumien käsittelyyn. Rajapinta mahdollistaa tapahtumien hakemisen, lisäämisen, muokkaamisen, poistamisen ja hakemisen.

## Base URL

`http://localhost:8080/api`

## Autentikaatio

Ei vaadita tässä kehitysversionissa.

## Endpointit

### GET /api/events
Hakee kaikki tapahtumat järjestelmästä.

**Polkuparametrit:** Ei

**Query-parametrit:** Ei

**Request Body:** Ei

**Vastaus:**

| Tilakoodi | Kuvaus             |
|-----------|--------------------|
| 200 OK    | Lista tapahtumista |

### GET /api/events/{id}
Hakee yksittäisen tapahtuman ID:n perusteella.

**Polkuparametrit:**

| Nimi | Tyyppi | Kuvaus                  |
|------|--------|-------------------------|
| id   | Long   | Tapahtuman tunniste     |

**Query-parametrit:** Ei

**Request Body:** Ei

**Vastaus:**

| Tilakoodi     | Kuvaus                  |
|---------------|-------------------------|
| 200 OK        | Tapahtuman tiedot       |
| 404 Not Found | Tapahtumaa ei löytynyt  |

### POST /api/events
Luo uuden tapahtuman.

**Polkuparametrit:** Ei

**Query-parametrit:** Ei

**Request Body (JSON):**

```json
{
  "name": "Kevätmessut 2026",
  "venue": "Messukeskus",
  "city": "Helsinki",
  "startTime": "2026-04-10T10:00:00"
}
```

**Vastaus:**

| Tilakoodi       | Kuvaus                        |
|-----------------|-------------------------------|
| 201 Created     | Tapahtuma luotu onnistuneesti |
| 400 Bad Request | Virheellinen syöte            |

### PUT /api/events/{id}
Päivittää olemassa olevan tapahtuman.

**Polkuparametrit:**

| Nimi | Tyyppi | Kuvaus                  |
|------|--------|-------------------------|
| id   | Long   | Tapahtuman tunniste     |

**Query-parametrit:** Ei

**Request Body (JSON):** Päivitetyt tapahtuman tiedot

**Vastaus:**

| Tilakoodi     | Kuvaus                  |
|---------------|-------------------------|
| 200 OK        | Tapahtuma päivitetty    |
| 404 Not Found | Tapahtumaa ei löytynyt  |

### DELETE /api/events/{id}
Poistaa tapahtuman.

**Polkuparametrit:**

| Nimi | Tyyppi | Kuvaus                  |
|------|--------|-------------------------|
| id   | Long   | Tapahtuman tunniste     |

**Query-parametrit:** Ei

**Request Body:** Ei

**Vastaus:**

| Tilakoodi       | Kuvaus                  |
|-----------------|-------------------------|
| 204 No Content  | Poisto onnistui         |
| 404 Not Found   | Tapahtumaa ei löytynyt  |

### GET /api/events/search
Hakee tapahtumia nimen tai kaupungin perusteella.

**Polkuparametrit:** Ei

**Query-parametrit:**

| Nimi | Tyyppi | Kuvaus                                |
|------|--------|---------------------------------------|
| name | String | Suodattaa tapahtumat nimen mukaan     |
| city | String | Suodattaa tapahtumat kaupungin mukaan |

**Request Body:** Ei

**Vastaus:**

| Tilakoodi | Kuvaus             |
|-----------|--------------------|
| 200 OK    | Lista tapahtumista |

## Tapahtuma-objektin skeema
```json
{
  "id": "Long",
  "name": "String",
  "venue": "String",
  "city": "String",
  "startTime": "LocalDateTime",
  "ticketTypes": "List<TicketType>"
}
```

## Huomiot
- API käyttää H2 in-memory -tietokantaa kehitykseen, joten tiedot eivät säily uudelleenkäynnistyksen jälkeen.
- Kaikki vastaukset ovat JSON-muodossa.
- Päivämäärät ovat ISO-muodossa (esim. 2023-12-01T20:00:00).
