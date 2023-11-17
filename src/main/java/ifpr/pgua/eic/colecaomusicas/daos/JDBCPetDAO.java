package ifpr.pgua.eic.colecaomusicas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Cliente;
import ifpr.pgua.eic.colecaomusicas.models.Pet;
import ifpr.pgua.eic.colecaomusicas.models.Raca;

public class JDBCPetDAO implements PetDAO {

    private FabricaConexoes fabrica;

    public JDBCPetDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Pet pet) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(
                    "INSERT INTO tb_animal(cliente_codigo, raca_codigo, nome,sexo, porte, especie, data_de_nascimento, tratamento_especiais, condicoes_fisicas) VALUES (?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, pet.getClienteCodigo());
            pstm.setInt(2, pet.getRacaCodigo());
            pstm.setString(3, pet.getNome());
            pstm.setInt(4, pet.getRaca().getCodigo());
            ;
            pstm.setString(5, pet.getSexo());
            pstm.setString(6, pet.getPorte());
            pstm.setString(7, pet.getEspecie());
            pstm.setObject(8, pet.getDataDeNascimento());
            pstm.setString(9, pet.getTratamentosEspeciais());
            pstm.setString(10, pet.getCondicoesFisicas());

            int ret = pstm.executeUpdate();

            if (ret == 1) {
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int codigo = rs.getInt(1);

                pet.setCodigo(codigo);

                return Resultado.sucesso("Pet cadastrado com sucesso!!!", pet);
            }
            return Resultado.erro("Ops.. deu um erro!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {

        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT tb_animal.*, tb_raca.nome AS nome_raca " +
            "FROM tb_animal " +
            "INNER JOIN tb_raca ON tb_animal.raca_codigo = tb_raca.codigo");


            ResultSet rs = pstm.executeQuery();
            ArrayList<Pet> lista = new ArrayList<>();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                int clienteCodigo = rs.getInt("cliente_codigo");

                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                String porte = rs.getString("porte");
                String especie = rs.getString("especie");
                LocalDate dataDeNascimento = rs.getObject("data_de_nascimento", LocalDate.class);
                String tratamentosEspeciais = rs.getString("tratamento_especiais");
                String condicoesFisicas = rs.getString("condicoes_fisicas");

                int raca_codigo = rs.getInt("raca_codigo");
                String nomeRaca = rs.getString("nome_raca");
                Raca raca = new Raca(raca_codigo, nomeRaca, null);

                Pet pet = new Pet(codigo, clienteCodigo, nome, raca, sexo, porte, especie,
                        dataDeNascimento,
                        tratamentosEspeciais, condicoesFisicas);
                lista.add(pet);
            }

            return Resultado.sucesso("Lista de Pets", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

}
