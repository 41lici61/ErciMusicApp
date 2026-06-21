package com.example.ercimusic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ercimusic.models.Usuario;
import com.example.ercimusic.LocalStorageManager;

import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private ListView lvRanking;
    private Usuario usuario;
    private LocalStorageManager storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        // ELIMINADO StrictMode

        lvRanking = findViewById(R.id.lvRanking);
        storage = new LocalStorageManager(this);

        int userId = getIntent().getIntExtra("usuario_id", -1);
        usuario = storage.obtenerUsuarioPorId(userId);

        if (usuario == null) {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        List<Usuario> ranking = storage.obtenerRanking();

        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(
                this,
                R.layout.item_ranking,
                R.id.tvItemRanking,
                ranking
        );

        lvRanking.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Ranking");
        }
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

        if (id == R.id.op_perfil) {
            Intent intent = new Intent(this, ProfileActivity.class);
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
            // Actualizar ranking
            List<Usuario> ranking = storage.obtenerRanking();
            ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(
                    this,
                    R.layout.item_ranking,
                    R.id.tvItemRanking,
                    ranking
            );
            lvRanking.setAdapter(adapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}