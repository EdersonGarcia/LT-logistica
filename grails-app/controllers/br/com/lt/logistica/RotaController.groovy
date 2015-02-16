package br.com.lt.logistica

import grails.converters.JSON


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RotaController {
    def ltService
    static scaffold = true
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

/**
 * @api {post} /rota/melhorRota?[mapa][origem][destino][autonomia][valor]
 * @apiParam {String} mapa nome do mapa que deseja realizar a busca exemp: "SP"
 * @apiVersion 0.0.1
 * @apiName melhorRota
 * @apiGroup rota
 * @apiParam {String} destino Nome da localizacao de destino
 * @apiParam {String} origem    Nome  da localizacao de origem
 * @apiParam {Number} kilometragem  distancia em kilometros "18"
 *
 * @apiDescription procura a rota mais curto
 *
 * @apiSuccessExample {json} Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *     ['rota':['A', 'B', 'D'], 'distancia':25, 'custo':6.25]
 *     }
 */
    @Transactional(readOnly = true)
       def melhorRota(String mapa,String origem , String destino, Integer autonomia , Double valorCombustivel){
        if(!origem.isEmpty() && !destino.isEmpty() &&
                autonomia>0  && valorCombustivel>0 && !mapa.isEmpty()) {
            Localizacao origemObj , destinoObj

             origemObj = ltService.buscarLocalizacaoPorNome(mapa,origem)
            destinoObj = ltService.buscarLocalizacaoPorNome(mapa,destino)
            if(origem!=null && destino!=null) {
                def melhorRota = ltService.melhorRota(origemObj, destinoObj)
                melhorRota << [custo:ltService.calculaCustoRota(melhorRota.distancia.toInteger(),autonomia,valorCombustivel)]
                render(melhorRota) as JSON
                }else{

                render (status:NOT_ACCEPTABLE )
            }
        }else{

            render (status:NOT_ACCEPTABLE )
        }
    }
/**
 * @api {post} /rota?[mapa][origem][destino][kilometragem]
 * @apiParam {String} mapa nome do mapa que deseja salvar exemp: "SP"
 * @apiVersion 0.0.1
 * @apiName salvarRota
 * @apiGroup rota
 * @apiParam {String} destino Nome da localizacao de destino
 * @apiParam {String} origem    Nome  da localizacao de origem
 * @apiParam {Number} kilometragem  distancia em kilometros "18"
 *
 *  @apiSuccessExample {json} Success-Response:
 *     HTTP/1.1 200 OK
 *   {
 *   "id":7,"origem":"A","destino":"E","kilometragem":60
 *   }
 * @apiDescription salva uma nova rota
 */
    @Transactional
    def salvarRota(String mapa, String origem , String destino, int kilometragem ){
        if(mapa == null || origem == null|| destino == null || kilometragem <=0){
            respond(info:'parametros invalidos') as JSON
            return
        }
        def rota = ltService.salvarRota(mapa,origem,destino,kilometragem)
        respond([id:rota.id,origem:rota.origem.nome,destino:rota.destino.nome,kilometragem:rota.kilometragem]) as JSON
    }

    /**
     * @api {post} /rota/listarLocalizacoes?[mapa]
     * @apiParam {String} mapa  nome do mapa que deseja realizar a busca

     * @apiVersion 0.0.1
     * @apiName listarLocalizacoes
     * @apiGroup rota
     * @apiSuccessExample {json} Success-Response:
     *     HTTP/1.1 200 OK{
     * {"class":"br.com.lt.logistica.Localizacao","id":3,"mapa":{"class":"br.com.lt.logistica.Mapa","id":1},"nome":"B"},
     * {"class":"br.com.lt.logistica.Localizacao","id":4,"mapa":{"class":"br.com.lt.logistica.Mapa","id":1},"nome":"C"},
     * {"class":"br.com.lt.logistica.Localizacao","id":5,"mapa":{"class":"br.com.lt.logistica.Mapa","id":1},"nome":"D"},
     * {"class":"br.com.lt.logistica.Localizacao","id":6,"mapa":{"class":"br.com.lt.logistica.Mapa","id":1},"nome":"E"
     *}
     *  @apiDescription listar localicoes de um mapa
      */

   def listarLocalizacoes(String mapa){
        def mapaObj = Mapa.findByNome(mapa)
       def localizacao = Localizacao.createCriteria().list {
           eq('mapa',mapaObj)

       }
       if(localizacao == []){
           respond(info:'Não foram encontrados localizacoes para o mapa ')
           return
       }
       respond(localizacao)


       }
   }


