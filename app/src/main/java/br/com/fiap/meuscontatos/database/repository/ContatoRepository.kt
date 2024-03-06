package br.com.fiap.meuscontatos.database.repository

import android.content.Context
import br.com.fiap.meuscontatos.database.dao.ContatoDb
import br.com.fiap.meuscontatos.model.Contato

/*
A classe ContatoRepository é responsável por intermediar a comunicação entre
a camada de dados e a camada de apresentação.
Responsável por acessar os métodos de persistência de dados (CRUD).
 */
class ContatoRepository(context: Context) {

    private var db = ContatoDb.getDatabase(context).contatoDao()
    // -> Leia-se: "db é uma instância do banco de dados que sabe persistir contatos"

    // Métodos de acesso ao banco de dados
    fun salvar(contato: Contato) : Long {
        return db.salvar(contato)
    }
    fun atualizar(contato: Contato) : Int {
        return db.atualizar(contato)
    }
    fun excluir(contato: Contato) : Int {
        return db.excluir(contato)
    }
    fun buscarContatoPeloId(id: Int) : Contato {
        return db.buscarContatoPeloId(id)
    }
    fun listarContatos() : List<Contato> {
        return db.listarContatos()
    }
}
