/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.DAOException;
import DAOInterfaces.VentaDAO;
import Modelo.Venta;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import static java.lang.String.valueOf;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy
 */
public class VentaDAOMySql implements VentaDAO {

    // QUERYS sql que son necesarios para hacer consultas y dml en la base de datos
    // los defino finales por que son constantes y no van a cambiar 
    // para mayor facilidad de mantenimiento de código
    final String INSERT = "INSERT INTO venta(zapato,cliente,fecha,pares,precio) VALUES(?,?,?,?,?)";
    final String UPDATE = "UPDATE venta SET zapato = ?, cliente = ?,pares = ?, precio = ? WHERE factura = ?";
    final String DELETE = "DELETE FROM venta WHERE factura = ?";
    final String GETALL = "SELECT factura, zapato, cliente, fecha, precio FROM venta";
    final String GETLISTCHAR = "SELECT factura, zapato, fecha, precio FROM venta WHERE cliente = ?";
    final String GETONE = "SELECT factura, zapato, cliente, fecha,precio,pares FROM venta WHERE factura = ?";

    private Connection conn;
    
    
    
    public long Contar() throws SQLException{
          ResultSet rs = null;
         PreparedStatement stat = null;
         long nRegistros;
         stat = (PreparedStatement) conn.prepareStatement("SELECT count(*) as total FROM venta");
         rs = stat.executeQuery();
         
         if(rs.next()){
         nRegistros=Integer.parseInt(rs.getString("total"));
         }else{
         nRegistros=0;}
         
         return nRegistros;
    }
    
    
    

    public VentaDAOMySql(Connection conn) {
        this.conn = conn;
    }

    /**
     *
     * @param objeto venta que se va a registrar el número de a factura debera
     * generarse automaticamente
     * @throws DAOException
     */
    @Override
    public void insertar(Venta objeto) throws DAOException {
        PreparedStatement start = null;
        ResultSet rs = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(INSERT);
            //start.s(1, objeto.getReferencia());
            start.setString(1, objeto.getZapato());
            start.setString(2, objeto.getCliente());
            java.sql.Date sDate = convert(objeto.getFecha());
            start.setDate(3, sDate);
            start.setInt(4, objeto.getPares());
            start.setDouble(5, objeto.getPrecio());
           // rs = start.getGeneratedKeys();
            if (start.executeUpdate() == 0) {
                throw new DAOException("puede que no se haya guardado");
            }
           /** if (rs.next()) {
                objeto.setFactura(rs.getLong(1));
            } else {
                throw new DAOException("No puedo generar ese ID");
            }*/
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
    public void eliminar(Venta objeto) throws DAOException {
        PreparedStatement start = null;
        ResultSet rs = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(DELETE);
            start.setLong(1, objeto.getFactura());

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
    public void modificar(Venta objeto) throws DAOException {
        PreparedStatement start = null;
        ResultSet rs = null;
        try {
            start = (PreparedStatement) conn.prepareStatement(UPDATE);
            start.setLong(1, objeto.getFactura());

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
    public List<Venta> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Venta> venta = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                venta.add(convertir(rs));
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
        return venta;
    }

    @Override
    public List<Venta> ObtenerPorLetra(Long clave) throws DAOException {
          PreparedStatement stat = null;
        ResultSet rs = null;
        List<Venta> venta = new ArrayList<>();
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETLISTCHAR);
            String c = valueOf(clave);
            stat.setString(1, c);
            rs = stat.executeQuery();
            while (rs.next()) {
                venta.add(convertir(rs));
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
        return venta;
    }

    @Override
    public Venta obtener(Long clave) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Venta venta = null;
        try {
            stat = (PreparedStatement) conn.prepareStatement(GETONE);
            stat.setLong(1, clave);
            rs = stat.executeQuery();
            if (rs.next()) {
                venta = convertir(rs);
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
        return venta;
    }

    @Override
    public Venta convertir(ResultSet rs) throws DAOException {
        Long factura = null;
        String zapato = null;
        String cliente = null;
        Date fecha = null;
        int pares = 0;
        double precio = 0.0;
        Venta venta = null;
        try {
            factura = rs.getLong("factura");
            zapato = rs.getString("zapato");
            cliente = rs.getString("cliente");
            fecha = rs.getDate("fecha");
            pares = rs.getInt("pares");
            precio = rs.getDouble("precio");
            venta = new Venta(factura,zapato,cliente,fecha,pares,precio);
        } catch (SQLException ex) {
            throw new DAOException("No existe", ex);
        }

        return venta;
    }

    /**
     * Metodo que me convierte util.Date a sql.Date 
     * que me permite agg a la base de datos
     * @param uDate
     * @return la fecha en formato sql con las correcciones
     */
     private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        sDate.setYear(sDate.getYear()-1900);
        sDate.setMonth(sDate.getMonth()-1);
        return sDate;
    }
    
}
