package com.example.ercimusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ercimusic.models.Level;
import com.example.ercimusic.models.Question;
import com.example.ercimusic.models.Usuario;
import com.example.ercimusic.LocalStorageManager;
import com.example.ercimusic.DataInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class game extends AppCompatActivity {

    private Usuario usuario;
    private Button btnVolverMenu;
    private int nivel;
    private List<Question> preguntas;
    private Question preguntaActual;
    private MediaPlayer mp;
    private HashMap<String, Integer> audioMap = new HashMap<>();
    private Button btn1, btn2, btn3, btn4, btnPlay;
    private LocalStorageManager storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnPlay = findViewById(R.id.btnPlay);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnVolverMenu = findViewById(R.id.btnVolverMenu);

        // ELIMINADO StrictMode - ya no es necesario

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Game");
        }

        int userId = getIntent().getIntExtra("usuario_id", -1);
        nivel = getIntent().getIntExtra("nivel", 1);

        storage = new LocalStorageManager(this);
        usuario = storage.obtenerUsuarioPorId(userId);

        if (usuario == null) {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        cargarPreguntas();
        cargarPreguntaAleatoria();

        btnPlay.setOnClickListener(v -> reproducirAudio());

        btn1.setOnClickListener(v -> comprobar(btn1.getText().toString()));
        btn2.setOnClickListener(v -> comprobar(btn2.getText().toString()));
        btn3.setOnClickListener(v -> comprobar(btn3.getText().toString()));
        btn4.setOnClickListener(v -> comprobar(btn4.getText().toString()));

        inicializarAudios();

        btnVolverMenu.setOnClickListener(v -> volverAlMenu());
    }

    private void cargarPreguntas() {
        List<Question> todasLasPreguntas = DataInitializer.getPreguntasIniciales();
        preguntas = new ArrayList<>();
        for (Question q : todasLasPreguntas) {
            if (q.getLevel() == nivel) {
                preguntas.add(q);
            }
        }
    }

    private void inicializarAudios() {
        audioMap.put("01-1", R.raw._01_1);
        audioMap.put("02-1", R.raw._02_1);
        audioMap.put("03-1", R.raw._03_1);
        audioMap.put("04-1", R.raw._04_1);
        audioMap.put("05-1", R.raw._05_1);

        audioMap.put("06-2", R.raw._06_2);
        audioMap.put("07-2", R.raw._07_2);
        audioMap.put("08-2", R.raw._08_2);
        audioMap.put("09-2", R.raw._09_2);
        audioMap.put("10-2", R.raw._10_2);

        audioMap.put("11-3", R.raw._11_3);
        audioMap.put("12-3", R.raw._12_3);
        audioMap.put("13-3", R.raw._13_3);
        audioMap.put("14-3", R.raw._14_3);
        audioMap.put("15-3", R.raw._15_3);

        audioMap.put("16-4", R.raw._16_4);
        audioMap.put("17-4", R.raw._17_4);
        audioMap.put("18-4", R.raw._18_4);
        audioMap.put("19-4", R.raw._19_4);
        audioMap.put("20-4", R.raw._20_4);

        audioMap.put("21-5", R.raw._21_5);
        audioMap.put("22-5", R.raw._22_5);
        audioMap.put("23-5", R.raw._23_5);
        audioMap.put("24-5", R.raw._24_5);
        audioMap.put("25-5", R.raw._25_5);

        audioMap.put("26-6", R.raw._26_6);
        audioMap.put("27-6", R.raw._27_6);
        audioMap.put("28-6", R.raw._28_6);
        audioMap.put("29-6", R.raw._29_6);
        audioMap.put("30-6", R.raw._30_6);
    }

    private void cargarPreguntaAleatoria() {
        if (preguntas.isEmpty()) {
            Toast.makeText(this, "Nivel completado!", Toast.LENGTH_SHORT).show();
            volverAlMenu();
            return;
        }

        Random r = new Random();
        int index = r.nextInt(preguntas.size());
        preguntaActual = preguntas.get(index);

        btn1.setText(preguntaActual.getAnswer1());
        btn2.setText(preguntaActual.getAnswer2());
        btn3.setText(preguntaActual.getAnswer3());
        btn4.setText(preguntaActual.getAnswer4());

        preguntas.remove(index);
    }

    private void reproducirAudio() {
        if (mp != null) {
            mp.release();
        }

        Integer audioId = audioMap.get(preguntaActual.getSong());
        if (audioId == null) {
            Toast.makeText(this, "Audio no encontrado", Toast.LENGTH_SHORT).show();
            return;
        }

        mp = MediaPlayer.create(this, audioId);
        mp.start();
    }

    private void comprobar(String respuesta) {
        boolean correcta = preguntaActual.validarRespuesta(respuesta);

        if (correcta) {
            if (nivel == usuario.getCurrent_lvl()) {
                usuario.setScore(usuario.getScore() + 1);
                Toast.makeText(this, "Correct! +1 🎯", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Correct! (No score in old levels 💅)", Toast.LENGTH_SHORT).show();
            }
        } else {
            usuario.setLife(usuario.getLife() - 1);
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();

            if (mp != null) {
                mp.release();
            }
            mp = MediaPlayer.create(this, R.raw.error);
            mp.start();
        }

        if (usuario.getLife() <= 0) {
            Toast.makeText(this, "GAME OVER", Toast.LENGTH_LONG).show();
            volverAlMenu();
            return;
        }

        actualizarBD();
        comprobarSubidaNivel();
        invalidateOptionsMenu();
        cargarPreguntaAleatoria();
    }

    private void actualizarBD() {
        storage.actualizarUsuario(usuario);
    }

    private void comprobarSubidaNivel() {
        List<Level> niveles = DataInitializer.getNivelesIniciales();
        Level siguiente = null;

        for (Level l : niveles) {
            if (l.getId_level() == nivel + 1) {
                siguiente = l;
                break;
            }
        }

        if (usuario.getCurrent_lvl() >= 6) {
            // El usuario puede jugar todo lo que quiera si es nivel 6
        } else if (siguiente != null && usuario.getScore() >= siguiente.getUnlock()) {
            usuario.setCurrent_lvl(nivel + 1);
            usuario.setLife(3);
            actualizarBD();
            Toast.makeText(this, "LEVEL UP! 🎉", Toast.LENGTH_LONG).show();
        }
    }

    private void volverAlMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("usuario_id", usuario.getId());
        startActivity(intent);
        finish();
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
        } else if (id == R.id.op_ranking) {
            Intent intent = new Intent(this, RankingActivity.class);
            intent.putExtra("usuario_id", usuario.getId());
            startActivity(intent);
            return true;
        } else if (id == R.id.op_salir) {
            volverAlMenu();
            return true;
        } else if (id == R.id.op_cheat) {
            usuario.setCurrent_lvl(6);
            usuario.setLife(5);
            actualizarBD();
            Toast.makeText(this, "MODO CHETO 😈", Toast.LENGTH_LONG).show();
            invalidateOptionsMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
}