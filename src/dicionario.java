
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class dicionario {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileReader le = new FileReader("entrada2.txt");

        // bufferiza a entrada do arquivo para ser possivel ler uma linha inteira no arquivo
        BufferedReader leBufferizado = new BufferedReader(le);

        String texto = "";
        String parada = ".";
        int numeroDePalavras = 0;
        String[] vetorComTodasAsPalavras = new String[1000];

        while (!texto.equals(parada)) {
            // aqui lido uma linha do arquivo
            texto = leBufferizado.readLine();
            // transforma para minuscula
            texto = texto.toLowerCase();
            // gera um vetor com as palavas da linha
            String palavras[] = texto.split(" ");

            // insere as palavras no dicionario do samuel ( vetorComTodasAsPalavras)
            // para cada uma das palavras em uma linha
            for (int i = 0; i < palavras.length; i++) {
                // faz a busca binaria da palavra no vetor com todas as palavras
                if (!palavras[i].equals("") && !palavras[i].equals(".") && buscabinaria(vetorComTodasAsPalavras, palavras[i]) != -1) {
                    // se nao achar a palavra no vetorComTodasAsPalavras, entao
                    // insere a palavra
                    InserePalavra(vetorComTodasAsPalavras, palavras[i], numeroDePalavras);
                    // aumenta o numero de palavras no vetor
                    numeroDePalavras++;
                }
            }
        }
        // impreme as palavras inseridas no vetor, somente as inseridas
        for (int i = 0; i < numeroDePalavras; i++) {
            System.out.println(vetorComTodasAsPalavras[i]);
        }
    }

    public static void InserePalavra(String[] vetorAtual, String novaPalavra, int qtdvetor) {
        // estou inserindo no final do vetor
        vetorAtual[qtdvetor] = novaPalavra;
        int i = qtdvetor; // comeca no fina do vetor (qtdvetor)
        while (i > 0 && vetorAtual[i].compareTo(vetorAtual[qtdvetor]) < 0) { //palavras[i].compareTo(palavras[j]) < 0
            vetorAtual[i] = vetorAtual[qtdvetor];
            i--;
        }
        vetorAtual[i] = novaPalavra;

        /* int i=j; // comeca no fina do vetor (qtdvetor)
         int x = v[i]; // int x = v[i]
         while(i > 0 && x < v[i-1] ){ //palavras[i].compareTo(palavras[j]) < 0
            v[i] = v[i-1];
            i--;
         }
         v[i]=x;
         */
    }

    public static int buscabinaria(String palavras[], String x) {
        int i, m, f;
        i = 0;
        f = palavras.length - 1;
        while (i <= f) {
            m = (i + f) / 2;
            if (palavras[m].equals(x)) { //meio
                return m;
            }
            if (x.compareTo(palavras[m]) < 0){ // esquerda 
                f = m - 1;
            }else{ // x > palavras[m] - direita
                i = m + 1;
            }
        }
        return -1;
    }
}
