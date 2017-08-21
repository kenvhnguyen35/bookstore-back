package com.pluralsight.bookstore.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by m05b372 on 7-7-2017.
 */
@ApplicationPath("api") //everything under the text api is an JAX-RS endpoint
public class JAXRSConfiguration extends Application {
}
