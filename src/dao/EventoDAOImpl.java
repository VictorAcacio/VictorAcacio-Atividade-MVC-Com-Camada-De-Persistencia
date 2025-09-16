package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Evento;
import model.Frequencia;
import util.ConnectionFactory;

public class EventoDAOImpl implements EventoDAO {

    @Override
    public void salvar(Evento evento) {
        String sql = "INSERT INTO eventos (descricao_evento, email, data_evento, alarme, frequencia) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, evento.getDescricaoEvento());
            ps.setString(2, evento.getEmail());
            ps.setDate(3, new java.sql.Date(evento.getDate().getTime()));
            ps.setBoolean(4, evento.isAlarme());
            ps.setString(5, evento.getFrequencia().name());
            
            ps.executeUpdate();
            
            // Recuperar o ID gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    evento.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar evento: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(Evento evento) {
        String sql = "UPDATE eventos SET descricao_evento = ?, email = ?, data_evento = ?, alarme = ?, frequencia = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, evento.getDescricaoEvento());
            ps.setString(2, evento.getEmail());
            ps.setDate(3, new java.sql.Date(evento.getDate().getTime()));
            ps.setBoolean(4, evento.isAlarme());
            ps.setString(5, evento.getFrequencia().name());
            ps.setInt(6, evento.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar evento: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir evento: " + e.getMessage(), e);
        }
    }

    @Override
    public Evento buscarPorId(int id) {
        String sql = "SELECT * FROM eventos WHERE id = ?";
        Evento evento = null;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    evento = extrairEventoDoResultSet(rs);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar evento: " + e.getMessage(), e);
        }
        
        return evento;
    }

    @Override
    public List<Evento> listarTodos() {
        String sql = "SELECT * FROM eventos ORDER BY data_evento";
        List<Evento> eventos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                eventos.add(extrairEventoDoResultSet(rs));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar eventos: " + e.getMessage(), e);
        }
        
        return eventos;
    }

    @Override
    public List<Evento> buscarPorDescricao(String descricao) {
        String sql = "SELECT * FROM eventos WHERE descricao_evento LIKE ? ORDER BY data_evento";
        List<Evento> eventos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + descricao + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    eventos.add(extrairEventoDoResultSet(rs));
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar eventos por descrição: " + e.getMessage(), e);
        }
        
        return eventos;
    }

    private Evento extrairEventoDoResultSet(ResultSet rs) throws SQLException {
        Evento evento = new Evento();
        evento.setId(rs.getInt("id"));
        evento.setDescricaoEvento(rs.getString("descricao_evento"));
        evento.setEmail(rs.getString("email"));
        evento.setDate(rs.getDate("data_evento"));
        evento.setAlarme(rs.getBoolean("alarme"));
        
        // Converter String para Enum Frequencia
        String frequenciaStr = rs.getString("frequencia");
        if (frequenciaStr != null) {
            evento.setFrequencia(Frequencia.valueOf(frequenciaStr));
        }
        
        return evento;
    }
}