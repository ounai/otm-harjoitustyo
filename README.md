#Harjoitustyö: NysseTulee

Digitransitin APIa hyödyntävä sovellus, jonka avulla voi nopeasti hakea tietoa pysäkeistä, lähdöistä ja linjoista.

## Dokumentaatio

* [Määrittelydokumentti](https://github.com/ounai/otm-harjoitustyo/blob/master/NysseTulee/dokumentaatio/VaatimusMaarittely.md)
* [Tuntikirjanpito](https://github.com/ounai/otm-harjoitustyo/blob/master/NysseTulee/dokumentaatio/TuntiKirjanpito.md)
* [Arkkitehtuuri](https://github.com/ounai/otm-harjoitustyo/blob/master/NysseTulee/dokumentaatio/Arkkitehtuuri.md)

## Käyttö komentoriviltä

### Testaaminen

```
mvn test
```

### Testien koodikattavuus

```
mvn jacoco:report
```

### Suoritettavan jar-tiedoston luominen

```
mvn package
```

### Checkstyle-tarkistus

```
mvn jxr:jxr checkstyle:checkstyle
```

### Javadocin luominen

```
mvn javadoc:javadoc
```
