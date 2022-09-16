import java.util.Scanner;

public class Player extends Main{
    private String name;
    private char symbol;
    Player (String name, char symbol){
        this.name = name;
        this.symbol = symbol;
    }
    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
    public char getSymbol(){
        return symbol;
    }
    public char getOppisiteSymbol(){
        if (getSymbol()=='X')return 'O';
        else return 'X';
    }
    public String getName(){
        return name;
    }
    public void turn(){
        System.out.println(getName()+" move.");
        super.turn(super.field, symbol);
    }
    public boolean checkWin(){
        return super.checkWin(super.field, symbol);
    }
}