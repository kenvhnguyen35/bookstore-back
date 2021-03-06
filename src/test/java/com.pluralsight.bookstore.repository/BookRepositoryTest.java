package com.pluralsight.bookstore.repository;
/**
 * Created by M05B372 on 2-7-2017.
 */

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.util.IsbnGenerator;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class BookRepositoryTest {
    @Inject
    private BookRepository bookRepository;

    @Test(expected = Exception.class)
    public void findWithNullId() {
        bookRepository.find(null);
    }

    @Test(expected = Exception.class)
    public void createInvalidBook() {
        Book newBook = new Book(null,
                "Good Java book",
                45F,
                "someISBN",
                new Date(),
                445,
                "https://www.pearsonhighered.com/assets/bigcovers/0/2/0/1/0201310058.jpg",
                Language.ENGLISH
        );

        Book b = bookRepository.create(newBook);
    }

    @Test
    public void create() throws Exception {

        assertEquals(Long.valueOf(0), bookRepository.countAll());
        assertEquals(0, bookRepository.findAll().size());

        Book newBook = new Book("Effective  Java",
                "Good Java book",
                45F,
                "someISBN",
                new Date(),
                445,
                "https://www.pearsonhighered.com/assets/bigcovers/0/2/0/1/0201310058.jpg",
                Language.ENGLISH
                );

        Book b = bookRepository.create(newBook);
        assertNotNull(b.getId());
        assertEquals("Effective Java", b.getTitle());
        assertTrue(b.getIsbn().startsWith("13"));
    }

    @Test
    public void countAll() throws Exception {
/*        assertEquals(Long.valueOf(1), bookRepository.countAll());*/
    }

    @Test
    public void findAll() throws Exception {
/*        assertEquals(1, bookRepository.findAll().size());*/
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(TextUtil.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                ;
    }
}
