package com.example.ercimusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ercimusic.models.Level;
import com.example.ercimusic.models.Usuario;
import com.example.ercimusic.LocalStorageManager;
import com.example.ercimusic.DataInitializer;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private Usuario usuario;
    private LocalStorageManager storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ELIMINADO StrictMode

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Menu");
        }

        storage = new LocalStorageManager(this);

        int userId = getIntent().getIntExtra("usuario_id", -1);

        if (userId == -1) {
            usuario = storage.obtenerUsuarioActual();
            if (usuario == null) {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        } else {
            usuario = storage.obtenerUsuarioPorId(userId);
            if (usuario == null) {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }

        configurarNiveles();
    }

    private void configurarNiveles() {
        Button[] botones = {
                findViewById(R.id.btnLevel1),
                findViewById(R.id.btnLevel2),
                findViewById(R.id.btnLevel3),
                findViewById(R.id.btnLevel4),
                findViewById(R.id.btnLevel5),
                findViewById(R.id.btnLevel6)
        };

        List<Level> niveles = DataInitializer.getNivelesIniciales();

        for (int i = 0; i < botones.length && i < niveles.size(); i++) {
            Level nivel = niveles.get(i);
            final int levelNumber = nivel.getId_level();

            if (usuario.getCurrent_lvl() >= nivel.getId_level()) {
                botones[i].setEnabled(true);
                botones[i].setText("LEVEL " + levelNumber);
                botones[i].setOnClickListener(v -> {
                    Intent intent = new Intent(MenuActivity.this, game.class);
                    intent.putExtra("nivel", levelNumber);
                    intent.putExtra("usuario_id", usuario.getId());
                    startActivity(intent);
                });
            } else {
                botones[i].setEnabled(false);
                botones[i].setText("LEVEL " + levelNumber + " 🔒\n(Necesitas " + nivel.getUnlock() + " pts)");
            }
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

        if (id == R.id.op_perfil) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("usuario_id", usuario.getId());
            startActivity(intent);
            return true;
        } else if (id == R.id.op_ranking) {
            Intent intent = new Intent(this, RankingActivity.class);
            intent.putExtra("usuario_id", usuario.getId());
            startActivity(intent);
            return true;
        } else if (id == R.id.op_salir) {
            storage.cerrarSesion();
            finishAffinity();
            return true;
        } else if (id == R.id.op_cheat) {
            usuario.setCurrent_lvl(6);
            usuario.setLife(5);
            storage.actualizarUsuario(usuario);
            Toast.makeText(this, "MODO CHETO 😈", Toast.LENGTH_LONG).show();
            invalidateOptionsMenu();
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int obtenerAvatar(String nombre) {
        switch (nombre) {
            case "avatar1": return R.drawable.avatar1;
            case "avatar2": return R.drawable.avatar2;
            case "avatar3": return R.drawable.avatar3;
            default: return R.drawable.avatar1;
        }
    }
}