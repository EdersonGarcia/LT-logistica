package br.com.lt.logistica

class Localizacao {

    static belongsTo = [mapa:Mapa]
    String nome

    static constraints = {
        nome nullable: false
    }

    String toString(){
        return "$mapa - $nome"
    }
}
