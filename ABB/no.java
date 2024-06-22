package ABB;

class No<T extends Comparable<T>> {
    T item;
    No<T> esquerda, direita;

    public No(T item) {
        this.item = item;
        this.esquerda = null;
        this.direita = null;
    }

    // Método para clonar o nó
    public No<T> clone() {
        No<T> novoNo = new No<>(this.item);
        if (this.esquerda != null) novoNo.esquerda = this.esquerda.clone();
        if (this.direita != null) novoNo.direita = this.direita.clone();
        return novoNo;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
