package com.pluralsight.bookstore.rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.repository.BookRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Created by m05b372 on 7-7-2017.
 */
@Path("/books") // make this class a JAX-RS endpoint aka REST endpoint
public class BookEndpoint {
    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{id : \\d+}")
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book book = bookRepository.find(id);
        if (book == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(book).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    @Path("/{id  : \\d+}")
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();
        if(books.size() == 0)
            return Response.noContent().build();

        return Response.ok(books).build();
    }

    @GET
    @Path("/count")
    @Produces(TEXT_PLAIN)
    public Response countBooks() {
        Long nbOfBooks = bookRepository.countAll();
        if (nbOfBooks == 0)
            return Response.noContent().build();
        return Response.ok(nbOfBooks).build();
    }

    @Inject
    private BookRepository bookRepository;


}
