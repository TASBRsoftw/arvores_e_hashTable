package HASH;

public class Entrada<K, V> {
    private K chave;
    private V valor;
    private boolean removido;

    public Entrada(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
        this.removido = false;
    }

    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }
}
