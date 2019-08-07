package com.example.findbug.Dominio;

public class Inseto {

    public int codigo, id;
    public String inf_adicionais;
    public String tipo;
    public String lavoura;

    public Inseto(){

    }

    public Inseto(int codigo, String tipo, String lavoura, String inf_adicionais){

        this.codigo         = codigo;
        this.tipo           = tipo;
        this.lavoura        = lavoura;
        this.inf_adicionais = inf_adicionais;

    }

    public Inseto(String tipo, String lavoura, String inf_adicionais){

            this.tipo           = tipo;
            this.lavoura        = lavoura;
            this.inf_adicionais = inf_adicionais;

    }

    public Inseto(int id){

        this.id = id;

    }

    //>========================================================================


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getInf_adicionais() {
        return inf_adicionais;
    }

    public void setInf_adicionais(String inf_adicionais) {
        this.inf_adicionais = inf_adicionais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLavoura() {
        return lavoura;
    }

    public void setLavoura(String lavoura) {
        this.lavoura = lavoura;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
