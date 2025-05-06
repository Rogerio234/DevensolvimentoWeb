package aplicativo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.Cliente;
import dominio.Produto;
import dominio.Venda;

public class Principal {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aula-jpa");
		EntityManager em = emf.createEntityManager();
		
		try {
			//Instancia o EntityManagerFactory com as configurações de persistencia
			emf = Persistence.createEntityManagerFactory("aula-jpa"); 
			//Intancia o EntityManager
			em = emf.createEntityManager(); 
			
			em.getTransaction().begin();

			Produto produto1 = new Produto("Monitor", 1000);
			Produto produto2 = new Produto("Cadeira", 3000);
			Produto produto3 = new Produto("Placa de Video", 4000);
			Produto produto4 = new Produto("Processador", 5000);
			
			em.persist(produto1);
			em.persist(produto2);
			em.persist(produto3);
			em.persist(produto4);


			Cliente cliente1 =  new Cliente("Rogério");
			Cliente cliente2 =  new Cliente("Carlos");
			Cliente cliente3 =  new Cliente("Rodrigo");
			Cliente cliente4 =  new Cliente("Vitor");

			
			Venda venda1 = new Venda(4000);
			Venda venda2 = new Venda(9000);
			Venda venda3 = new Venda(8000);
			Venda venda4 = new Venda(5000);

			venda1.setProdutos(Arrays.asList(produto1,produto2));
			venda2.setProdutos(Arrays.asList(produto3,produto4));
			venda3.setProdutos(Arrays.asList(produto2,produto4));
			venda4.setProdutos(Arrays.asList(produto4));

			cliente1.setVendas(Arrays.asList(venda1));
			cliente2.setVendas(Arrays.asList(venda2));
			cliente3.setVendas(Arrays.asList(venda3));
			cliente4.setVendas(Arrays.asList(venda4));

			venda1.setCliente(cliente1);
			venda2.setCliente(cliente2);
			venda3.setCliente(cliente3);
			venda4.setCliente(cliente4);

			em.persist(cliente1);
			em.persist(cliente2);
			em.persist(cliente3);
			em.persist(cliente4);
			em.persist(venda1);
			em.persist(venda2);
			em.persist(venda3);
			em.persist(venda4);

			// Inserir os objetos aqui!
			
			em.getTransaction().commit();//confirmar as alterações realizadas
			
			emf.close();
			em.close();
			
			// Inserir os laços for aqui!
			
		}catch (Exception e){
			if(em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}finally {
			if(em != null) {
				em.close();
			}
			if(emf != null) {
				emf.close();
			}
		}
	}			
}
