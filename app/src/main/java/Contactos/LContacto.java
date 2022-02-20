package Contactos;

import java.io.Serializable;

public class LContacto implements Serializable {
    String pais,nombres,nota, telefono;
    private Integer id;
   //byte[] imagen;
    public LContacto(Integer id, String pais, String nombres, String telefono, String nota/* ,byte[] imagen*/){
        this.id=id;
        this.pais=pais;
        this.nombres=nombres;
        this.telefono=telefono;
        this.nota=nota;
       // this.imagen=imagen;
    }
    public LContacto(){}

    public Integer getId() {return id;}
    public void setId(Integer id) { this.id = id;}
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombre) {this.nombres = nombre; }
    public String getTelefono() {return telefono; }
    public void setTelefono(String telefono) {this.telefono = telefono; }
    public String getNota() {return nota; }
    public void setNota(String nota) { this.nota = nota; }
    /*public byte[] getImagen() { return imagen; }
    public void setImagen(byte[] imagen){ this.imagen = imagen; }*/
}
