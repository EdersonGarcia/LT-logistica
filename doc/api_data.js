define({ "api": [
  {
    "type": "post",
    "url": "/rota/listarLocalizacoes?[mapa]",
    "title": "",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mapa",
            "description": "<p>nome do mapa que deseja realizar a busca</p> "
          }
        ]
      }
    },
    "version": "0.0.1",
    "name": "listarLocalizacoes",
    "group": "rota",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK{\n{\"class\":\"br.com.lt.logistica.Localizacao\",\"id\":3,\"mapa\":{\"class\":\"br.com.lt.logistica.Mapa\",\"id\":1},\"nome\":\"B\"},\n{\"class\":\"br.com.lt.logistica.Localizacao\",\"id\":4,\"mapa\":{\"class\":\"br.com.lt.logistica.Mapa\",\"id\":1},\"nome\":\"C\"},\n{\"class\":\"br.com.lt.logistica.Localizacao\",\"id\":5,\"mapa\":{\"class\":\"br.com.lt.logistica.Mapa\",\"id\":1},\"nome\":\"D\"},\n{\"class\":\"br.com.lt.logistica.Localizacao\",\"id\":6,\"mapa\":{\"class\":\"br.com.lt.logistica.Mapa\",\"id\":1},\"nome\":\"E\"\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>listar localicoes de um mapa</p> ",
    "filename": "./grails-app/controllers/br/com/lt/logistica/RotaController.groovy",
    "groupTitle": "rota"
  },
  {
    "type": "post",
    "url": "/rota/melhorRota?[mapa][origem][destino][autonomia][valor]",
    "title": "",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mapa",
            "description": "<p>nome do mapa que deseja realizar a busca exemp: &quot;SP&quot;</p> "
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "destino",
            "description": "<p>Nome da localizacao de destino</p> "
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "origem",
            "description": "<p>Nome  da localizacao de origem</p> "
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "kilometragem",
            "description": "<p>distancia em kilometros &quot;18&quot;</p> "
          }
        ]
      }
    },
    "version": "0.0.1",
    "name": "melhorRota",
    "group": "rota",
    "description": "<p>procura a rota mais curto</p> ",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n['rota':['A', 'B', 'D'], 'distancia':25, 'custo':6.25]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "./grails-app/controllers/br/com/lt/logistica/RotaController.groovy",
    "groupTitle": "rota"
  },
  {
    "type": "post",
    "url": "/rota?[mapa][origem][destino][kilometragem]",
    "title": "",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mapa",
            "description": "<p>nome do mapa que deseja salvar exemp: &quot;SP&quot;</p> "
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "destino",
            "description": "<p>Nome da localizacao de destino</p> "
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "origem",
            "description": "<p>Nome  da localizacao de origem</p> "
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "kilometragem",
            "description": "<p>distancia em kilometros &quot;18&quot;</p> "
          }
        ]
      }
    },
    "version": "0.0.1",
    "name": "salvarRota",
    "group": "rota",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "  HTTP/1.1 200 OK\n{\n\"id\":7,\"origem\":\"A\",\"destino\":\"E\",\"kilometragem\":60\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>salva uma nova rota</p> ",
    "filename": "./grails-app/controllers/br/com/lt/logistica/RotaController.groovy",
    "groupTitle": "rota"
  }
] });