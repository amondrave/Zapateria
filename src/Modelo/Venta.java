/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy
 */
public class Venta {
    private Long factura;
    
    private String zapato;
    
    private String cliente;
    // fecha en la que se genera la factura
    private Date fecha;
    
    private int pares;
    
    private double precio;

    public Venta() {
    }

    public Venta(long factura, String zapato, String cliente, Date fecha, int pares, double precio) {
        this.factura = factura;
        this.zapato = zapato;
        this.cliente = cliente;
        this.fecha = fecha;
        this.pares = pares;
        this.precio = precio;
    }

    public long getFactura() {
        return factura;
    }

    public void setFactura(long factura) {
        this.factura = factura;
    }

    public String getZapato() {
        return zapato;
    }

    public void setZapato(String zapato) {
        this.zapato = zapato;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPares() {
        return pares;
    }

    public void setPares(int pares) {
        this.pares = pares;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Venta{" + "factura=" + factura + ", zapato=" + zapato + ", cliente=" + cliente + ", fecha=" + fecha + ", pares=" + pares + ", precio=" + precio + '}';
    }
    
    
    
}
