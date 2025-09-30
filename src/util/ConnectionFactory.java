package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection conn = null;

    // Retorna a conexão única
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
                System.out.println("SUCESSO - Conexão com o banco estabelecida!");
            } catch (SQLException e) {
                throw new RuntimeException("FALHA - Erro ao conectar com o banco: " + e.getMessage(), e);
            }
        }
        return conn;
    }

    // Carrega as propriedades do arquivo externo
    private static Properties loadProperties() {
        Properties props = new Properties();
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            props.load(fs);
        } catch (IOException e) {
            throw new RuntimeException("FALHA - Erro ao carregar db.properties", e);
        }
        return props;
    }

    // Fecha a conexão
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Conexão fechada!");
            } catch (SQLException e) {
                throw new RuntimeException("FALHA - Erro ao fechar conexão", e);
            }
        }
    }
}
