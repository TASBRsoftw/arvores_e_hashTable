package AVL;

import java.util.NoSuchElementException;

public class AVL<E extends Comparable<E>> {
    private NoAVL<E> raiz;

    public AVL() {
        this.raiz = null;
    }

    // Método para obter a altura de um nó
    private int altura(NoAVL<E> no) {
        return no == null ? 0 : no.altura;
    }

    // Método para obter o fator de balanceamento de um nó
    private int fatorBalanceamento(NoAVL<E> no) {
        return no == null ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    // Método para rotacionar à direita
    private NoAVL<E> rotacaoDireita(NoAVL<E> y) {
        NoAVL<E> x = y.esquerda;
        NoAVL<E> T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        return x;
    }

    // Método para rotacionar à esquerda
    private NoAVL<E> rotacaoEsquerda(NoAVL<E> x) {
        NoAVL<E> y = x.direita;
        NoAVL<E> T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        return y;
    }

    // Método para inserir um item na árvore AVL
    public void inserir(E item) {
        raiz = inserirRec(raiz, item);
    }

    private NoAVL<E> inserirRec(NoAVL<E> no, E item) {
        if (no == null) {
            return new NoAVL<>(item);
        }
        if (item.compareTo(no.item) < 0) {
            no.esquerda = inserirRec(no.esquerda, item);
        } else if (item.compareTo(no.item) > 0) {
            no.direita = inserirRec(no.direita, item);
        } else {
            return no; // Item duplicado não é permitido
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        int balance = fatorBalanceamento(no);

        // Se o nó ficar desbalanceado, então existem 4 casos

        // Caso Esquerda-Esquerda
        if (balance > 1 && item.compareTo(no.esquerda.item) < 0) {
            return rotacaoDireita(no);
        }

        // Caso Direita-Direita
        if (balance < -1 && item.compareTo(no.direita.item) > 0) {
            return rotacaoEsquerda(no);
        }

        // Caso Esquerda-Direita
        if (balance > 1 && item.compareTo(no.esquerda.item) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        // Caso Direita-Esquerda
        if (balance < -1 && item.compareTo(no.direita.item) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    // 1) Caminhamento Pré-Ordem
    public void caminhamentoPreOrdem() {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        preOrdemRec(raiz);
        System.out.println();
    }

    private void preOrdemRec(NoAVL<E> no) {
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

    private void posOrdemRec(NoAVL<E> no) {
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

    private void decrescenteRec(NoAVL<E> no) {
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

    private E obterMenorRec(NoAVL<E> no) {
        if (no.esquerda == null) return no.item;
        return obterMenorRec(no.esquerda);
    }

    // 5) Clonar a árvore
    public AVL<E> clone() {
        AVL<E> novaArvore = new AVL<>();
        if (this.raiz != null) novaArvore.raiz = this.raiz.clone();
        return novaArvore;
    }

    // 6) Obter subconjunto de maiores ou iguais ao item
    public AVL<E> obterSubconjuntoMaiores(E item) {
        if (raiz == null) throw new NoSuchElementException("A árvore está vazia");
        AVL<E> novaArvore = new AVL<>();
        obterSubconjuntoMaioresRec(raiz, item, novaArvore);
        if (novaArvore.raiz == null) throw new NoSuchElementException("Nenhum elemento encontrado");
        return novaArvore;
    }

    private void obterSubconjuntoMaioresRec(NoAVL<E> no, E item, AVL<E> novaArvore) {
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

    private E obterAntecessorRec(NoAVL<E> no, E item, E antecessor) {
        if (no == null) throw new NoSuchElementException("Item não encontrado");
        if (item.compareTo(no.item) == 0) {
            if (no.esquerda != null) {
                NoAVL<E> temp = no.esquerda;
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
