DROP SCHEMA IF EXISTS oblig3jk CASCADE;
CREATE SCHEMA oblig3jk;
SET search_path TO oblig3jk;

CREATE TABLE ansatt(

 ansattId SERIAL PRIMARY KEY,
 brukernavn VARCHAR(4) UNIQUE,
 fornavn VARCHAR(99),
 etternavn VARCHAR(99),
 ansettelse DATE,
 stilling VARCHAR(99),
 manedslonn INTEGER,
 avdeling INTEGER
);

CREATE TABLE avdeling(

 avdelingId SERIAL PRIMARY KEY,
 avdelingsNavn VARCHAR(99),
 avdelingsSjef INTEGER,

 CONSTRAINT avdelingsSjef_FK FOREIGN KEY (avdelingsSjef) REFERENCES ansatt(ansattId)
);

ALTER TABLE ansatt add FOREIGN KEY (avdeling) references avdeling(avdelingid);

CREATE TABLE prosjekt(

 prosjektId SERIAL PRIMARY KEY,
 prosjektNavn VARCHAR(99),
 beskrivelse VARCHAR(100)
);

CREATE TABLE prosjektDeltakelse(

 prosjektDeltakelseId SERIAL PRIMARY KEY,
 rolle VARCHAR(99),
 arbeidsTimer INTEGER,
 prosjektId INTEGER,
 ansattId INTEGER,

 CONSTRAINT prosjektId_FK FOREIGN KEY (prosjektId) REFERENCES prosjekt(prosjektId),
 CONSTRAINT ansattId_FK FOREIGN KEY (ansattId) REFERENCES ansatt(ansattId)
);

INSERT INTO avdeling(avdelingId , avdelingsNavn)
VALUES(DEFAULT, 'Bir'),
      (DEFAULT, 'Kongene'),
      (DEFAULT, 'HVL');

INSERT INTO ansatt(ansattId, brukernavn, fornavn, etternavn, ansettelse, stilling, manedslonn, avdeling)
VALUES(DEFAULT, 'KaMa', 'Karl', 'Marx', '10-10-10', 'Etande stilling', 33790, 2),
      (DEFAULT, 'JoGr', 'Jostein', 'Grinder', '2019-07-20', 'Entrepenor', 22000, 1),
      (DEFAULT, 'MoNi', 'Mohammed', 'Nilsen', '2010-12-12', 'Leder', 99999, 1),
      (DEFAULT, 'AuSe', 'Augustus', 'September', '1989-01-30', 'Lmao', 2, 1),
      (DEFAULT, 'KnIn', 'Knut', 'Inge', '1888-01-31', 'Geni', 1234567 , 2);


INSERT INTO prosjekt(prosjektId ,prosjektNavn , beskrivelse)
VALUES(DEFAULT, 'Bolle baking', 'Kanskje saft og?'),
      (DEFAULT, 'Korstog', 'Ta tilbake det hellige landet'),
      (DEFAULT, 'Spre missinformasjon', 'Gå på twitter');

INSERT INTO prosjektdeltakelse(prosjektDeltakelseId ,rolle, arbeidsTimer, prosjektId, ansattId)
VALUES(DEFAULT, 'Danny devito', 99, 1, 1),
      (DEFAULT, 'Arkitekt', 12, 1, 2),
      (DEFAULT, 'Frisør', 1, 3, 3),
      (DEFAULT, 'Suppe lager', 7, 2, 2),
      (DEFAULT, 'Bestikking', 9999, 3, 4);


UPDATE avdeling
set avdelingssjef = 4 where avdelingid = 2;

UPDATE avdeling
set avdelingssjef = 5 where avdelingid = 3;
