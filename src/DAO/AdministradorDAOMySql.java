/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.AdministradorDAO;
import DAOInterfaces.DAOException;
import Modelo.Administrador;
import Vista.Login;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy
 */
public class AdministradorDAOMySql implements AdministradorDAO {

    // QUERYS sql que son necesarios para hacer consultas y dml en la base de datos
    // los defino finales por que son constantes y no van a cambiar 
    // para mayor facilidad de mantenimiento de código
    final String INSERT = "INSERT INTO administrador(usuario,contrasena) VALUES(?,?)";
    final String UPDATE = "UPDATE administrador SET contrasena = ? WHERE usuario = ?";
    final String DELETE = "DELETE FROM administrador WHERE usuario = ?";
    final String GETALL = "SELECT usuario, contrasena FROM administrador";
    final String GETLISTCHAR = "SELECT usuario , contrasena FROM administrador WHERE usuario LIKE '?%'";
    final String GETONE = "SELECT usuario, contrasena FROM administrador WHERE usuario = ?";

    private Connection conn;
    Login l;

    public AdministradorDAOMySql(Connection conn) throws DAOException{
        this.conn = conn;
        try {
            login();
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAOMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void login() throws SQLException, DAOException{
        this.l=new Login();
    }

    @Override
    public void insertar(Administrador objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(INSERT);
            start.setString(1, objeto.getUsuario());
            start.setString(2, objeto.getContrasena());
            if (start.executeUpdate() == 0) {
                throw new DAOException("puede que no se haya guardado");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en el sql", ex);
        } finally {
            if (start != null) {
                try {
                    start.close();
                } catch (SQLException ex) {
                    throw new DAOException("error en sql", ex);
                }

            }
        }
    }

    @Override
    public void eliminar(Administrador objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(DELETE);
            start.setString(1, objeto.getUsuario());
            if (start.executeUpdate() == 0) {
                throw new DAOException("puede que no se haya guardado");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en el sql", ex);
        } finally {
            if (start != null) {
                try {
                    start.close();
                } catch (SQLException ex) {
                    throw new DAOException("error en sql", ex);
                }

            }
        }
    }

    @Override
    public void modificar(Administrador objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(UPDATE);
            start.setString(1, objeto.getUsuario());
            if (start.executeUpdate() == 0) {
                throw new DAOException("puede que no se haya guardado");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en el sql", ex);
        } finally {
            if (start != null) {
                try {
                    start.close();
                } catch (SQLException ex) {
                    throw new DAOException("error en sql", ex);
                }

            }
        }
    }

    @Override
    public List<Administrador> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Administrador> ObtenerPorLetra(String clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Administrador obtener(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Administrador admin = null;
        
        
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            if (rs.next()) {
                admin = convertir(rs);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese sus datos", "Error", JOptionPane.ERROR_MESSAGE);
                l.dispose();
                l.setVisible(true);
                //throw new DAOException("No existe ese registro en la base");
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            l.dispose();
            l.setVisible(true);
            //throw new DAOException("Error en e sql", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en e sql", ex);
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    throw new DAOException("error en sql", ex);
                }

            }

        }
        return admin;
    }

    @Override
    public Administrador convertir(ResultSet rs) throws DAOException {
        String usuario = "";
        String contrasena = null;
        Administrador admin = null;
        try {
            usuario = rs.getString("usuario");
            contrasena = rs.getString("contrasena");
            admin = new Administrador(usuario, contrasena);
        } catch (SQLException ex) {
            throw new DAOException("No existe", ex);
        }

        return admin;
    }
}


