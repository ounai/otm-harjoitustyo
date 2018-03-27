# Vaatimusmäärittely

## Tarkoitus

NysseTulee-sovelluksella käyttäjä pystyy nopeasti tarkistamaan koska seuraavat julkisen liikenteen välineet kulkee hänen suosikkipysäkeiltään. Käyttäjä pystyy luomaan erilaisia profiileja (esim. koti, työpaikka) joista jokaiselle on mahdollista tallentaa ja katsoa erillinen lista pysäkkejä. Sovellus käyttää Digitransitin avoimen datan rajapintaa tiedonhakuun, ja tallentaa tietoja (kuten profiilit ja suosikkipysäkit) SQL-tietokantaan. Sovellus toimii alustavasti vain HSL-alueella, mutta jatkokehityksessä sen voi mahdollistaa toimimaan myös kaikkialla muualla Suomessa missä Digitransitin API on käytössä.

## Käyttäjät

Sovellus on tarkoitettu vain yhden henkilön tai talouden käyttöön, ja siten ohjelmalla on vain peruskäyttäjän käyttäjärooli.

## Toiminnallisuus

### Perusnäkymä

- Näyttää suosikkipysäkkien reaaliaikaiset aikataulut seuraaville lähdöille
- Antaa mahdollisuuden rajoittaa pysäkkilistan profiilien avulla
- Kertoo liikennehäiriöt, erityisesti korostettuna jos sellainen koskee suosikkipysäkiltä lähtevää linjaa
- Tarjoaa painikkeet joista pääsee hakemaan pysäkkejä tai linjoja

### Pysäkkihaku

- Antaa hakea pysäkkiä nimellä tai pysäkkitunnuksella
- Esittää hakutulokset tiiviinä listana, kertoo pysäkistä nimen, pysäkkitunnuksen ja linjat jotka käyttää pysäkkiä
- Yksittäisestä hakutuloksesta pääsee katsomaan pysäkin aikataulua ja lisäämään sen suosikkeihin (yhteen tai useampaan profiiliin)

### Linjahaku

- Antaa hakea linjaa tunnuksen perusteella
- Kertoo jokaisesta tuloksesta tunnuksen ja reitin
- Yksittäisestä hakutuloksesta pääsee katsomaan tarkempia tietoja linjasta, sekä listaa reitin yksittäisistä pysäkeistä, yksittäisestä pysäkistä taas pääsee samaan näkymään kuin pysäkkihausta

## Jatkokehitys

- Sovelluksen saa toimimaan koko Suomessa vaihtamalla rajapintaa, mutta tällöin olisi mielekästä että käyttäjä valitsee ensin pysäkin alueen, jotta haku toimisi helpommin
