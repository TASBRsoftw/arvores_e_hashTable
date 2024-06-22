package HASH;

public class Main2a {
    public static void main(String[] args) {
        // Conversão de 'tiagosuncdep' para valores numéricos
        String chaves = "tiagosuncdep";
        TabelaHash<Integer, Character> tabela = new TabelaHash<>();

        for (char c : chaves.toCharArray()) {
            int chave = c - 'a' + 1;
            tabela.inserir(chave, c);
        }

        Lista<Entrada<Integer, Character>> entradas = tabela.obterTodasEntradas();
        for (Entrada<Integer, Character> entrada : entradas.paraArray()) {
            System.out.println("Chave: " + entrada.getChave() + ", Valor: " + entrada.getValor());
        }
    }
}
