package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Funcionario;

public interface FuncionarioDAO {
    Resultado criar(Funcionario funcionario);
    Resultado listar();   
    Resultado editar(int codigo, Funcionario novo);

}
