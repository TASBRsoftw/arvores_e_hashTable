package AVL;

class NoAVL<T extends Comparable<T>> {
    T item;
    NoAVL<T> esquerda, direita;
    int altura;

    public NoAVL(T item) {
        this.item = item;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;
    }

    // Método para clonar o nó
    public NoAVL<T> clone() {
        NoAVL<T> novoNo = new NoAVL<>(this.item);
        if (this.esquerda != null) novoNo.esquerda = this.esquerda.clone();
        if (this.direita != null) novoNo.direita = this.direita.clone();
        novoNo.altura = this.altura;
        return novoNo;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
