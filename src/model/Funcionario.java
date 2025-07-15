package model;

public class Funcionario {

    private Integer idFuncionario;
    private String nome;
    private String sobrenome;
    private  Setor setor;

    public Funcionario(Integer idFuncionario, String nome, String sobrenome, Setor setor) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.setor = setor;
    }

    public Funcionario() {
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "{"
                + "\"idFuncionario\": " + idFuncionario + ", "
                + "\"nome\": \"" + nome + "\", "
                + "\"sobrenome\": \"" + sobrenome + "\", "
                + "\"setor\": \"" + setor + "\""
                + "}";
    }

}
