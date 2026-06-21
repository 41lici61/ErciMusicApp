package com.example.ercimusic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ercimusic.models.Usuario;
import com.example.ercimusic.LocalStorageManager;

public class MainActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button bInicio;
    private TextView tvRegister;
    private LocalStorageManager storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = new LocalStorageManager(this);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        bInicio = findViewById(R.id.bInicio);
        tvRegister = findViewById(R.id.tvRegister);

        // Verificar si hay usuario guardado
        Usuario usuarioActual = storage.obtenerUsuarioActual();
        if (usuarioActual != null) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("usuario_id", usuarioActual.getId());
            startActivity(intent);
            finish();
            return;
        }

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        bInicio.setOnClickListener(v -> loginUsuario());
    }

    private void loginUsuario() {
        String nickname = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if (nickname.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario usuario = storage.login(nickname, pass);

        if (usuario != null) {
            Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("usuario_id", usuario.getId());
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}