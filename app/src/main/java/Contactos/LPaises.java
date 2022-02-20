package Contactos;

public class LPaises {
    private String idpais;
    private String nombrePais;
    private String codigopais;

    public LPaises(String idpais, String nombrePais, String codigopais) {
        this.idpais = idpais;
        this.nombrePais = nombrePais;
        this.codigopais = codigopais;
    }

    public LPaises(){}

    public String getIdpais() {
        return idpais;
    }
    public void setIdpais(String idpais) {
        this.idpais = idpais;
    }

    public String getNombrePais() {
        return nombrePais;
    }
    public void setNombrepais(String nombrepais) {
        this.nombrePais = nombrepais;
    }

    public String getCodigopais() {
        return codigopais;
    }
    public void setCodigopais(String codigopais) {
        this.codigopais = codigopais;
    }


}
