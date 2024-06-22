package HASH;

public class Main1 {
    public static void main(String[] args) {
        TabelaHash<Integer, String> tabela = new TabelaHash<>();
        tabela.inserir(35, "Valor35");
        tabela.inserir(49, "Valor49");
        tabela.inserir(11, "Valor11");
        tabela.inserir(18, "Valor18");
        tabela.inserir(13, "Valor13");
        tabela.inserir(29, "Valor29");
        tabela.inserir(32, "Valor32");
        tabela.inserir(90, "Valor90");

        Lista<Entrada<Integer, String>> entradas = tabela.obterTodasEntradas();
        for (Entrada<Integer, String> entrada : entradas.paraArray()) {
            System.out.println("Chave: " + entrada.getChave() + ", Valor: " + entrada.getValor());
        }
    }
}
