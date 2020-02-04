/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Angel Yesid Mondragón - amondrave54@gmail.com
 * @author Sinsy
 */
public class Zapato {
    
    private String referencia;
    
    private String nombre;
    
    private String color;
    
    private String suela;
    
    public Zapato(){
    
    }

    public Zapato(String referencia, String nombre, String color, String suela) {
        this.referencia = referencia;
        this.nombre = nombre;
        this.color = color;
        this.suela = suela;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSuela() {
        return suela;
    }

    public void setSuela(String suela) {
        this.suela = suela;
    }

    @Override
    public String toString() {
        return "Zapato{" + "referencia=" + referencia + ", nombre=" + nombre + ", color=" + color + ", suela=" + suela + '}';
    }
    
    
    
}
