package org.ta.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ta.database.dto.Author;
import org.ta.database.dto.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DatabaseServiceTest {

	Logger log = LogManager.getLogger(this.getClass().getName());
	
	private EntityManagerFactory emf;
	
	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory("persistence-unit");
	}

	@After
	public void close() {
		emf.close();
	}

	
	@Test
	public void bidirectionalManyToMany() {
		log.info("... bidirectionalManyToMany ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		
		Author a = new Author();
		a.setFirstname("Thorben");
		a.setLastname("Janssen");
		
		a.getBooks().add(b);
		b.getAuthors().add(a);
		
		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
		
		// Get Book entity with Authors
		em = emf.createEntityManager();
		em.getTransaction().begin();

		b = em.find(Book.class, 1L);
		
		List<Author> authors = b.getAuthors();
		Assert.assertTrue(authors.get(0).getBooks().contains(b));
		Assert.assertTrue(authors.contains(a));
		
		em.getTransaction().commit();
		em.close();
	}
	
}
