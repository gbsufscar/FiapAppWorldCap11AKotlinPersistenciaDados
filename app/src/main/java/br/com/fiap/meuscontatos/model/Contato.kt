package br.com.fiap.meuscontatos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_contato") // Define o nome da tabela no banco de dados
data class Contato(
    @PrimaryKey(autoGenerate = true) var id: Long = 0, // Define a chave primária e a auto geração de valores
    var nome: String = "",
    var telefone: String = "",
   @ColumnInfo(name = "is_amigo") var amigo: Boolean = false // Define o nome da coluna no banco de dados
)
