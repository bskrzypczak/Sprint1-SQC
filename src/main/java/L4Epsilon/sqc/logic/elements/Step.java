package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;
import java.util.List;
import java.util.ArrayList;

/**
 * Klasa reprezentująca krok w scenariuszu
 * Krok może zawierać zagnieżdżone instrukcje (kroki lub akcje) oraz opis w postaci tekstu
 */
public class Step extends Instruction{
    private List<Instruction> nextInstructions;
    private String text;

    /**
     * Konstruktor klasy Step
     * Inicjalizuje krok z listą zagnieżdżonych instrukcji oraz tekstem
     *
     * @param nextInstructions Lista instrukcji zagnieżdżonych w kroku (pusta lista jeśli null)
     * @param text Tekst opisujący krok
     */
    public Step(List<Instruction> nextInstructions, String text){
        this.nextInstructions = (nextInstructions != null) ? nextInstructions : new ArrayList<>();
        this.text = text;
    }

    /**
     * Zwraca listę zagnieżdżonych instrukcji w kroku
     *
     * @return Lista obiektów klasy Instruction zagnieżdżonych w kroku
     */
    public List<Instruction> getNextInstructions(){
        return nextInstructions;
    }

    /**
     * Zwraca tekst opisujący krok
     *
     * @return Tekst kroku jako String
     */
    public String getText(){
        return text;
    }

    /**
     * Akceptuje wizytatora i pozwala mu odwiedzić ten krok
     *
     * @param visitor Obiekt implementujący interfejs Visitor, który odwiedza ten krok
     */
    @Override
    public void accept(Visitor visitor){
        visitor.visitStep(this);
    }
}
