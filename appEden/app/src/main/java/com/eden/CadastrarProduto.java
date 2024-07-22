package com.eden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CadastrarProduto extends AppCompatActivity {

    EditText nome, preco, descricao, tipoEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produto);

        DatabaseUtil db = new DatabaseUtil();
        Button btnAvancar = findViewById(R.id.btnCadastroAvancar);
        nome = findViewById(R.id.editText_nome_produto);
        preco = findViewById(R.id.editText_valor_produto);
        descricao = findViewById(R.id.editText_descricao_produto);
        tipoEntrega = findViewById(R.id.editText_meio_entrega);

        // Salvando os dados no firebase
        btnAvancar.setOnClickListener(v -> {
            if (nome.getText().toString().equals("") || preco.getText().toString().equals("") || descricao.getText().toString().equals("") || tipoEntrega.getText().toString().equals("")) {
                Toast.makeText(this, "Os valores não podem estar vazios", Toast.LENGTH_SHORT).show();
            } else if (Double.parseDouble(preco.getText().toString()) <= 0) {
                Toast.makeText(this, "O valor deve ser maior que 0", Toast.LENGTH_SHORT).show();
            } else {
                // Salvando produto
                db.salvarProduto(
                        new Produto(0, nome.getText().toString(),
                                descricao.getText().toString(), Double.parseDouble(preco.getText().toString()),
                                false, false), this);
            }
        });


    }
}