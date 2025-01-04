package model;

import java.util.Scanner;

public class HumanPlayer extends Players{
    private Integer Level;
    private Integer coins;
private Scanner scanner;

    public HumanPlayer(Integer id,String name, char symbol) {
        super(id,name,PlayerType.HUMAN,new Symbol(symbol));
        this.Level = 1;
        this.coins = 100;
scanner = new Scanner(System.in);
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Move makeMove(Board board) {
        System.out.println("Please enter the row in which you want to make move");
        int row = scanner.nextInt();
        System.out.println("Please enter the column in which you want to make move");
        int column = scanner.nextInt();
       return new Move(new Cell(row,column),this);

    }
}
