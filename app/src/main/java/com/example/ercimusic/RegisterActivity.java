package com.example.ercimusic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ercimusic.models.Usuario;
import com.example.ercimusic.LocalStorageManager;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etLastname, etNickname, etPass, etPassConfirm;
    private ImageView avatar1, avatar2, avatar3;
    private Button bRegister;
    private String avatarSeleccionado = null;
    private LocalStorageManager storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // ELIMINADO StrictMode

        storage = new LocalStorageManager(this);

        etName = findViewById(R.id.etName);
        etLastname = findViewById(R.id.etLastname);
        etNickname = findViewById(R.id.etNickname);
        etPass = findViewById(R.id.etPass);
        etPassConfirm = findViewById(R.id.etPassConfirm);

        avatar1 = findViewById(R.id.avatar1);
        avatar2 = findViewById(R.id.avatar2);
        avatar3 = findViewById(R.id.avatar3);

        bRegister = findViewById(R.id.bRegister);

        avatar1.setOnClickListener(v -> {
            avatarSeleccionado = "avatar1";
            resaltarAvatar(avatar1);
        });
        avatar2.setOnClickListener(v -> {
            avatarSeleccionado = "avatar2";
            resaltarAvatar(avatar2);
        });
        avatar3.setOnClickListener(v -> {
            avatarSeleccionado = "avatar3";
            resaltarAvatar(avatar3);
        });

        bRegister.setOnClickListener(v -> registrarUsuario());
    }

    private void resaltarAvatar(ImageView selected) {
        avatar1.setAlpha(0.5f);
        avatar2.setAlpha(0.5f);
        avatar3.setAlpha(0.5f);
        selected.setAlpha(1.0f);
    }

    private void registrarUsuario() {
        String name = etName.getText().toString().trim();
        String lastname = etLastname.getText().toString().trim();
        String nickname = etNickname.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String passConfirm = etPassConfirm.getText().toString().trim();

        if (name.isEmpty() || lastname.isEmpty() || nickname.isEmpty()
                || pass.isEmpty() || passConfirm.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(passConfirm)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        if (avatarSeleccionado == null) {
            Toast.makeText(this, "Selecciona un avatar", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario usuario = new Usuario(
                avatarSeleccionado,
                nickname,
                name,
                lastname,
                pass,
                0,
                3,
                1
        );

        boolean insertado = storage.insertarUsuario(usuario);

        if (insertado) {
            Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "El nickname ya existe", Toast.LENGTH_SHORT).show();
        }
    }
}