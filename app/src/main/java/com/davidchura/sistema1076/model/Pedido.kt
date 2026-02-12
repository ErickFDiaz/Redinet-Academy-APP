package com.davidchura.sistema1076.model

data class Pedido(
        val idpedido: String,
        val idempleado: String,
        val fechapedido: String,
        val fechaentrega: String,
        val fechaenvio: String,
        val idempresaenvio: String,
        val cargo: String,
        val destinatario: String,
        val direcciondestinatario: String,
        val ciudaddestinatario: String,
        val regiondestinatario: String?,
        val codigopostaldestinatario: String,
        val paisdestinatario: String,
        val idcliente: String,
        val empleado: String,
        val cliente: String,
        val contacto: String
)
