package com.eden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.eden.adapter.ProdutosAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BottomNavigationView footer;
    List<Produto> produtos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseUtil db = new DatabaseUtil();

        ImageView btnSidebar = findViewById(R.id.btnSidebar);
        ImageView btnCarrinho = findViewById(R.id.btnCarrinho);

        footer = findViewById(R.id.footer_navigation);
        recyclerView = findViewById(R.id.recyclerView);

        // Settando o adapter da RecyclerView
        ProdutosAdapter adapter = new ProdutosAdapter(produtos);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.listarProdutos(produtos, adapter);

        btnSidebar.setOnClickListener(v -> {
            Intent intent = new Intent(this, Sidebar.class);
            startActivity(intent);
        });
        btnCarrinho.setOnClickListener(v -> {
            Intent intent = new Intent(this, Carrinho.class);
            startActivity(intent);
        });

        footer.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_add){
                    recyclerView.setVisibility(View.GONE);
                    Intent intent = new Intent(MainActivity.this, CadastrarProduto.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.menu_home){
                    recyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        footer.setSelectedItemId(R.id.menu_home);

    }
}