package model;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import core.Model;
import core.View;
import dao.EventoDAO;
import dao.EventoDAOImpl;

public class EventoIO implements Model {

    private List<View> views = new ArrayList<>();
    private String notice;
    private EventoDAO eventoDAO; // ← ADICIONAR DAO
    
    public EventoIO() {
        this.eventoDAO = new EventoDAOImpl(); // ← INICIALIZAR DAO
    }
    
    public void salvarEvento(Evento evento) {
        try {
            // SALVAR NO BANCO (usando DAO) - substitui o código de arquivo
            eventoDAO.salvar(evento);
            
            // Notificar views se necessário
            this.notice = "Evento salvo com sucesso! ID: " + evento.getId();
            notificarViews();
            
        } catch(Exception e) {
            e.printStackTrace();
            this.notice = "Erro ao salvar evento: " + e.getMessage();
            notificarViews();
        }
    }
    
    public Vector<Vector<Object>> getEventos() {
        Vector<Vector<Object>> dados = new Vector<Vector<Object>>();
        
        try {
            // BUSCAR DO BANCO (usando DAO) - substitui leitura de arquivo
            List<Evento> eventos = eventoDAO.listarTodos();
            
            for (Evento evento : eventos) {
                Vector<Object> eventoInfo = new Vector<Object>();
                
                eventoInfo.add(evento.getDate()); // Data como Date object
                eventoInfo.add(evento.getDescricaoEvento());
                eventoInfo.add(evento.getFrequencia());
                eventoInfo.add(evento.getEmail());
                eventoInfo.add(evento.isAlarme() ? "ON" : "OFF");
                
                dados.add(eventoInfo);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            this.notice = "Erro ao carregar eventos: " + e.getMessage();
            notificarViews();
        }
        
        return dados;
    }
    
    // MÉTODOS ADICIONAIS ÚTEIS (opcional)
    public void atualizarEvento(Evento evento) {
        try {
            eventoDAO.atualizar(evento);
            this.notice = "Evento atualizado com sucesso!";
            notificarViews();
        } catch(Exception e) {
            e.printStackTrace();
            this.notice = "Erro ao atualizar evento: " + e.getMessage();
            notificarViews();
        }
    }
    
    public void excluirEvento(int id) {
        try {
            eventoDAO.excluir(id);
            this.notice = "Evento excluído com sucesso!";
            notificarViews();
        } catch(Exception e) {
            e.printStackTrace();
            this.notice = "Erro ao excluir evento: " + e.getMessage();
            notificarViews();
        }
    }
    
    // MÉTODOS DE OBSERVER (mantidos intactos)
    @Override
    public void attach(View view) {
        views.add(view);
    }

    @Override
    public void detach(View view) {
        views.remove(view);
    }

    @Override
    public void notificarViews() {
        for(View v: views) {
            v.update(this, notice);
        }
    }
}