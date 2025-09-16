import dao.EventoDAO;
import dao.EventoDAOImpl;
import model.Evento;
import model.Frequencia;
import java.util.Date;
import java.util.List;

public class TesteEventoDAO {
    public static void main(String[] args) {
        System.out.println("=== TESTE DO EVENTO DAO ===");
        
        EventoDAO eventoDAO = new EventoDAOImpl();
        
        // 1. Teste: Listar todos os eventos existentes
        System.out.println("\n1. 📋 LISTANDO EVENTOS EXISTENTES:");
        List<Evento> eventos = eventoDAO.listarTodos();
        for (Evento evento : eventos) {
            System.out.println("   → " + evento);
        }
        
        // 2. Teste: Buscar evento por ID
        System.out.println("\n2. 🔍 BUSCANDO EVENTO POR ID (ID=1):");
        Evento evento = eventoDAO.buscarPorId(1);
        if (evento != null) {
            System.out.println("   ✅ Encontrado: " + evento);
        } else {
            System.out.println("   ❌ Evento não encontrado!");
        }
        
        // 3. Teste: Inserir novo evento
        System.out.println("\n3. ➕ INSERINDO NOVO EVENTO:");
        Evento novoEvento = new Evento();
        novoEvento.setDescricaoEvento("Workshop de Java Persistence");
        novoEvento.setEmail("java@workshop.com");
        novoEvento.setDate(new Date()); // Data atual
        novoEvento.setAlarme(true);
        novoEvento.setFrequencia(Frequencia.SEMANAL);
        
        eventoDAO.salvar(novoEvento);
        System.out.println("   ✅ Novo evento inserido com ID: " + novoEvento.getId());
        
        // 4. Teste: Listar novamente para ver o novo evento
        System.out.println("\n4. 📋 LISTANDO APÓS INSERÇÃO:");
        eventos = eventoDAO.listarTodos();
        for (Evento e : eventos) {
            System.out.println("   → " + e);
        }
        
        System.out.println("\n=== TESTE CONCLUÍDO ===");
    }
}