import br.com.lt.logistica.Localizacao
import br.com.lt.logistica.Mapa
import br.com.lt.logistica.Rota
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        long initTime = System.currentTimeMillis()
        if (Environment.current == Environment.DEVELOPMENT) {
            println("Ambiente de Desenvolvimento, verificando BD ...")
            if (Mapa.count < 1){
                println("adicionando dados  na base de Desenvolvimento")
                Mapa sp = new Mapa(nome: "SP").save(flush: true)
                Localizacao a =  new Localizacao(mapa: sp, nome: "A").save(flush: true)
                Localizacao b =  new Localizacao(mapa: sp, nome: "B").save(flush: true)
                Localizacao c =  new Localizacao(mapa: sp, nome: "C").save(flush: true)
                Localizacao d =  new Localizacao(mapa: sp, nome: "D").save(flush: true)
                Localizacao e =  new Localizacao(mapa: sp, nome: "E").save(flush: true)
                new Rota(origem: a, destino: b, kilometragem: 10).save(flush: true)
                new Rota(origem: b, destino: d, kilometragem: 15).save(flush: true)
                new Rota(origem: a, destino: c, kilometragem: 20).save(flush: true)
                new Rota(origem: c, destino: d, kilometragem: 30).save(flush: true)
                new Rota(origem: b, destino: e, kilometragem: 50).save(flush: true)
                new Rota(origem: d, destino: e, kilometragem: 30).save(flush: true)
            }else{
                println("Ja Exitem dados na base de Desenvolvimento")
            }
            if (Environment.current == Environment.PRODUCTION) {
                println("Ambiente de Produção!!")
            }
            println("Tempo Gasto nas verificaçãos : ${System.currentTimeMillis() - initTime}  ms")
        }
    }
    def destroy = {
    }
}
