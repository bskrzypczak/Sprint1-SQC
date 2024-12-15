# Sprint1-SQC
Repozytorium zawierające kod projektu Scenario Quality Checker, realizowanego na potrzeby przedmiotu Inżynieria Oprogramowania. Dla analityków dokumentujących wymagania funkcjonalne za pomocą scenariuszy nasza aplikacja SQC dostarczy informacji ilościowych oraz umożliwi wykrywanie problemów w wymaganiach funkcjonalnych zapisanych w postaci scenariuszy. Aplikacja będzie dostępna poprzez GUI, a także jako zdalne API dzięki czemu można ją zintegrować z istniejącymi narzędziami.

# Autorzy:
- Bartosz Skrzypczak 155832
- Jan Paluszkiewicz 155902
- Mateusz Górecki 155836
- Igor Taciak 155997

# Notacja scenariuszy:
- Scenariusz zawiera nagłówek określający jego tytuł i aktorów (zewnętrznych oraz system)
- Scenariusz składa się z kroków (każdy krok zawiera tekst)
- Kroki mogą zawierać pod-scenariusze (dowolny poziom zagłębień)
- Kroki mogą się zaczynać od słów kluczowych: IF, ELSE, FOR EACH

Przykład:  
Tytuł: Dodanie książki  
Aktorzy:  Bibliotekarz  
Aktor systemowy: System  

- Bibliotekarz wybiera opcje dodania nowej pozycji książkowej
- Wyświetla się formularz.
- Bibliotekarz podaje dane książki.
- IF: Bibliotekarz pragnie dodać egzemplarze książki
    - Bibliotekarz wybiera opcję definiowania egzemplarzy
    - System prezentuje zdefiniowane egzemplarze
    - FOR EACH egzemplarz:
        - Bibliotekarz wybiera opcję dodania egzemplarza
        - System prosi o podanie danych egzemplarza
        - Bibliotekarz podaje dane egzemplarza i zatwierdza.
        - System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
- Bibliotekarz zatwierdza dodanie książki.
- System informuje o poprawnym dodaniu książki.

# Obsługa REST na lokalnym serwerze:

## GUI
Aplikacja ScenarioQualityChecker oferuje analitykom również dostęp do przyjaznego użytkownikowi interfejsu graficznego, pod adresem http://localhost:8080 
 
 Z poziomu GUI użytkownik na możliwość podania nazwy pliku, który należy przeanalizować i za pomocą checkboxów wybiera jakie informacje mają zostać wyświetlone. Po wygenerowaniu wyniku aplikacja oferuje możliwość pobrania wygenerowanych danych w formacie JSON.
![image](https://github.com/user-attachments/assets/6d2b362b-29b2-448f-abcb-3ccc5de1a751)


## Obsługa funkcji bez użycia GUI

W ramach działania naszej aplikacji, użytkownik może na serwerze zobaczyć wyniki wykonania poszczególnych funkcji na interesującym go scenariuszu. Dostępne funkcje to:
- wyświetlenie ponumerowanego scenariusza                            http://localhost:8080/scenario/text?fileName=test4
- wyświetlenie liczby kroków w scenariuszu                           http://localhost:8080/scenario/number-of-steps?fileName=test4
- wyświetlenie liczby kroków zaczynających się od słów kluczowych    http://localhost:8080/scenario/key-words?fileName=test4
- wyświetlenie wszystkich powyższych informacji jednocześnie         http://localhost:8080/scenario/all-info?fileName=test4

Podane linki odnoszą się do przykładowego scenariusza znajdującego się w pliku test4.json. W przypadku chęci odczytania informacji z innego pliku wystarczy zmienić w linku "test4" na nazwę naszego pliku w formacie JSON (nie nalezy podawac rozszerzenia). 
