package ifpr.pgua.eic.colecaomusicas.repositories;

import java.time.LocalDate; 
import com.github.hugoperlin.results.Resultado;
import ifpr.pgua.eic.colecaomusicas.daos.PetDAO;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Raca;

public class RepositorioPet {
    private PetDAO dao;

    public RepositorioPet(PetDAO dao) {
        this.dao = dao;
    }

    public Resultado cadastrarPet(String nome, Raca raca, String sexo, String porte, String especie,
                                  LocalDate dataDeNascimento, String tratamentosEspeciais, String condicoesFisicas) {
        if (nome == null || nome.isBlank()) {
            return Resultado.erro("Nome inválido!");
        }

        if (raca == null) {
            return Resultado.erro("Raça inválida!");
        }

        if (sexo == null || sexo.isBlank()) {
            return Resultado.erro("Sexo inválido!");
        }

        if (porte == null || porte.isBlank()) {
            return Resultado.erro("Porte inválido!");
        }

        if (especie == null || especie.isBlank()) {
            return Resultado.erro("Espécie inválida!");
        }

        if (dataDeNascimento == null) {
            return Resultado.erro("Data de Nascimento inválida!");
        }

        if (tratamentosEspeciais == null || tratamentosEspeciais.isBlank()) {
            return Resultado.erro("Tratamentos Especiais e Observações inválidas!");
        }

        if (condicoesFisicas == null || condicoesFisicas.isBlank()) {
            return Resultado.erro("Condições Físicas inválidas!");
        }

        Pet pet = new Pet(nome, raca, sexo, porte, especie, dataDeNascimento, tratamentosEspeciais, condicoesFisicas);
        return dao.criar(pet);
    }

    public Resultado listarRaca(){
        return dao.listar();
    }
}
