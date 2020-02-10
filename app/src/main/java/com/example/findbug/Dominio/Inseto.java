package com.example.findbug.Dominio;

public class Inseto {

    public int codigo, id;
    public String inf_adicionais;
    public String tipo;
    public String lavoura;
    public String Nome;

    public Inseto(){

    }

    public Inseto(int codigo, String Nome, String tipo, String lavoura, String inf_adicionais) {

        this.codigo         = codigo;
        this.Nome           = Nome;
        this.tipo           = tipo;
        this.lavoura        = lavoura;
        this.inf_adicionais = inf_adicionais;

    }

    public Inseto(String Nome, String tipo, String lavoura, String inf_adicionais) {

        this.Nome = Nome;
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

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getLavoura() {
        return lavoura;
    }

    public void setLavoura(String lavoura) {
        this.lavoura = lavoura;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) { this.id = id; }
}
