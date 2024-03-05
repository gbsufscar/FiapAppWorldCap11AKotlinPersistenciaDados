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
    Bloco companion object para identificar se tratar de uma classe estática Singleton.
    Retorna uma única instância do banco de dados (classe ContatoDb).
     */
    companion object {

        private lateinit var instance: ContatoDb // Atributo privado e estático do tipo ContatoDb

        /*

         */
        fun getDatabase(context: Context): ContatoDb {
            if (!::instance.isInitialized) { // Verifica se a instância do banco de dados já foi inicializada
                instance = Room
                    .databaseBuilder(
                        context,
                        ContatoDb::class.java,
                        "contato_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance // Retorna a instância do banco de dados
        }
    }
}