package model;

import dao.SetorDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Arq {
    public static void main(String[] args) {

        // Caminho para um arquivo, não para um diretório!
        String caminho = "C:\\Users\\lsoar\\Desktop\\Projetos do Java\\industria\\src\\model\\arquivo.csv";
        File arquivo = new File(caminho);
        SetorDAO setorDAO = new SetorDAO();
        ArrayList<Setor> setores = setorDAO.listar();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            // Se quiser pular o cabeçalho:
            br.readLine();
            while ((linha = br.readLine()) != null) {
                // Aqui você pode processar cada linha do CSV
                System.out.println(linha);
                // Exemplo: String[] campos = linha.split(";");
                // Relacionar com setores, se necessário
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write("ID,NOME,RESPONSAVEL");
            bw.newLine();
            for (Setor setor : setores){
                Integer id =  setor.getIdSetor();
                String nome = setor.getNome();
                String responsavel = setor.getResponsavel();

                bw.write(String.format("%d,%s,%s",id,nome,responsavel));
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo.");
        }

    }
}
