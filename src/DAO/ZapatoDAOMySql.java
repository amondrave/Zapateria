/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.DAOException;
import DAOInterfaces.ZapatoDAO;
import Modelo.Zapato;
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
public class ZapatoDAOMySql implements ZapatoDAO {

    // QUERYS sql que son necesarios para hacer consultas y dml en la base de datos
    // los defino finales por que son constantes y no van a cambiar 
    // para mayor facilidad de mantenimiento de código
    final String INSERT = "INSERT INTO zapato(referencia,nombre,suela,color) VALUES(?,?,?,?)";
    final String UPDATE = "UPDATE zapato SET nombre = ?, suela = ?,color = ? WHERE referencia = ?";
    final String DELETE = "DELETE FROM zapato WHERE referencia = ?";
    final String GETALL = "SELECT referencia, nombre, suela, color FROM zapato";
    final String GETLISTCHAR = "SELECT referencia, nombre, suela, color FROM zapato WHERE referencia like '?%'";
    final String GETONE = "SELECT referencia, nombre, suela, color FROM zapato WHERE referencia = ?";
    

    private Connection conn;

    public ZapatoDAOMySql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Zapato objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(INSERT);
            start.setString(1, objeto.getReferencia());
            start.setString(2, objeto.getNombre());
            start.setString(4, objeto.getColor());
            start.setString(3, objeto.getSuela());
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
    public void eliminar(Zapato objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(DELETE);
            start.setString(1, objeto.getReferencia());
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
    public void modificar(Zapato objeto) throws DAOException {
        PreparedStatement start = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(UPDATE);
            start.setString(1, objeto.getReferencia());
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
    public List<Zapato> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Zapato> zapatos = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                zapatos.add(convertir(rs));
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
        return zapatos;
    }

    @Override
    public List<Zapato> ObtenerPorLetra(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Zapato> zapatos = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETLISTCHAR);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            while (rs.next()) {
                zapatos.add(convertir(rs));
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
        return zapatos;
    }

    @Override
    public Zapato obtener(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Zapato zapato = null;
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            if (rs.next()) {
                zapato = convertir(rs);
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
        return zapato;
    }

    @Override
    public Zapato convertir(ResultSet rs) throws DAOException {
        String referencia = "";
        String nombre = null;
        String color = null;
        String suela = null;
        Zapato zapato = null;
        try {
            referencia = rs.getString("referencia");
            nombre = rs.getString("nombre");
            suela = rs.getString("suela");
            color = rs.getString("color");
            zapato = new Zapato(referencia, nombre, color, suela);
        } catch (SQLException ex) {
            throw new DAOException("No existe", ex);
        }

        return zapato;
    }
    
    

}
