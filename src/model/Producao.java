package model;

import java.time.LocalDate;

public class Producao {
    private Integer id;
    private Produto produto;
    private Funcionario funcionario;
    private LocalDate data;
    private Integer quantidade;

    public Producao() {}

    public Producao(Integer id, Produto produto, Funcionario funcionario, LocalDate data, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.funcionario = funcionario;
        this.data = data;
        this.quantidade = quantidade;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + ", " +
                "\"produto\": " + (produto != null ? produto.toString() : "null") + ", " +
                "\"funcionario\": " + (funcionario != null ? funcionario.toString() : "null") + ", " +
                "\"data\": \"" + (data != null ? data.toString() : "") + "\", " +
                "\"quantidade\": " + quantidade +
                "}";
    }
}
