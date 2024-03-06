package br.com.fiap.meuscontatos.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.meuscontatos.model.Contato

/*
A classe abstrata ContatoDb herda a classe abstrata RoomDatabase (por isso aquela deve ser abstrata,
pois classe abstrata só herda de classe abstrata.
 */
@Database(entities = [Contato::class], version = 1)
abstract class ContatoDb : RoomDatabase() {

    /*
    Função abstrata contato Dao que é do tipo ContatoDao.
    Viabiliza o acesso aos métodos CRUD.
     */
    abstract fun contatoDao(): ContatoDao

    /*
    Bloco companion object para indicar que tudo o que está dentro dele é uma classe estática;
    trata-se de uma classe estática Singleton (garante sempre o retorno de uma única instância).
    Retorna uma única instância do banco de dados (classe ContatoDb).
     */
    companion object {

        private lateinit var instance: ContatoDb // Atributo privado e estático do tipo ContatoDb

        //Função getDatabase que retorna uma instância do banco de dados.
        fun getDatabase(context: Context): ContatoDb { // Recebe um contexto da aplicação como parâmetro
            if (!::instance.isInitialized) { // Verifica se a instância do banco de dados já foi inicializada
                instance = Room
                    .databaseBuilder( // Método construtor do banco de dados
                        context,
                        ContatoDb::class.java, // Indicar a classe que representa o banco de dados, já instanciada
                        "contato_db" // Nome do banco de dados, criado na primeira instanciação do banco de dados
                    )
                    .allowMainThreadQueries() // Permite que as consultas ao banco de dados sejam feitas na thread principal
                    .fallbackToDestructiveMigration() // Permite que o banco de dados seja recriado em caso de mudança de versão
                    .build() // Constrói o banco de dados (cria a instância)
            }
            return instance // Retorna a instância do banco de dados
        }
    }
}