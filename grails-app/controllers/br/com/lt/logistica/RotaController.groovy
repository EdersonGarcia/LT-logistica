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

        rotaInstance.save flush: true
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

        rotaInstance.save flush: true
        respond rotaInstance, [status: OK]
    }

    @Transactional
    def delete(Rota rotaInstance) {

        if (rotaInstance == null) {
            render status: NOT_FOUND
            return
        }

        rotaInstance.delete flush: true
        render status: NO_CONTENT
    }

}