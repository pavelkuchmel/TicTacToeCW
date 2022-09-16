import java.util.Scanner;

public class Main {
    static final int size = 3;
    static char[][] field = new char[size][size];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main main = new Main();
        Player player1 = new Player(initPlayerName(), initSymbol());
        Player player2 = new Player(initPlayerName(), player1.getOppisiteSymbol());
        initField(field);
        printField();
        while (true){
            int count = 0;
            //игровой цикл
            while (true) {
                //ход крестиков
                player1.turn();
                //вывести поле
                printField();
                //если победа крестиков - конец игры - прервать цикл
                if (player1.checkWin()) {
                    System.out.print(player1.getName() + " win!");
                    break;
                }
                //ничья - конец игры - прервать цикл
                if (checkDraw(field)) {
                    System.out.print("Draw!");
                    break;
                }
                //ход ноликов
                player2.turn();
                //вывести поле
                printField();
                //если победа ноликов - конец игры - прервать цикл
                if (player2.checkWin()) {
                    System.out.print(player2.getName() + " win!");
                    break;
                }
                //ничья - конец игры - прервать цикл
                if (checkDraw(field)) {
                    System.out.print("Draw!");
                    break;
                }
                count++;
            }
            System.out.print(" Quantity steps: "+count);
            break;
        }
    }
    static boolean checkDraw(char[][] field){
        boolean result = true;
        int position = 1;
        for (int i = 0;i < field.length; i++){
            for (int j = 0; j < field[i].length; j++){
                if (field[i][j] != intToChar(position)){
                    result = false;
                    position++;
                }
            }
        }
        return result;
    }
    static boolean checkWin(char[][] field, char symbol){
        boolean result = false;
        //если в любой строке три одинаковых символа
        for(int i = 0; i < field.length; i++){
            if (field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][2] == symbol){
                result = true;
                break;
            }
        }
        //если в любом столбце три одинаковых символа
        for(int j = 0; j < field[0].length; j++){
            if(field[0][j] == field[1][j] && field[1][j] == field[2][j]&& field[2][j] == symbol){
                result = true;
                break;
            }
        }
        //если на главной диагонали три одинаковых символа
        if(field[0][0] == field[1][1] && field[1][1] == field[2][2]&& field[2][2] == symbol)
            result = true;
            //если на побочной диагонали три одинаковых символа
        else if(field[0][2] == field[1][1] && field[1][1] == field[2][0]&& field[2][0] == symbol)
            result = true;
        return  result;
    }
    public void turn(char[][] field, char symbol){
        Scanner in = new Scanner(System.in);
        int posNum = 0;
        do {
            System.out.print("Enter position number: ");
            posNum = in.nextInt();
        }while (check(posNum, symbol));
    }
    public boolean check(int posNum, char symbol){
        boolean check = true;
        if(posNum < 1 || posNum > 9){
            printField();
            System.out.println("This position  is out of border.");
        }
        else {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    if (field[i][j] == intToChar(posNum)) {
                        field[i][j] = symbol;
                        check = false;
                        break;
                    }
                }
            }
            if (check){
                printField();
                System.out.println("This position is occupied.");
            }
        }
        return check;
    }
    static char intToChar(int var){
        String position1 = ""+var;
        return position1.charAt(0);
    }
    static void initField(char[][] field){
        char positionSymbol = '1';
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field[i].length; j++){
                field[i][j] = positionSymbol;
                positionSymbol++;
            }
        }
    }
    static void printLineSeparator(){
        for (int k = 0; k < 3; k++) {
            System.out.print("|---");
        }
        System.out.println('|');
    }
    static void printField() {
        for (int i = 0; i < field.length; i++) {
            printLineSeparator();
            for (int j = 0; j < field[i].length; j++){
                System.out.print("| "+field[i][j]+" ");
            }
            System.out.println('|');
        }
        printLineSeparator();
    }
    static String initPlayerName (){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter player name: ");
        return in.nextLine();
    }
    static char initSymbol (){
        Scanner in = new Scanner(System.in);
        char symbol = ' ';
        do {
            System.out.print("Enter first player symbol (X or O): ");
            symbol = in.next().charAt(0);
            if(symbol != 'X' && symbol != 'O') System.out.println("Invalid symbol.");
        }while (symbol != 'X' && symbol != 'O');
        return symbol;
    }
}