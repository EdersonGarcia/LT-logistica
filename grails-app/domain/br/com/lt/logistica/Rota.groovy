package br.com.lt.logistica

class Rota {


    static belongsTo = [origem: Localizacao, destino:Localizacao]
    int kilometragem

    static constraints = {
        origem ()
        destino ()
        kilometragem ()
    }

    String toString(){
        return "Origem: $origem - Destino: $destino Distancia: $kilometragem km"
    }
}
