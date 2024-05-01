package com.example.dadosmeteorologicos.db;

import com.example.dadosmeteorologicos.model.Registro;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SuspeitoSQL extends IniciaBanco {
    // Atributo de conexão
    protected Connection conn;

    public SuspeitoSQL() {
        conn = super.conectarBanco();
    }
    // Método para buscar registros suspeitos no banco
    public List<Registro> buscaRegistrosSuspeitosBanco() {
        // Lista de registros suspeitos
        List<Registro> listaRegistros = new ArrayList<>();
        // Tenta buscar os registros suspeitos no banco
        try {
            if(conn != null){
                // Query para buscar os registros suspeitos
                String sql = "SELECT * FROM registro WHERE suspeito = true";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                // Adiciona os registros suspeitos na lista
                while (rs.next()) {
                    int id = rs.getInt("id");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    LocalTime hora = rs.getTime("hora").toLocalTime();
                    String estacao = rs.getString("estacao");
                    String siglaCidade = rs.getString("siglaCidade");
                    String tipo = rs.getString("tipo");
                    Double valor = rs.getDouble("valor");
                    boolean suspeito = rs.getBoolean("suspeito");
                    Registro registro = new Registro(id, data, hora, estacao, siglaCidade, tipo, valor, suspeito);
                    listaRegistros.add(registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna a lista de registros suspeitos
        return listaRegistros;
    
}
}