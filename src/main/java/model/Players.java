package model;

public abstract class Players {
    private Integer id;
    private String name;
    private PlayerType type;
    private Symbol symbol;
    public abstract Move makeMove(Board board);

    public Players(Integer id, String name, PlayerType type,Symbol symbol){
        this.id = id;
        this.name = name;
        this.type = type;
        this.symbol = symbol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
