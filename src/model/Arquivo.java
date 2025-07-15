package model;

import dao.SetorDAO;

import java.io.*;
import java.util.ArrayList;

public class Arquivo {
    public static void main(String[] args) {
        SetorDAO setorDAO = new SetorDAO();
        ArrayList<Setor> setores = setorDAO.listar();

        String caminho = "C:\\Users\\lsoar\\Desktop\\Projetos do Java\\industria\\src\\model\\arquivo.csv";
        File file = new File(caminho);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0].trim());
                String nome = partes[1];
                String responsavel = partes[2];

                System.out.printf("%-5d %-30s %-50s %n", id, nome, responsavel);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
        }


    }
}
