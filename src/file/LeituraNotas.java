package file;

import java.io.*;

public class LeituraNotas {

    public static void main(String[] args) {
        String caminho = "C:\\Users\\lsoar\\Desktop\\Projetos do Java\\industria\\src\\file\\notas.txt";
        File arquivo = new File(caminho);

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            String linha;
            Integer qtdeLinhas = 0;
            Double soma = 0.0, media = 0.0;

            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.split(",");
                soma += Double.parseDouble(colunas[1]);
                qtdeLinhas++;

            }
            media = soma / qtdeLinhas;
            System.out.println("Media das Notas da Turma: " + media);

        } catch (FileNotFoundException e) {
            System.out.println("Erro arquivo nao encontrado.");
        } catch (IOException e) {
            System.out.println("Erro nao foi possivel ler o arquivo.");
        }


    }
}
