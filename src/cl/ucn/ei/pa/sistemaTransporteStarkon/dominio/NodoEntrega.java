package cl.ucn.ei.pa.sistemaTransporteStarkon.dominio;

public class NodoEntrega {
	private NodoEntrega previous;
	private NodoEntrega next;
	private Entrega entrega;
	
	public NodoEntrega(Entrega entrega) {
		super();
		this.entrega = entrega;
	}

	public NodoEntrega getPrevious() {
		return previous;
	}

	public void setPrevious(NodoEntrega previous) {
		this.previous = previous;
	}

	public NodoEntrega getNext() {
		return next;
	}

	public void setNext(NodoEntrega next) {
		this.next = next;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
}
