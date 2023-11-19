package ifpr.pgua.eic.colecaomusicas.models;

import java.time.LocalDate;

public class Pet {
    private int codigo;
    private Cliente cliente;
    private String nome;
    private Raca raca;
    private String sexo;
    private String porte;
    private String especie;
    private LocalDate dataDeNascimento; 
    private String tratamentosEspeciais;
    private String condicoesFisicas;

    public Pet(Cliente cliente,Raca raca,String nome, String sexo, String porte, String especie, LocalDate dataNascimento, String tratamentosEspeciais, String condicoesFisicas) {
        this.cliente = cliente;
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.porte = porte;
        this.especie = especie;
        this.dataDeNascimento = dataNascimento;
        this.tratamentosEspeciais = tratamentosEspeciais;
        this.condicoesFisicas = condicoesFisicas;
    }

    public Pet(int codigo, Cliente cliente, Raca raca,String nome, String sexo, String porte, String especie, LocalDate dataDeNascimento, String tratamentosEspeciais, String condicoesFisicas) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.porte = porte;
        this.especie = especie;
        this.dataDeNascimento = dataDeNascimento;
        this.tratamentosEspeciais = tratamentosEspeciais;
        this.condicoesFisicas = condicoesFisicas;
    }
    
     public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "Pet: " + nome + "" + cliente + " ";
    }
}
