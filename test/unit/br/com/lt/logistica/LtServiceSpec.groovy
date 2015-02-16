package br.com.lt.logistica

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(LtService)
class LtServiceSpec extends Specification {



    void "teste calculaCustoRota"() {

        expect:
        service.calculaCustoRota(distancia, autonomia, valorCombustivel) == resultado

        where: "casos de calculo"
        distancia| autonomia  | valorCombustivel | resultado
       10   	 |  10        | 1.0		        | 1.0
        25     	|  10         | 2.5	            |  6.25
        1000    |   14        |   3.40          |  242.86

    }

}

