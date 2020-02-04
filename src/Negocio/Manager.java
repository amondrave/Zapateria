/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DAO.AdministradorDAOMySql;
import DAO.ClienteDAOMySql;
import DAO.ColorDAOMySql;
import DAO.ManagerDAO;
import DAO.SuelaDAOMySql;
import DAO.VentaDAOMySql;
import DAO.ZapatoDAOMySql;
import DAOInterfaces.DAOManager;
import DAOInterfaces.PDFException;
import Modelo.Venta;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import static java.lang.String.valueOf;
import java.sql.SQLException;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Siensy 
 */
public class Manager implements DAOManager {
    private Conexion co = null;
    
    private ManagerDAO dao = null;
    
    public Manager() throws SQLException{
     co = new Conexion();
     dao = new ManagerDAO(co.getConnection());
    }

    @Override
    public ClienteDAOMySql getClienteDAOMySql() {
        return dao.getClienteDAOMySql();
    }

    @Override
    public ColorDAOMySql getColorDAOMySql() {
        return dao.getColorDAOMySql();
    }

    @Override
    public SuelaDAOMySql getSuelaDAOMySql() {
        return dao.getSuelaDAOMySql();
    }

    @Override
    public VentaDAOMySql getVentaDAOMySql() {
        return dao.getVentaDAOMySql();
    }

    @Override
    public ZapatoDAOMySql getZapatoDAOMySql() {
        return dao.getZapatoDAOMySql();
    }

    @Override
    public AdministradorDAOMySql getAdministradorDAOMySql() {
        return dao.getAdministradorDAOMySql();
    }
    
    /**
     * Metodo que nos ayuda a crear pdf a partir del id de a factura
     * se utiliza la libreria de Itext
     * @param ruta en donde se almacenara el pdf
     * @param factura que se quiere generar de pdf
     */
    public void generarPDF(String ruta , long factura) throws FileNotFoundException, PDFException{
    String contenido = "";
    
    try{
     FileOutputStream fl = new FileOutputStream(ruta+".pdf");
     Venta venta = new Venta();
     VentaDAOMySql v = getVentaDAOMySql();
     venta = v.obtener(factura);
     contenido+=" Factura número : "+venta.getFactura()+" \n"
             + " y Corresponde al cliente identificado con cedula: "+venta.getCliente()
             + " por el numero de pares: "+venta.getPares() +" del zapato: "+venta.getZapato()
             +" \n el total de la factura es: "+venta.getPrecio();
     Document doc = new Document();
     PdfWriter.getInstance(doc, fl);
     doc.open();
     doc.add(new Paragraph(contenido));
     doc.close();
     System.out.println("Pdf generado correctamente");
    
    }catch(Exception e){
     throw new PDFException("No se puede generar el pdf se ha presentado un error",e);
    }
    
    }
    
    
}
