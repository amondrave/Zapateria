/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.ClienteDAO;
import DAOInterfaces.DAOException;
import Modelo.Cliente;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy Implementacion de la interfaz dao de cliente clase
 * correspondiente que me permite usar sql y java
 */
public class ClienteDAOMySql implements ClienteDAO {

    // QUERYS sql que son necesarios para hacer consultas y dml en la base de datos
    // los defino finales por que son constantes y no van a cambiar 
    // para mayor facilidad de mantenimiento de código
    final String INSERT = "INSERT INTO cliente(cedula,nombre,direccion,telefono) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE cliente SET nombre = ?, direccion = ?,teledono = ? WHERE cedula = ?";
    final String DELETE = "DELETE FROM cliente WHERE cedula = ?";
    final String GETALL = "SELECT cedula, nombre, direccion, telefono FROM cliente";
    final String GETLISTCHAR = "SELECT cedula , nombre FROM cliente WHERE nombre like ?";
    final String GETONE = "SELECT cedula, nombre, direccion, telefono FROM cliente WHERE cedula = ?";

    private Connection conn;

    public ClienteDAOMySql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Cliente objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(INSERT);
            start.setString(1, objeto.getCedula());
            start.setString(2, objeto.getNombre());
            start.setString(3, objeto.getDireccion());
            start.setString(4, objeto.getTelefono());
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
    public void eliminar(Cliente objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(DELETE);
            start.setString(1, objeto.getCedula());
            if (start.executeUpdate() == 0) {
                throw new DAOException("puede que no se haya eliminado");
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
    public void modificar(Cliente objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(UPDATE);
            start.setString(1, objeto.getCedula());
            if (start.executeUpdate() == 0) {
                throw new DAOException("puede que no se haya modificado");
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

    private Cliente clienteConvert(ResultSet rs) throws SQLException {
        String cedula = rs.getString("cedula");
        String nombre = rs.getString("nombre");
        String direccion = rs.getString("direccion");
        String telefono = rs.getString("telefono");
        Cliente cliente = new Cliente(cedula, nombre, direccion, telefono);
        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cliente> cliente = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                cliente.add(clienteConvert(rs));
            } 
        } catch (SQLException ex) {
            throw new DAOException("Error en e sql", ex);
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
        return cliente;
    }
   
    

    @Override
    public List<Cliente> ObtenerPorLetra(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Cliente> cliente = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETLISTCHAR);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            while (rs.next()) {
                cliente.add(clienteConvert(rs));
            } 
        } catch (SQLException ex) {
            throw new DAOException("Error en e sql", ex);
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
        return cliente;
    }

    /**
     *
     * @param clave cedula de cliente
     * @return el cliente que se obtiene a traves de su clave
     */
    @Override
    public Cliente obtener(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            if (rs.next()) {
                cliente = clienteConvert(rs);
            } else {
                throw new DAOException("No existe ese registro en la base");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en e sql", ex);
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
        return cliente;
    }

    @Override
    public Cliente convertir(ResultSet rs) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
