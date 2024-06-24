package AVL;

public class Main {
    public static void main(String[] args) {
        AVL<Integer> arvore = new AVL<>();

        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);

        // Caminhamento Pré-Ordem
        arvore.caminhamentoPreOrdem(); // Output: 50 30 20 40 70 60 80 

        // Caminhamento Pós-Ordem
        arvore.caminhamentoPosOrdem(); // Output: 20 40 30 60 80 70 50

        // Caminhamento Decrescente
        arvore.caminhamentoDecrescente(); // Output: 80 70 60 50 40 30 20

        // Obter o menor elemento
        System.out.println(arvore.obterMenor()); // Output: 20

        // Clonar a árvore
        AVL<Integer> cloneArvore = arvore.clone();
        cloneArvore.caminhamentoPreOrdem(); // Output: 50 30 20 40 70 60 80

        // Obter subconjunto de maiores ou iguais a 40
        AVL<Integer> subconjunto = arvore.obterSubconjuntoMaiores(40);
        subconjunto.caminhamentoPreOrdem(); // Output: 50 40 70 60 80

        // Verificar se o item é a raiz
        System.out.println(arvore.ehRaiz(50)); // Output: true

        // Obter antecessor
        System.out.println(arvore.obterAntecessor(60)); // Output: 50
    }
}
