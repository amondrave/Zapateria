/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterfaces;


import DAO.*;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy
 * interfaz que nos ayudara a formar un patron singlenton
 * en donde en su impementacion haremos un manager para ofrecer los servicios DAOS
 */
public interface DAOManager {
    
    public ClienteDAOMySql getClienteDAOMySql();
    public ColorDAOMySql getColorDAOMySql();
    public SuelaDAOMySql getSuelaDAOMySql();
    public VentaDAOMySql getVentaDAOMySql();
    public ZapatoDAOMySql getZapatoDAOMySql();
    public AdministradorDAOMySql getAdministradorDAOMySql();
}
