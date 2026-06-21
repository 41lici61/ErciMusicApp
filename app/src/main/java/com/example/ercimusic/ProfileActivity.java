package com.example.ercimusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ercimusic.models.Usuario;
import com.example.ercimusic.LocalStorageManager;

public class ProfileActivity extends AppCompatActivity {

    private Usuario usuario;
    private LocalStorageManager storage;
    private ImageView ivAvatar;
    private TextView tvNickname, tvName, tvScore, tvLife, tvLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // ELIMINADO StrictMode

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Perfil");
        }

        ivAvatar = findViewById(R.id.ivAvatar);
        tvNickname = findViewById(R.id.tvNickname);
        tvName = findViewById(R.id.tvName);
        tvScore = findViewById(R.id.tvScore);
        tvLife = findViewById(R.id.tvLife);
        tvLevel = findViewById(R.id.tvLevel);

        storage = new LocalStorageManager(this);

        int userId = getIntent().getIntExtra("usuario_id", -1);
        usuario = storage.obtenerUsuarioPorId(userId);

        if (usuario != null) {
            mostrarDatos();
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void mostrarDatos() {
        tvNickname.setText("Nickname: " + usuario.getNickname());
        tvName.setText("Nombre: " + usuario.getName() + " " + usuario.getLastname());
        tvScore.setText("Score: " + usuario.getScore());
        tvLife.setText("Vidas: " + usuario.getLife());
        tvLevel.setText("Nivel actual: " + usuario.getCurrent_lvl());
        ivAvatar.setImageResource(obtenerAvatar(usuario.getPfp()));
    }

    private int obtenerAvatar(String nombre) {
        switch (nombre) {
            case "avatar1": return R.drawable.avatar1;
            case "avatar2": return R.drawable.avatar2;
            case "avatar3": return R.drawable.avatar3;
            default: return R.drawable.avatar1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overflow, menu);

        if (usuario != null) {
            MenuItem itemLives = menu.findItem(R.id.op_lives);
            MenuItem itemScore = menu.findItem(R.id.op_score);
            MenuItem itemPhoto = menu.findItem(R.id.op_photo);

            itemLives.setTitle("❤️ " + usuario.getLife());
            itemScore.setTitle("🎯 " + usuario.getScore());
            itemPhoto.setIcon(obtenerAvatar(usuario.getPfp()));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.op_ranking) {
            Intent intent = new Intent(this, RankingActivity.class);
            intent.putExtra("usuario_id", usuario.getId());
            startActivity(intent);
            return true;
        } else if (id == R.id.op_salir) {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("usuario_id", usuario.getId());
            startActivity(intent);
            return true;
        } else if (id == R.id.op_cheat) {
            usuario.setCurrent_lvl(6);
            usuario.setLife(5);
            storage.actualizarUsuario(usuario);
            Toast.makeText(this, "MODO CHETO 😈", Toast.LENGTH_LONG).show();
            invalidateOptionsMenu();
            mostrarDatos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}