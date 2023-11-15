package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Pet;

public interface PetDAO {
    Resultado criar(Pet pet);
    Resultado listar();
}
