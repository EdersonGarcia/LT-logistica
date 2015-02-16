        package br.com.lt.logistica

        import busca.Dijkstras
        import busca.Edge
        import grails.transaction.Transactional


        @Transactional
        class LtService {

            def melhorRota (Localizacao origem, Localizacao destino){
                def  dijkstra = new Dijkstras(criaGrafoParaBusca(), origem.id.toString(), destino.id.toString())
                if(!dijkstra)
                {
                    return [info: 'Não foram encontrados rotas disponiveis para o destino escolhido']

                }
                def distancia = dijkstra.calculateShortestPath()
                def rotaIdealId = dijkstra.shortestPath
                def rotaIdeal = buscaRotasPorId(rotaIdealId)
                return [rota: rotaIdeal,distancia: distancia]
            }

            def calculaCustoRota(Integer distancia = 0 , Integer autonomia,Double valorCombustivel){
                def custo =  (distancia / autonomia * valorCombustivel)

                return custo.toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP)
            }

          private  def buscaRotasPorId(def listaIdRotas = []){
                def rotas=[]
                listaIdRotas.each {
                    rotas += Localizacao.read(it).nome
                }
                return  rotas
            }
            def buscarLocalizacaoPorNome(String nomeMapa , String nomeRota){
                Localizacao localizacao = Localizacao.createCriteria().get {
                    eq('nome',nomeRota)
                            mapa{
                                eq('nome',nomeMapa)
                            }
                }
                return  localizacao
            }

            private def criaGrafoParaBusca(){
                def grafo =[]
                def rotas = Rota.list()
                rotas.each {
                    grafo << new Edge(node1: it.origem.id,node2: it.destino.id,distance: it.kilometragem)
                }
                return grafo
            }
         def salvarRota(String mapaNome, String origemNome , String destinoNome, int kilometragem = 0){
             def mapa = Mapa.findByNome(mapaNome)
             def origem , destino
                println(mapa)
             def rota
             if(mapa==null){
                 mapa = new Mapa(nome: mapaNome).save(flush:true, failOnError: true)
             }

              origem = Localizacao.findByNomeAndMapa(origemNome,mapa)
             if(origem==null){
                 origem = new Localizacao(nome: origemNome,mapa:mapa).save(flush:true, failOnError: true)
             }

             destino = Localizacao.findByNomeAndMapa(destinoNome,mapa)
             if(destino==null){
                 destino = new Localizacao(nome: destinoNome,mapa:mapa).save(flush:true, failOnError: true)
             }
             rota = Rota.findByOrigemAndDestino(origem,destino)
                if(rota== null) {
                    return new Rota(origem: origem, destino: destino, kilometragem: kilometragem).save(flush: true, failOnError: true)
                }else{
                    rota.kilometragem = kilometragem
                   return rota.save(flush: true, failOnError: true)
                }

         }

        }
