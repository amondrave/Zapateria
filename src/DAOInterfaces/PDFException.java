/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterfaces;

/**
 *
 * @author Angel Yesid Mondraón - amondrave54@gmail.com
 * @author Sinsy
 */
public class PDFException extends Exception{

    public PDFException(String message) {
        super(message);
    }

    public PDFException(String message, Throwable cause) {
        super(message, cause);
    }

    public PDFException(Throwable cause) {
        super(cause);
    }
    
}
