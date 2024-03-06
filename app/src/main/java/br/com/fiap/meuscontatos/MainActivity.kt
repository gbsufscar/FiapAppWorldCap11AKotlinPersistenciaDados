package br.com.fiap.meuscontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.meuscontatos.database.repository.ContatoRepository
import br.com.fiap.meuscontatos.model.Contato
import br.com.fiap.meuscontatos.ui.theme.MeusContatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeusContatosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ContatosScreen()
                    }

                }
            }
        }
    }
}

@Composable
fun ContatosScreen() {
    // Variáveis de estado
    var nomeSate = remember {
        mutableStateOf("")
    }
    var telefoneState = remember {
        mutableStateOf("")
    }
    var amigoState = remember {
        mutableStateOf(false)
    }

    // Tela
    Column {
        ContatoForm(
            nome = nomeSate.value,
            telefone = telefoneState.value,
            amigo = amigoState.value,
            onNomeChange = {
                nomeSate.value = it
            },
            onTelefoneChange = {
                telefoneState.value = it
            },
            onAmigoChange = {
                amigoState.value = it
            }
        )
        ContatoList()
    }
}


// Funções da aplicação
@Composable
fun ContatoForm(
    // Parâmetros da função
    nome: String,
    telefone: String,
    amigo: Boolean,

    // Funções de callback
    /*
    Função de callback para quando o nome do contato for alterado:
    Em Kotlin, uma função de callback é aquela que é passada como argumento para outra função.
    Durante a execução da função receptora, a função de callback pode ser invocada no momento apropriado.

    Nesse caso, trata-se de um parâmetro do tipo função que recebe uma String como argumento
    e não retorna nada (representado por Unit). Esse parâmetro será usado para lidar com mudanças no nome do contato.
     */
    onNomeChange: (String) -> Unit,
    onTelefoneChange: (String) -> Unit,
    onAmigoChange: (Boolean) -> Unit
) {
    // Obter a instância do repositorio
    val context = LocalContext.current // Obter o contexto da aplicação (necessário para instanciar o repositório)
    val contatoRepository = ContatoRepository(context) // Instanciar o repositório

    // Formulário
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Cadastro de contatos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(
                0xFFE91E63
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nome,
            modifier = Modifier.fillMaxWidth(),
            /*
            onValueChange:
            É uma função de callback que é chamada quando o valor do campo de texto é alterado.
            Ela recebe o novo valor como argumento: a função onTelefoneChange é chamada
            com o novo valor.
             */
            onValueChange = {
                onNomeChange(it)
            },
            label = {
                Text(text = "Nome contato")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = telefone,
            onValueChange = { onTelefoneChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Telefone do contato")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = amigo,
                onCheckedChange = {
                    onAmigoChange(it)
                }
            )
            Text(text = "Amigo")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                    // Criar um objeto do tipo Contato com os dados preenchidos no formulário
                      val contato = Contato(
                          nome = nome,
                          telefone = telefone,
                          amigo = amigo
                      )
                       },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "CADASTRAR",
                modifier = Modifier.padding(8.dp)
            )
        }

    }
}


@Composable
fun ContatoList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Barra de rolagem vertical
    ) {
        for (i in 0..10) {
            ContatoCard()
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ContatoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(R.color.cinza_claro)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            ) {
                Text(
                    text = "Nome do Contato",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "8888-9999",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Amigo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            // Botão de exclusão em forma de ícone (lixeirinha
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}