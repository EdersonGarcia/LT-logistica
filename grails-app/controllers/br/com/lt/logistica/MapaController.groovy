package br.com.lt.logistica


import grails.transaction.Transactional

@Transactional(readOnly = true)

class MapaController {
    static scaffold =true
}
