package ifpr.pgua.eic.colecaomusicas.models;

import java.time.LocalDate;

public class Pet {
    private int codigo;
    private int clienteCodigo;
    private int racaCodigo;
    private String nome;
    private Raca raca;
    private String sexo;
    private String porte;
    private String especie;
    private LocalDate dataDeNascimento; 
    private String tratamentosEspeciais;
    private String condicoesFisicas;

    public Pet(String nome, Raca raca, String sexo, String porte, String especie, LocalDate dataNascimento, String tratamentosEspeciais, String condicoesFisicas) {
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.porte = porte;
        this.especie = especie;
        this.dataDeNascimento = dataNascimento;
        this.tratamentosEspeciais = tratamentosEspeciais;
        this.condicoesFisicas = condicoesFisicas;
    }

    public Pet(int codigo, int clienteCodigo, String nome, Raca raca, String sexo, String porte, String especie, LocalDate dataDeNascimento, String tratamentosEspeciais, String condicoesFisicas) {
        this.codigo = codigo;
        this.clienteCodigo = clienteCodigo;
        this.racaCodigo = racaCodigo;
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.porte = porte;
        this.especie = especie;
        this.dataDeNascimento = dataDeNascimento;
        this.tratamentosEspeciais = tratamentosEspeciais;
        this.condicoesFisicas = condicoesFisicas;
    }
    
     public int getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(int clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public int getRacaCodigo() {
        return racaCodigo;
    }

    public void setRacaCodigo(int racaCodigo) {
        this.racaCodigo = racaCodigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getTratamentosEspeciais() {
        return tratamentosEspeciais;
    }

    public void setTratamentosEspeciais(String tratamentosEspeciais) {
        this.tratamentosEspeciais = tratamentosEspeciais;
    }

    public String getCondicoesFisicas() {
        return condicoesFisicas;
    }

    public void setCondicoesFisicas(String condicoesFisicas) {
        this.condicoesFisicas = condicoesFisicas;
    }

    @Override
    public String toString() {
        return "Pet: " + nome + "";
    }
}
