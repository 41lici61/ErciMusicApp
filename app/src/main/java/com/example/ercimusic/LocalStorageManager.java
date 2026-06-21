package com.example.ercimusic;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ercimusic.models.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocalStorageManager {

    private static final String PREF_NAME = "ercimusic_prefs";
    private static final String KEY_USUARIO_ACTUAL = "usuario_actual";
    private static final String KEY_USUARIOS = "usuarios";

    private SharedPreferences prefs;

    public LocalStorageManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // --- USUARIO ---

    public void guardarUsuario(Usuario usuario) {
        String json = usuarioToJson(usuario);
        prefs.edit().putString(KEY_USUARIO_ACTUAL, json).apply();

        List<Usuario> usuarios = obtenerTodosLosUsuarios();
        boolean existe = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNickname().equals(usuario.getNickname())) {
                usuarios.set(i, usuario);
                existe = true;
                break;
            }
        }
        if (!existe) {
            usuarios.add(usuario);
        }
        String usuariosJson = usuariosListToJson(usuarios);
        prefs.edit().putString(KEY_USUARIOS, usuariosJson).apply();
    }

    public Usuario obtenerUsuarioActual() {
        String json = prefs.getString(KEY_USUARIO_ACTUAL, null);
        if (json != null && !json.isEmpty()) {
            return jsonToUsuario(json);
        }
        return null;
    }

    public Usuario obtenerUsuarioPorId(int id) {
        List<Usuario> usuarios = obtenerTodosLosUsuarios();
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public Usuario login(String nickname, String pass) {
        List<Usuario> usuarios = obtenerTodosLosUsuarios();
        for (Usuario u : usuarios) {
            if (u.getNickname().equals(nickname) && u.getPass().equals(pass)) {
                guardarUsuario(u);
                return u;
            }
        }
        return null;
    }

    public boolean insertarUsuario(Usuario usuario) {
        List<Usuario> usuarios = obtenerTodosLosUsuarios();

        // Verificar si el nickname ya existe
        for (Usuario u : usuarios) {
            if (u.getNickname().equals(usuario.getNickname())) {
                return false;
            }
        }

        // Generar ID automático
        int maxId = 0;
        for (Usuario u : usuarios) {
            if (u.getId() > maxId) maxId = u.getId();
        }
        usuario.setId(maxId + 1);

        usuarios.add(usuario);
        String json = usuariosListToJson(usuarios);
        prefs.edit().putString(KEY_USUARIOS, json).apply();

        // Guardar como usuario actual
        guardarUsuario(usuario);
        return true;
    }

    public void actualizarUsuario(Usuario usuario) {
        guardarUsuario(usuario);
    }

    public List<Usuario> obtenerRanking() {
        List<Usuario> usuarios = obtenerTodosLosUsuarios();
        // Ordenar por score descendente
        for (int i = 0; i < usuarios.size() - 1; i++) {
            for (int j = 0; j < usuarios.size() - i - 1; j++) {
                if (usuarios.get(j).getScore() < usuarios.get(j + 1).getScore()) {
                    Usuario temp = usuarios.get(j);
                    usuarios.set(j, usuarios.get(j + 1));
                    usuarios.set(j + 1, temp);
                }
            }
        }
        return usuarios;
    }

    public void cerrarSesion() {
        prefs.edit().remove(KEY_USUARIO_ACTUAL).apply();
    }

    // --- MÉTODOS DE CONVERSIÓN MANUAL ---

    private String usuarioToJson(Usuario usuario) {
        try {
            JSONObject json = new JSONObject();
            json.put("id", usuario.getId());
            json.put("pfp", usuario.getPfp());
            json.put("nickname", usuario.getNickname());
            json.put("name", usuario.getName());
            json.put("lastname", usuario.getLastname());
            json.put("pass", usuario.getPass());
            json.put("score", usuario.getScore());
            json.put("life", usuario.getLife());
            json.put("current_lvl", usuario.getCurrent_lvl());
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private Usuario jsonToUsuario(String jsonStr) {
        try {
            JSONObject json = new JSONObject(jsonStr);
            return new Usuario(
                    json.getInt("id"),
                    json.getString("pfp"),
                    json.getString("nickname"),
                    json.getString("name"),
                    json.getString("lastname"),
                    json.getString("pass"),
                    json.getInt("score"),
                    json.getInt("life"),
                    json.getInt("current_lvl")
            );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String usuariosListToJson(List<Usuario> usuarios) {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Usuario u : usuarios) {
                JSONObject json = new JSONObject();
                json.put("id", u.getId());
                json.put("pfp", u.getPfp());
                json.put("nickname", u.getNickname());
                json.put("name", u.getName());
                json.put("lastname", u.getLastname());
                json.put("pass", u.getPass());
                json.put("score", u.getScore());
                json.put("life", u.getLife());
                json.put("current_lvl", u.getCurrent_lvl());
                jsonArray.put(json);
            }
            return jsonArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "[]";
        }
    }

    private List<Usuario> jsonToUsuariosList(String jsonStr) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Usuario u = new Usuario(
                        json.getInt("id"),
                        json.getString("pfp"),
                        json.getString("nickname"),
                        json.getString("name"),
                        json.getString("lastname"),
                        json.getString("pass"),
                        json.getInt("score"),
                        json.getInt("life"),
                        json.getInt("current_lvl")
                );
                usuarios.add(u);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private List<Usuario> obtenerTodosLosUsuarios() {
        String json = prefs.getString(KEY_USUARIOS, null);
        if (json != null && !json.isEmpty()) {
            return jsonToUsuariosList(json);
        }
        return new ArrayList<>();
    }
}