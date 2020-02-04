/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.ColorDAO;
import DAOInterfaces.DAOException;
import Modelo.Color;
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
public class ColorDAOMySql implements ColorDAO {

    // QUERYS sql que son necesarios para hacer consultas y dml en la base de datos
    // los defino finales por que son constantes y no van a cambiar 
    // para mayor facilidad de mantenimiento de código
    final String INSERT = "INSERT INTO color(referencia,nombre) VALUES(?,?)";
    final String UPDATE = "UPDATE color SET nombre = ? WHERE referencia = ?";
    final String DELETE = "DELETE FROM color WHERE referencia = ?";
    final String GETALL = "SELECT referencia,nombre FROM color";
    final String GETLISTCHAR = "SELECT   nombre FROM color WHERE referencia LIKE ?";
    final String GETONE = "SELECT referecnia, nombre FROM color WHERE referencia = ?";

    private Connection conn;

    public ColorDAOMySql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Color objeto) throws DAOException {
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
    public void eliminar(Color objeto) throws DAOException {
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
    public void modificar(Color objeto) throws DAOException {
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
    public List<Color> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Color> colores = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                colores.add(convertir(rs));
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
        return colores;
    }

    @Override
    public List<Color> ObtenerPorLetra(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Color> colores = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETLISTCHAR);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            while (rs.next()) {
                colores.add(convertir(rs));
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
        return colores;
    }

    @Override
    public Color obtener(String clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Color color = null;
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setString(1, clave);
            rs = stat.executeQuery();
            if (rs.next()) {
                color = convertir(rs);
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
        return color;
    }

    @Override
    public Color convertir(ResultSet rs) throws DAOException {
        String referencia = "";
        String nombre = null;
        Color color = null;
        try {
            referencia = rs.getString("referencia");
            nombre = rs.getString("nombre");
            color = new Color(referencia, nombre);
        } catch (SQLException ex) {
            throw new DAOException("No existe", ex);
        }

        return color;
    }

}
