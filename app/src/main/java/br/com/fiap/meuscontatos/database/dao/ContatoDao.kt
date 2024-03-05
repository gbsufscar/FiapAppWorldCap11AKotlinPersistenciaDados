package br.com.fiap.meuscontatos.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.meuscontatos.model.Contato

@Dao // Define que a interface é um DAO
interface ContatoDao {

    @Insert
    fun salvar(contato: Contato): Long // O retorno é o id do contato salvo no banco de dados

    @Update
    fun atualizar(contato: Contato): Int // O retorno é a quantidade de registros atualizados

    @Delete
    fun excluir(contato: Contato): Int

    @Query("SELECT * FROM tbl_contato WHERE id = :id") // Define a consulta SQL
    fun buscarContatoPeloId(id: Int): Contato

    @Query("SELECT * FROM tbl_contato ORDER BY nome ASC")
    fun listarContatos(): List<Contato>

}