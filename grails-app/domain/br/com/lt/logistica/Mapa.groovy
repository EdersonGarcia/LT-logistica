package br.com.lt.logistica

class Mapa {

    String nome

    static constraints = {
        nome unique: true
    }

    String toString(){
        return nome
    }
}
