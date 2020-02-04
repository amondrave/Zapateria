/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Conexion.Conexion;
import DAO.ClienteDAOMySql;
import DAO.VentaDAOMySql;
import DAOInterfaces.DAOException;
import DAOInterfaces.PDFException;
import Modelo.Cliente;
import Modelo.Venta;
import Negocio.Imagen;
import Negocio.Manager;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author discarok
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, DAOException, FileNotFoundException, PDFException, IOException {
        // TODO code application logic here
        
        Conexion con = new Conexion();
        con.conector();
        
        Imagen obj = new Imagen();
        
        ImageIO.write(obj.redimensionar("src/Imagenes/us1.jpg", 0.50), "jpg", new File("src/Imagenes/us.jpg"));
        
        ClienteDAOMySql c = new ClienteDAOMySql(con.getConnection());
        //Cliente cliente = new Cliente("100","angel","calle 3","111"); 
        //c.insertar(cliente);
       
        Cliente cli = c.obtener("100");
        System.out.println(cli.toString());
        
        //VentaDAOMySql venta = new VentaDAOMySql(con.getConnection());
         /**
        
         Venta  ventas = new Venta();
         ventas.setCliente("100");
         ventas.setZapato("01");
         ventas.setPares(6);
         ventas.setPrecio(3000.0);
         ventas.setFecha(new Date(2020,2,1));
         
         venta.insertar(ventas);
         */
         
        //System.out.print(""+venta.Contar());
         
         
         
         //Manager man = new Manager();
         //man.generarPDF("/home/discarok/Escritorio/Zapateria/hola", 1);
    }
    
}
