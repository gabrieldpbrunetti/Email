package brunetti.depaula.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btEnviar = findViewById(R.id.etButton);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Começando a selecionar os elementos
                EditText etEmail = findViewById(R.id.etEmail);
                EditText etAssunto = findViewById(R.id.etAssunto);
                EditText etTexto = findViewById(R.id.etTexto);
                //Fim da seleção

                //Capturando textos
                String email = String.valueOf(etEmail.getText());
                String assunto = String.valueOf(etAssunto.getText());
                String texto= String.valueOf(etTexto.getText());
                //fim capturando textos

                //criando intent
                Intent i = new Intent(Intent.ACTION_SENDTO);
                //procurando apps que resolvam o uri mailto
                i.setData(Uri.parse("mailto:"));

                //emails de destinatarios
                String[] emails = new String[] {email};

                //adicionando o conteudo a intent
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);
                //tentando iniciar a intent
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                //capturando exceção
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}