package co.edu.utp.misiontic2022.c2.reto4.model.vo;

public class ComprasDeLiderVo {

	private String lider;
	private Double valor;

	public ComprasDeLiderVo(String lider, Double valor) {
		this.lider = lider;
		this.valor = valor;
	}

	public String getLider() {
		return lider;
	}

	public void setLider(String lider) {
		this.lider = lider;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ComprasDeLiderVo [lider=" + lider + ", valor=" + valor + "]";
	}

}
