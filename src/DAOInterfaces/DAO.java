/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterfaces;

import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy
 * @param <T> Objeto del modelo que utiliaremos para la base de datos
 * @param <K> clave de a tabla que esta representada como atributo en un objeto
 */
public interface DAO<T,K> {
    void insertar(T objeto) throws DAOException;
    
    void eliminar(T objeto) throws DAOException;
    
    void modificar(T objeto) throws DAOException;
    
    List<T>obtenerTodos() throws DAOException;
    
    //metodo que me trae los objetos que contengan similitudes en letras
    List<T>ObtenerPorLetra(K clave) throws DAOException;
    
    T obtener(K clave) throws DAOException;
    
    T convertir (ResultSet rs) throws DAOException ;
}
