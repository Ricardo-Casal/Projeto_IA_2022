package eightpuzzle;

public class Enemy {
    private int coluna;
    private int linha;
    private char enemyType;
    private boolean temArmadilha;
    private boolean temChave;


    public Enemy( int linha, int coluna, char enemyType) {
        this.coluna = coluna;
        this.linha = linha;
        this.enemyType = enemyType;
    }

    public Enemy(int linha, int coluna, char enemyType, boolean temArmadilha, boolean temChave) {
        this.coluna = coluna;
        this.linha = linha;
        this.enemyType = enemyType;
        this.temArmadilha = temArmadilha;
        this.temChave = temChave;
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
        return new Enemy(linha, coluna, enemyType, temArmadilha, temChave);
    }
}
