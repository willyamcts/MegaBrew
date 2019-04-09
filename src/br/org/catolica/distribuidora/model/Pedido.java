package br.org.catolica.distribuidora.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Pedido {
		private int id;
		//@XmlElementWrapper(name = "itens")
		//@XmlElement(name = "item")
		private List<Item> itens;
		private double valorTotal;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public List<Item> getItens() {
			return itens;
		}
		public void setItens(List<Item> itens) {
			this.itens = itens;
		}
		
		public double getValorTotal() {
			return valorTotal;
		}
		public void setValorTotal(double valorTotal) {
			this.valorTotal = valorTotal;
		}	

}