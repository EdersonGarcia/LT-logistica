package br.com.lt.logistica

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(RotaController)
@Mock([Rota,Localizacao,Mapa])
class RotaControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    void " webService listarLocalizacoes teste de resposta"() {
        given:
        def mapa =  new Mapa(nome: 'SP').save(flush: true)
         new Localizacao(nome: 'A', mapa:mapa).save(flush: true)

        when:
        request.method = 'POST'
        response.format = 'json'
        controller.listarLocalizacoes('SP')

        then:
        response.status == 200
        response.contentAsString == '[{"class":"br.com.lt.logistica.Localizacao","id":1,"mapa":{"class":"br.com.lt.logistica.Mapa","id":1},"nome":"A"}]'

    }
}
