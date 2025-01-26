package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;
import java.util.List;

/**
 * Klasa reprezentująca scenariusz
 * Scenariusz składa się z tytułu, aktora systemowego, listy aktorów oraz listy instrukcji
 */
public class Scenario implements Element{
    private String title;
    private String systemActor;
    private List<String> actors;
    private List<Instruction> instructions;

    /**
     * Konstruktor klasy `Scenario`
     *
     * @param title Tytuł scenariusza
     * @param systemActor Główny aktor systemowy
     * @param actors Lista aktorów uczestniczących w scenariuszu
     * @param instructions Lista instrukcji w scenariuszu
     */
    public Scenario(String title, String systemActor, List<String> actors, List<Instruction> instructions){
        this.title = title;
        this.systemActor = systemActor;
        this.actors = actors;
        this.instructions = instructions;
    }

    /**
     * Zwraca tytuł scenariusza
     *
     * @return Tytuł scenariusza jako String
     */
    public String getTitle(){
        return title;
    }

    /**
     * Zwraca głównego aktora systemowego scenariusza
     *
     * @return Nazwa aktora systemowego jako String
     */
    public String getSystemActor(){
        return systemActor;
    }

    /**
     * Zwraca listę aktorów uczestniczących w scenariuszu
     *
     * @return Lista nazw aktorów jako lista Stringów
     */
    public List<String> getActors(){
        return actors;
    }

    /**
     * Zwraca listę instrukcji w scenariuszu
     *
     * @return Lista obiektów klasy `Instruction` w scenariuszu
     */
    public List<Instruction> getInstructions(){
        return instructions;
    }

    /**
     * Akceptuje wizytatora i pozwala mu odwiedzić ten scenariusz
     *
     * @param visitor Obiekt implementujący interfejs `Visitor`, który odwiedza ten scenariusz
     */
    @Override
    public void accept(Visitor visitor){
        visitor.visitScenario(this);
    }
}
