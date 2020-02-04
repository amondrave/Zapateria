/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.DAOException;
import DAOInterfaces.SuelaDAO;
import Modelo.Suela;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy
 */
public class SuelaDAOMySql implements SuelaDAO {

    

    private Connection conn;// QUERYS sql que son necesarios para hacer consultas y dml en la base de datos
    // los defino finales por que son constantes y no van a cambiar 
    // para mayor facilidad de mantenimiento de código
    final String INSERT = "INSERT INTO suela(referencia,nombre) VALUES(?,?)";
    final String UPDATE = "UPDATE suela SET nombre = ? WHERE referencia = ?";
    final String DELETE = "DELETE FROM suela WHERE referencia = ?";
    final String GETALL = "SELECT referencia,nombre FROM suela";
    final String GETLISTCHAR = "SELECT   nombre FROM suela WHERE referencia LIKE ?";
    final String GETONE = "SELECT referecnia, nombre FROM suela WHERE referencia = ?";

    public SuelaDAOMySql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Suela objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(INSERT);
            start.setString(1, objeto.getReferencia());
            start.setString(2, objeto.getNombre());
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
    public void eliminar(Suela objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(DELETE);
            start.setString(1, objeto.getReferencia());
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
    public void modificar(Suela objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(UPDATE);
            start.setString(1, objeto.getReferencia());
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

    @Override
    public List<Suela> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Suela> suelas = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                suelas.add(convertir(rs));
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
        return suelas;
    }

    @Override
    public List<Suela> ObtenerPorLetra(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Suela> suelas = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETLISTCHAR);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            while (rs.next()) {
                suelas.add(convertir(rs));
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
        return suelas;
    }

    @Override
    public Suela obtener(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Suela suela = null;
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            if (rs.next()) {
                suela = convertir(rs);
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
        return suela;
    }

    @Override
    public Suela convertir(ResultSet rs) throws DAOException {
        String referencia = "";
        String nombre = null;
        Suela suela = null;
        try {
            referencia = rs.getString("referencia");
            nombre = rs.getString("nombre");
            suela = new Suela(referencia, nombre);
        } catch (SQLException ex) {
            throw new DAOException("No existe", ex);
        }

        return suela;
    }
}
