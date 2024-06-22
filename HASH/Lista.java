package HASH;
public class Lista<E> {
    private class Nodo {
        E elemento;
        Nodo proximo;

        Nodo(E elemento) {
            this.elemento = elemento;
            this.proximo = null;
        }
    }

    private Nodo cabeca;
    private int tamanho;

    public Lista() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    public void adicionar(E elemento) {
        Nodo novoNodo = new Nodo(elemento);
        novoNodo.proximo = cabeca;
        cabeca = novoNodo;
        tamanho++;
    }

    public boolean contem(E elemento) {
        Nodo atual = cabeca;
        while (atual != null) {
            if (atual.elemento.equals(elemento)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public boolean remover(E elemento) {
        if (cabeca == null) return false;

        if (cabeca.elemento.equals(elemento)) {
            cabeca = cabeca.proximo;
            tamanho--;
            return true;
        }

        Nodo atual = cabeca;
        while (atual.proximo != null && !atual.proximo.elemento.equals(elemento)) {
            atual = atual.proximo;
        }

        if (atual.proximo == null) return false;

        atual.proximo = atual.proximo.proximo;
        tamanho--;
        return true;
    }

    public int getTamanho() {
        return tamanho;
    }

    public E[] paraArray() {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[tamanho];
        Nodo atual = cabeca;
        int index = 0;
        while (atual != null) {
            array[index++] = atual.elemento;
            atual = atual.proximo;
        }
        return array;
    }
}
