package dao;

import java.util.List;
import model.Evento;

public interface EventoDAO {
    void salvar(Evento evento);
    void atualizar(Evento evento);
    void excluir(int id);
    Evento buscarPorId(int id);
    List<Evento> listarTodos();
    List<Evento> buscarPorDescricao(String descricao);
}