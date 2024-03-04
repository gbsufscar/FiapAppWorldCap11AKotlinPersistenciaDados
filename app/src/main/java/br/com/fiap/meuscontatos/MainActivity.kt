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
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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


    // Funções da aplicação
    @Composable
    fun ContatoForm(
        nome: String,
        telefone: String,
        amigo: Boolean,
        onNomeChange: (String) -> Unit,
        onTelefoneChange: (String) -> Unit,
        onAmigoChange: (Boolean) -> Unit
    ) {
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
                value = telefone,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    onTelefoneChange(it)
                },
                label = {
                    Text(text = "Telefone de contato")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
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
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Cadastrar",
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }
}

@Composable
fun ContatoList(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Barra de rolagem vertical
    ) {
        for(i in 0..10){
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
            Column(modifier = Modifier
                .padding(8.dp)
                .weight(2f)) {
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