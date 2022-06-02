package eightpuzzle;

public class Enemy {
    private int coluna;
    private int linha;
    private char enemyType;
    private boolean temArmadilha;
    private boolean temChave;
    private char onTopOf='.';


    public Enemy( int linha, int coluna, char enemyType) {
        this.coluna = coluna;
        this.linha = linha;
        this.enemyType = enemyType;
    }

    public Enemy(int linha, int coluna, char enemyType, char onTopOf) {
        this.coluna = coluna;
        this.linha = linha;
        this.enemyType = enemyType;
        this.onTopOf=onTopOf;
    }

    public char getOnTopOf() {
        return onTopOf;
    }

    public void setOnTopOf(char onTopOf) {
        this.onTopOf = onTopOf;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "coluna=" + coluna +
                ", linha=" + linha +
                '}';
    }

    public boolean isTemArmadilha() {
        return temArmadilha;
    }

    public void setTemArmadilha(boolean temArmadilha) {
        this.temArmadilha = temArmadilha;
    }

    public boolean isTemChave() {
        return temChave;
    }

    public void setTemChave(boolean temChave) {
        this.temChave = temChave;
    }

    public char getEnemyType() {
        return enemyType;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    @Override
    protected Enemy clone()  {
        return new Enemy(linha, coluna, enemyType,onTopOf);
    }
}
