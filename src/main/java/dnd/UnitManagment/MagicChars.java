package dnd.UnitManagment;

public enum MagicChars {
    WALL('#'), 
    EMPTYSPACE('.'), 
    PLAYER('@'),
    LannisterSolider('s'), 
    LannisterKnight('k'),
    QueensGuard('q'),
    Wright('z'),
    BearWright('b'),
    GiantWright('g'),
    WhiteWalker('w'),
    TheMountain ('M'),
    QueenCersei('C'),
    NightsKing('K'),
    BonusTrap('B'),
    QueensTrap('Q'),
    DeathTrap('D'),
    DEAD('X');
    


    private final char value;

    MagicChars(char symbol) {
        this.value = symbol;
    }
    
    public char getSymbol() {
        return value;
    }
}
