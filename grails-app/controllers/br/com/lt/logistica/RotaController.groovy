package br.com.lt.logistica

import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RotaController {
    def ltService
    static scaffold = true
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Transactional
    def save(Rota rotaInstance) {
        if (rotaInstance == null) {
            render status: NOT_FOUND
            return
        }

        rotaInstance.validate()
        if (rotaInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        rotaInstance.save flush:true
        respond rotaInstance, [status: CREATED]
    }

    @Transactional
    def update(Rota rotaInstance) {
        if (rotaInstance == null) {
            render status: NOT_FOUND
            return
        }

        rotaInstance.validate()
        if (rotaInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        rotaInstance.save flush:true
        respond rotaInstance, [status: OK]
    }

    @Transactional
    def delete(Rota rotaInstance) {

        if (rotaInstance == null) {
            render status: NOT_FOUND
            return
        }

        rotaInstance.delete flush:true
        render status: NO_CONTENT
    }

    @Transactional(readOnly = true)
       def melhorRota(MelhorRotaCommand melhorRotaCommand){
        if(melhorRotaCommand.validate()) {
            Localizacao origem , destino
            origem = ltService.buscarLocalizacaoPorNome(melhorRotaCommand.mapa,melhorRotaCommand.origem)
            destino = ltService.buscarLocalizacaoPorNome(melhorRotaCommand.mapa,melhorRotaCommand.destino)
            if(origem!=null && destino!=null) {
                def melhorRota = ltService.melhorRota(origem, destino)
                melhorRota << [custo:ltService.calculaCustoRota(melhorRota.distancia.toInteger(),melhorRotaCommand.autonomia,melhorRotaCommand.valorCombustivel)]
                render([melhorRota]) as JSON
                }else{
                render (status:NOT_ACCEPTABLE )
            }
        }else{
            render (status:NOT_ACCEPTABLE )
        }
    }


}
@grails.validation.Validateable
class MelhorRotaCommand{
    String mapa
    String origem
    String destino
    Integer autonomia
    Double valorCombustivel

    static constraints = {
        mapa blank: false
        destino blank: false
        autonomia blank: false, min: 0
        valorCombustivel blank: false , min: 0D
    }
    String toString(){
        return "$mapa - $destino - $destino - $autonomia - $valorCombustivel"
    }
}
