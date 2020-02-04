/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.DAOException;
import DAOInterfaces.DAOManager;
import com.mysql.jdbc.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author discarok
 */
public class ManagerDAO implements DAOManager {

    private Connection conn;

    private ClienteDAOMySql clientes = null;
    private ColorDAOMySql colores = null;
    private SuelaDAOMySql suelas = null;
    private VentaDAOMySql ventas = null;
    private ZapatoDAOMySql zapatos = null;
    private AdministradorDAOMySql administradores = null;

    /**
     * Constructor que mandaremos a instanciar con la conexión
     *
     * @param conn
     */
    public ManagerDAO(Connection conn) {
        this.conn = conn;
    }

    
    @Override
    public ClienteDAOMySql getClienteDAOMySql() {
        if (clientes == null) {
            clientes = new ClienteDAOMySql(conn);
        }
        return clientes;
    }

    @Override
    public ColorDAOMySql getColorDAOMySql() {
        if (colores == null) {
            colores = new ColorDAOMySql(conn);
        }
        return colores;
    }

    @Override
    public SuelaDAOMySql getSuelaDAOMySql() {
        if (suelas == null) {
            suelas = new SuelaDAOMySql(conn);
        }
        return suelas;
    }

    @Override
    public VentaDAOMySql getVentaDAOMySql() {
        if (ventas == null) {
            ventas = new VentaDAOMySql(conn);
        }
        return ventas;
    }

    @Override
    public ZapatoDAOMySql getZapatoDAOMySql() {
        if (zapatos == null) {
            zapatos = new ZapatoDAOMySql(conn);
        }
        return zapatos;
    }

    @Override
    public AdministradorDAOMySql getAdministradorDAOMySql() {
        if (administradores == null) {
            try {
                administradores = new AdministradorDAOMySql(conn);
            } catch (DAOException ex) {
                Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return administradores;
    }

}
