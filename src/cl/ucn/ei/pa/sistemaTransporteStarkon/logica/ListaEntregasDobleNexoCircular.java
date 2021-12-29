package cl.ucn.ei.pa.sistemaTransporteStarkon.logica;

import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Documento;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Encomienda;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Entrega;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.NodoEntrega;
import cl.ucn.ei.pa.sistemaTransporteStarkon.dominio.Valija;

public class ListaEntregasDobleNexoCircular {
	private NodoEntrega first;
	private NodoEntrega last;
	private int size;
	public ListaEntregasDobleNexoCircular() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	public void agregarPrimero(Entrega entrega) {
		NodoEntrega nuevoNodoEntrega = new NodoEntrega(entrega);
		if(first == null){
			last = nuevoNodoEntrega;
		}
		else{
		 first.setPrevious(nuevoNodoEntrega);
		}
		
		nuevoNodoEntrega.setNext(first);
		first = nuevoNodoEntrega; 
		size++;
	}
	
	public void agregarUltimo(Entrega entrega) {
		NodoEntrega nuevoNodoEntrega = new NodoEntrega(entrega);
		if(first == null) {
			 first = nuevoNodoEntrega;
		}
		else {
			 last.setNext(nuevoNodoEntrega);
			 nuevoNodoEntrega.setPrevious(last);
		}
		last = nuevoNodoEntrega; 
		size++;
	}
	
	public Entrega buscarEntrega(int codigo) {
		NodoEntrega current = first;
		while(current != null && current.getEntrega().getCodigo() != codigo){
			current = current.getNext();
		}
		if (current != null) { 
			return current.getEntrega();
		}
		
		return null;
	}

	public NodoEntrega getFirst() {
		return first;
	}

	public void setFirst(NodoEntrega first) {
		this.first = first;
	}

	public NodoEntrega getLast() {
		return last;
	}

	public void setLast(NodoEntrega last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		String salida = "";
		NodoEntrega current = first;
		while(current != null) {
			Entrega entrega = current.getEntrega();
			int codigo = entrega.getCodigo();
			String rutClienteRemitente = entrega.getClienteRemitente().getRut();
			String rutClienteDestinatario = entrega.getClienteDestinatario().getRut();
			salida += codigo + ", ";
			if (entrega instanceof Documento) {
				Documento documento = (Documento) entrega;
				salida += "D, "
						+ rutClienteRemitente
						+ ", "
						+ rutClienteRemitente
						+ ", "
						+ documento.getPeso()
						+ ", " + documento.getGrosor()
						+ "\n";
			}
			else if (entrega instanceof Encomienda) {
				Encomienda encomienda = (Encomienda) entrega;
				salida += "E, "
						+ rutClienteRemitente
						+ ", "
						+ rutClienteRemitente
						+ ", "
						+ encomienda.getPeso()
						+ ", " 
						+ encomienda.getLargo() 
						+ ", "
						+ encomienda.getAncho() 
						+ ", "
						+ encomienda.getProfundidad() 
						+ "\n";
			}
		 
			else if (entrega instanceof Valija) {
				Valija valija = (Valija) entrega;
				salida += "V, "
						+ rutClienteRemitente
						+ ", "
						+ rutClienteRemitente
						+ ", "
					    + valija.getMaterial()
						+ ", "
						+ valija.getPeso()
						+ "\n";
			}
			current = current.getNext();
		}
		
		return salida;
	}
}