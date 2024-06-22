package ABB;

import java.util.NoSuchElementException;

public class ABB<E extends Comparable<E>> {
    private No<E> raiz;

    public ABB() {
        this.raiz = null;
    }

    // Método para inserir um item na árvore
    public void inserir(E item) {
        raiz = inserirRec(raiz, item);
    }

    private No<E> inserirRec(No<E> raiz, E item) {
        if (raiz == null) {
            raiz = new No<>(item);
            return raiz;
        }
        if (item.compareTo(raiz.item) < 0) {
            raiz.esquerda = inserirRec(raiz.esquerda, item);
        } else if (item.compareTo(raiz.item) > 0) {
            raiz.direita = inserirRec(raiz.direita, item);
        }
        return raiz;
    }

    // 1) Caminhamento Pré-Ordem
    public void caminhamentoPreOrdem() {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        preOrdemRec(raiz);
        System.out.println();
    }

    private void preOrdemRec(No<E> no) {
        if (no != null) {
            System.out.print(no.item + " ");
            preOrdemRec(no.esquerda);
            preOrdemRec(no.direita);
        }
    }

    // 2) Caminhamento Pós-Ordem
    public void caminhamentoPosOrdem() {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        posOrdemRec(raiz);
        System.out.println();
    }

    private void posOrdemRec(No<E> no) {
        if (no != null) {
            posOrdemRec(no.esquerda);
            posOrdemRec(no.direita);
            System.out.print(no.item + " ");
        }
    }

    // 3) Caminhamento Decrescente
    public void caminhamentoDecrescente() {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        decrescenteRec(raiz);
        System.out.println();
    }

    private void decrescenteRec(No<E> no) {
        if (no != null) {
            decrescenteRec(no.direita);
            System.out.print(no.item + " ");
            decrescenteRec(no.esquerda);
        }
    }

    // 4) Obter o menor elemento
    public E obterMenor() {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        return obterMenorRec(raiz);
    }

    private E obterMenorRec(No<E> no) {
        if (no.esquerda == null) return no.item;
        return obterMenorRec(no.esquerda);
    }

    // 5) Clonar a árvore
    public ABB<E> clone() {
        ABB<E> novaArvore = new ABB<>();
        if (this.raiz != null) novaArvore.raiz = this.raiz.clone();
        return novaArvore;
    }

    // 6) Obter subconjunto de maiores ou iguais ao item
    public ABB<E> obterSubconjuntoMaiores(E item) {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        ABB<E> novaArvore = new ABB<>();
        obterSubconjuntoMaioresRec(raiz, item, novaArvore);
        if (novaArvore.raiz == null) throw new NoSuchElementException("Nenhum elemento encontrado");
        return novaArvore;
    }

    private void obterSubconjuntoMaioresRec(No<E> no, E item, ABB<E> novaArvore) {
        if (no != null) {
            if (no.item.compareTo(item) >= 0) {
                novaArvore.inserir(no.item);
                obterSubconjuntoMaioresRec(no.esquerda, item, novaArvore);
                obterSubconjuntoMaioresRec(no.direita, item, novaArvore);
            } else {
                obterSubconjuntoMaioresRec(no.direita, item, novaArvore);
            }
        }
    }

    // 7) Verificar se o item é a raiz
    public boolean ehRaiz(E item) {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        return raiz.item.equals(item);
    }

    // 8) Obter antecessor
    public E obterAntecessor(E item) {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        return obterAntecessorRec(raiz, item, null);
    }

    private E obterAntecessorRec(No<E> no, E item, E antecessor) {
        if (no == null) throw new NoSuchElementException("Item não encontrado");
        if (item.compareTo(no.item) == 0) {
            if (no.esquerda != null) {
                No<E> temp = no.esquerda;
                while (temp.direita != null) temp = temp.direita;
                return temp.item;
            } else if (antecessor != null) {
                return antecessor;
            } else {
                throw new NoSuchElementException("Não há antecessor");
            }
        } else if (item.compareTo(no.item) < 0) {
            return obterAntecessorRec(no.esquerda, item, antecessor);
        } else {
            return obterAntecessorRec(no.direita, item, no.item);
        }
    }
}
