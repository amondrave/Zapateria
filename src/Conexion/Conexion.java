/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Angel Yesid Mondragon Rodriguez - amondrave54@gmail.com
 * @author Sinsy 
 * Clase que nos permitira conectarnos a nuestra base de datos
 * MySql usando el driver de jdbc
 */
public class Conexion {

    private static Connection con;
    // Declaramos los datos de conexion a la bd
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "";
    //Cambiar el puerto , en mi pc uso el 3380 pero usted me imagino que usa el 3306
    private static final String url = "jdbc:mysql://localhost:3306/zapateria";
    
    /**
     * Constructor vacio que llama el metodo conector
     * Cuando se instancie la clase se establecera la conexion con la base de datos
     * @throws SQLException  
     */
    public Conexion() throws SQLException{
      conector();
    }

    // Funcion que va conectarse a mi bd de mysql
    public void conector() throws SQLException {
        // Reseteamos a null la conexion a la bd
        con = null;
        try {
            Class.forName(driver);
            // Nos conectamos a la bd
            con = (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con != null) {
                System.out.println("Conexion establecida");
            }
        } // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("error al conectar "+e);
        }
    }
    
    /*
    *Con este metodo se obtiene la conexion para que pueda ser llamada desde las otras clases
    * @return connection
    */
    public Connection getConnection() throws SQLException{
     conector();
     return con;
    }

}
