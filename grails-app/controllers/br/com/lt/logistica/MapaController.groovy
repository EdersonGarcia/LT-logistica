package br.com.lt.logistica

import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MapaController extends RestfulController {
    static scaffold = true
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    MapaController() {
            super(Mapa)
        }
    }

