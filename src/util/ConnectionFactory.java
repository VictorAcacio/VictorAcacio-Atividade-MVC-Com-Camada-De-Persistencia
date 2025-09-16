package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/eventos_db";
    private static final String USER = "root";
    private static final String PASSWORD = "135579";
    
    // Bloco estático para registrar o driver manualmente
    static {
        try {
            // Tente a nova classe do driver (MySQL Connector/J 8.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver MySQL 8.x carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            try {
                // Fallback para a classe antiga (MySQL 5.x)
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("✅ Driver MySQL 5.x carregado com sucesso!");
            } catch (ClassNotFoundException ex) {
                System.err.println("❌ Nenhum driver MySQL encontrado!");
                System.err.println("Verifique se o arquivo mysql-connector-j-8.0.33.jar está no classpath");
                throw new RuntimeException("Driver MySQL não encontrado", ex);
            }
        }
    }
    
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexão com MySQL estabelecida com sucesso!");
            return connection;
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar com o banco de dados: " + e.getMessage());
            System.err.println("Verifique:");
            System.err.println("1. Se o MySQL está rodando");
            System.err.println("2. Se o usuário e senha estão corretos"); 
            System.err.println("3. Se o banco 'eventos_db' existe");
            throw new RuntimeException("Falha na conexão com o banco de dados", e);
        }
    }
    
    public static void testarConexao() {
        System.out.println("=== Teste de Conexão ===");
        System.out.println("URL: " + URL);
        System.out.println("Usuário: " + USER);
        
        try (Connection conn = getConnection()) {
            System.out.println("✅ Conexão bem-sucedida!");
            System.out.println("Banco de dados: " + conn.getCatalog());
        } catch (SQLException e) {
            System.out.println("❌ Falha na conexão: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        testarConexao();
    }
}