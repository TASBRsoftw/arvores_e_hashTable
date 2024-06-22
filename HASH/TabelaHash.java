package HASH;

public class TabelaHash<K, V> {
    private Lista<Entrada<K, V>>[] tabela;
    private int capacidade;
    private int tamanho;
    private static final int TAMANHO_INICIAL = 11;
    private static final float FATOR_CARGA = 0.75f;

    @SuppressWarnings("unchecked")
    public TabelaHash() {
        this.capacidade = TAMANHO_INICIAL;
        this.tabela = new Lista[capacidade];
        this.tamanho = 0;
    }

    private int hash(K chave) {
        return Math.abs(chave.hashCode() % capacidade);
    }

    public void inserir(K chave, V valor) {
        if ((float) tamanho / capacidade >= FATOR_CARGA) {
            rehash();
        }

        int index = hash(chave);
        if (tabela[index] == null) {
            tabela[index] = new Lista<>();
        }

        for (Entrada<K, V> entrada : tabela[index].paraArray()) {
            if (entrada.getChave().equals(chave)) {
                entrada.setValor(valor);
                return;
            }
        }

        tabela[index].adicionar(new Entrada<>(chave, valor));
        tamanho++;
    }

    public V pesquisar(K chave) {
        int index = hash(chave);
        if (tabela[index] == null) return null;

        for (Entrada<K, V> entrada : tabela[index].paraArray()) {
            if (entrada.getChave().equals(chave)) {
                return entrada.getValor();
            }
        }
        return null;
    }

    public void remover(K chave) {
        int index = hash(chave);
        if (tabela[index] == null) return;

        Entrada<K, V> paraRemover = null;
        for (Entrada<K, V> entrada : tabela[index].paraArray()) {
            if (entrada.getChave().equals(chave)) {
                paraRemover = entrada;
                break;
            }
        }

        if (paraRemover != null) {
            paraRemover.setRemovido(true);
            tamanho--;
        }
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    public int obterTamanho() {
        return tamanho;
    }

    public boolean contemValor(V valor) {
        for (Lista<Entrada<K, V>> lista : tabela) {
            if (lista != null) {
                for (Entrada<K, V> entrada : lista.paraArray()) {
                    if (entrada.getValor().equals(valor)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Lista<Entrada<K, V>> obterTodasEntradas() {
        Lista<Entrada<K, V>> todasEntradas = new Lista<>();
        for (Lista<Entrada<K, V>> lista : tabela) {
            if (lista != null) {
                for (Entrada<K, V> entrada : lista.paraArray()) {
                    if (!entrada.isRemovido()) {
                        todasEntradas.adicionar(entrada);
                    }
                }
            }
        }
        return todasEntradas;
    }

    public Lista<K> obterTodasChaves() {
        Lista<K> todasChaves = new Lista<>();
        for (Lista<Entrada<K, V>> lista : tabela) {
            if (lista != null) {
                for (Entrada<K, V> entrada : lista.paraArray()) {
                    if (!entrada.isRemovido()) {
                        todasChaves.adicionar(entrada.getChave());
                    }
                }
            }
        }
        return todasChaves;
    }

    public Lista<V> obterTodosValores() {
        Lista<V> todosValores = new Lista<>();
        for (Lista<Entrada<K, V>> lista : tabela) {
            if (lista != null) {
                for (Entrada<K, V> entrada : lista.paraArray()) {
                    if (!entrada.isRemovido()) {
                        todosValores.adicionar(entrada.getValor());
                    }
                }
            }
        }
        return todosValores;
    }

    public void substituir(K chave, V novoValor) {
        int index = hash(chave);
        if (tabela[index] == null) return;

        for (Entrada<K, V> entrada : tabela[index].paraArray()) {
            if (entrada.getChave().equals(chave)) {
                entrada.setValor(novoValor);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Lista<Entrada<K, V>>[] tabelaAntiga = tabela;
        capacidade *= 2;
        tabela = new Lista[capacidade];
        tamanho = 0;

        for (Lista<Entrada<K, V>> lista : tabelaAntiga) {
            if (lista != null) {
                for (Entrada<K, V> entrada : lista.paraArray()) {
                    if (!entrada.isRemovido()) {
                        inserir(entrada.getChave(), entrada.getValor());
                    }
                }
            }
        }
    }
}
