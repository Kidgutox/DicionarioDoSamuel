/*
Entrega do Trabalho 2- Algoritmos e Programação II
Nós,
Gustavo Costa Santos Almeida
Luís Felipe da Costa Angelo
declaramos que
todas as respostas são fruto de nosso próprio trabalho,
não copiamos respostas de colegas externos à equipe,
não disponibilizamos nossas respostas para colegas externos ao grupo e
não realizamos quaisquer outras atividades desonestas para nos beneficiar ou
prejudicar outros.

 /* Dados importantes

Data de entrega: 22/06/2020
Curso: Bacharelado em Jogos Digitais
Semestre: 3°
Matéria: Algoritmo e programação II

 */

//Importações necessárias
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class dicionario {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileReader le = new FileReader("entrada.txt");

        // bufferiza a entrada do arquivo para ser possivel ler uma linha inteira no
        // arquivo
        BufferedReader leBufferizado = new BufferedReader(le);

        // lê uma linha do arquivo de entrada
        String texto = "";
        String parada = ".";
        String[] vetorComTodasAsPalavras = {};

        /*
	O trecho abaixo é responsável por realizar a leitura de todas as linhas do
	arquivo e por diminuir todas as letras do arquivo que estiverem maiusculas
         */
        while (!texto.equals(parada)) {
            // aqui lido uma linha do arquivo
            texto = leBufferizado.readLine();
            // transforma para minuscula
            texto = texto.toLowerCase();
            // gera um vetor com as palavas da linha
            String palavras[] = texto.split(" ");
            for (int i = 0; i < palavras.length; i++) {
                if (!palavras[i].equals("") && !palavras[i].equals(".") && (buscaBinaria(vetorComTodasAsPalavras, palavras[i], 0, vetorComTodasAsPalavras.length) == false)) {
                    vetorComTodasAsPalavras = totalDePalavras(vetorComTodasAsPalavras, palavras[i], vetorComTodasAsPalavras.length + 1);
                    vetorComTodasAsPalavras = inserePalavra(vetorComTodasAsPalavras);
                }
            }
        }
        imprimeVetor(vetorComTodasAsPalavras);
    }

    /*
    A função abaixo é responsável por criar um vetor com N posições
    (N = quantidade de palavras do txt).
     */
    public static String[] totalDePalavras(String[] vetorAtual, String novaPalavra, int tamanhoDoVetorASerCriado) {
        String[] novoVetor = new String[tamanhoDoVetorASerCriado];

        for (int i = 0; i < novoVetor.length; i++) {
            if (i < vetorAtual.length) {
                novoVetor[i] = vetorAtual[i];
            } else {
                novoVetor[i] = novaPalavra;
            }
        }
        return novoVetor;
    }

    /*
    A função abaixo realiza a inserção e a troca de posições
    das palavras do dicionário
     */
    public static String[] inserePalavra(String[] vetorAtual) {
        String tempPalavra;
        int j = vetorAtual.length - 1;

        // Realiza a comparação e troca de posições
        for (int i = j; i > 0; i--) {
            if (vetorAtual[i].compareTo(vetorAtual[i - 1]) < 0) {
                tempPalavra = vetorAtual[i - 1];
                vetorAtual[i - 1] = vetorAtual[i];
                vetorAtual[i] = tempPalavra;
            }
        }

        return vetorAtual;
    }

    /*
    Função responsável por verificar se a palavra já existe no dicionário
     */
    public static boolean buscaBinaria(String vetorComTodasAsPalavras[], String palavraAtual, int inicio, int fim) {
        int meio;
        // realiza o cálculo absoluto do meio do vetor
        meio = Math.abs((inicio + fim) / 2);
        if (vetorComTodasAsPalavras.length != meio) {
            if (palavraAtual.compareTo(vetorComTodasAsPalavras[meio]) == 0) { // meio do vetor
                return true; // Retorna true pois, encontrou a palavra no dicionário
            } else if (fim > inicio) {
                if (palavraAtual.compareTo(vetorComTodasAsPalavras[meio]) < 0) { // direita do vetor
                    return buscaBinaria(vetorComTodasAsPalavras, palavraAtual, inicio, meio - 1);

                } else if (palavraAtual.compareTo(vetorComTodasAsPalavras[meio]) > 0) { // esquerda do vetor
                    return buscaBinaria(vetorComTodasAsPalavras, palavraAtual, meio + 1, fim);
                }
            }
        }
        return false; // Retorna false pois, a palavra não se enquadra nas condições acima
    }

    //Imprime o vetor
    public static void imprimeVetor(String[] vetorAtual) {
        for (int i = 0; i < vetorAtual.length; i++) {
            System.out.println(vetorAtual[i]);
        }
        System.out.println("A quantidade total de palavras no dicionário são: " + vetorAtual.length);
    }

}
