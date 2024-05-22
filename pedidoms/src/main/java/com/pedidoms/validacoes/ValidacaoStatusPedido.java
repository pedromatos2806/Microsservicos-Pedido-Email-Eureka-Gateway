package com.pedidoms.validacoes;



import com.pedidoms.enums.StatusPedido;

public class ValidacaoStatusPedido {

	public static boolean verificarStatusPedido(StatusPedido status) {
	    switch (status) {
	        case Solicitado:
	        case Cancelado:
	        case Transito:
	        case Recebido:
	            return true;
	        default:
	            return false;
	    }
	}

}

