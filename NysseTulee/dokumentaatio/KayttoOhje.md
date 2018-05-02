# Käyttöohje

Lataa tiedosto NysseTulee.jar uusimmasta julkaisusta ja suorita se komennolla

```
java -jar NysseTulee.jar
```

Sovellus aukeaa tekstikäyttöliittymään jossa sen eri ominaisuuksia pystyy heti käyttämään. Sovellus ohjeistaa käynnistäessä komentojen kanssa, tämän avustuksen saa uudelleen esiin komennolla "help".

## Liikennetiedotteiden hakeminen

Komennolla "alerts" näkee listan voimassa olevista liikennetiedotteista.

## Pysäkkien hakeminen

Komennolla "stopsearch" voi hakea pysäkkiä hakusanalla. Hakusanana voi käyttää pysäkin nimeä tai sen koodia. Esimerkiksi pysäkin "2233 Tuusulanväylä" löytää joko hakusanalla "Tuusulan" tai "2233". Hakutuloksista voi valita haluamansa sen numeron perusteella, ja tällöin sovellus hakee ja näyttää pysäkin seuraavat lähdöt. Lähtölistan voi päivittää komennolla "refresh" ja pysäkin voi lisätä suosikkeihin komennolla "add (profiilin nimi)", missä profiiliksi voi nimetä esimerkiksi "koti" tai "koulu".

## Reittejen hakeminen

Komennolla "routesearch" voi hakea reittejä hakusanalla.

## Profiilin näyttäminen

Kun on tallentanut pysäkin profiiliin, sen profiilin voi hakea komennolla "profiili (nimi)". Tällöin sovellus näyttää kyseiseen profiiliin kuuluvat pysäkit ja niiden seuraavat lähdöt.

