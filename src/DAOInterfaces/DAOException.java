/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterfaces;

/**
 *
 * @author Angel Yesid Mondragon
 * Exception personalizada que nos ayudara a detectar errores especificos de sql
 */
public class DAOException extends Exception{

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
    
    
}
