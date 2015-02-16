package br.com.lt.logistica

import grails.converters.JSON

import java.beans.Transient

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RotaController {
    def ltService
    static scaffold = true
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']


    @Transactional(readOnly = true)
       def melhorRota(String mapa,String origem , String destino, Integer autonomia , Double valorCombustivel){
        println(mapa)
        if(!origem.isEmpty() && !destino.isEmpty() &&
                autonomia>0  && valorCombustivel>0 && !mapa.isEmpty()) {
            Localizacao origemObj , destinoObj

             origemObj = ltService.buscarLocalizacaoPorNome(mapa,origem)
            destinoObj = ltService.buscarLocalizacaoPorNome(mapa,destino)
            if(origem!=null && destino!=null) {
                def melhorRota = ltService.melhorRota(origemObj, destinoObj)
                melhorRota << [custo:ltService.calculaCustoRota(melhorRota.distancia.toInteger(),autonomia,valorCombustivel)]
                render([melhorRota]) as JSON
                }else{

                render (status:NOT_ACCEPTABLE )
            }
        }else{

            render (status:NOT_ACCEPTABLE )
        }
    }

    def salvarRota(String mapa, String origem , String destino, int kilometragem ){
        if(mapa == null || origem == null|| destino == null || kilometragem <=0){
            respond(info:'parametros invalidos')
            println(params)
            return
        }
        def rota = ltService.salvarRota(mapa,origem,destino,kilometragem)
        respond([id:rota.id,origem:rota.origem.nome,destino:rota.destino.nome,kilometragem:rota.kilometragem]) as JSON
    }

}

