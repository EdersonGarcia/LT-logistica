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
                println(distancia)
                def rotaIdealId = dijkstra.shortestPath
                def rotaIdeal = buscaRotasPorId(rotaIdealId)
                return [rota: rotaIdeal,distancia: distancia]
            }

            def calculaCustoRota(Integer distancia = 0 , Integer autonomia,Double valorCombustivel){
                return (distancia / autonomia * valorCombustivel)
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

        }
