package DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {

    private static DBConnection instance;
    private static MysqlDataSource dataSource;

    private DBConnection() {
        try {
            // Cargar propiedades desde el classpath
            Properties props = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("conexion.properties");
            if (is == null) {
                throw new RuntimeException("No se encontró el fichero conexion.properties en resources");
            }
            props.load(is);

            // Configurar DataSource
            dataSource = new MysqlDataSource();
            dataSource.setUrl(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("db.user"));
            dataSource.setPassword(props.getProperty("db.pass"));


            // Probar la conexión al inicializar
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("> Conexión establecida correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar la conexión a la base de datos: " + e.getMessage());
        }
    }

    // Singleton
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Devuelve una nueva conexión
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource no inicializado. Revisa la configuración de la base de datos.");
        }
        return dataSource.getConnection();
    }
}



