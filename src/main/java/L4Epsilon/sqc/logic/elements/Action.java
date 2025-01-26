package L4Epsilon.sqc.logic.elements;

import L4Epsilon.sqc.logic.visitors.Visitor;

/**
 * Klasa reprezentująca pojedynczą akcję w scenariuszu
 * Akcja to podstawowy element scenariusza, opisany tekstowo
 */
public class Action extends Instruction{
    private String text;

    /**
     * Konstruktor klasy `Action`
     * Inicjalizuje akcję z podanym tekstem
     *
     * @param text Tekst opisujący akcję
     */
    public Action(String text){
        this.text = text;
    }

    /**
     * Getter, który zwraca tekst opisujący akcję
     *
     * @return Tekst akcji jako String
     */
    public String getText() {
        return text;
    }

    /**
     * Akceptuje wizytatora i pozwala mu odwiedzić tę akcję
     *
     * @param visitor Obiekt implementujący interfejs `Visitor`, który odwiedza akcję
     */
    @Override
    public void accept(Visitor visitor){
        visitor.visitAction(this);
    }
}
