package eightpuzzle;

public class Hero {

    private int linha;
    private int coluna;



    public Hero(int linha, int coluna) {
        this.coluna = coluna;
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    @Override
    protected Hero clone() {
        return new Hero(linha,coluna);
    }
}
