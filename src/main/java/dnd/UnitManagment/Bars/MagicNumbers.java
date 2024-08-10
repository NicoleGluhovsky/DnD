package dnd.UnitManagment.Bars;

public enum MagicNumbers {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    TEN(10),
    TWENTYFIVE(25),
    FIFTY(50);

    private final int value;

    MagicNumbers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}