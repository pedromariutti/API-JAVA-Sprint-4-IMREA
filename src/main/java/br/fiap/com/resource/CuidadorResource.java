package br.fiap.com.resource;

import br.fiap.com.bo.CuidadorBO;
import br.fiap.com.to.CuidadorTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/imrea-api/cuidadores")
public class CuidadorResource {

    private CuidadorBO cuidadorBO = new CuidadorBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<CuidadorTO> resultado = cuidadorBO.findAll();
        Response.ResponseBuilder response = Response.status(Response.Status.OK);

        if (resultado == null || resultado.isEmpty()) {
            response = Response.status(Response.Status.NOT_FOUND);
        }

        return response.entity(resultado).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long codigo) {
        CuidadorTO resultado = cuidadorBO.findByCodigo(codigo);

        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid CuidadorTO cuidador) {
        CuidadorTO resultado = cuidadorBO.save(cuidador);

        if (resultado != null) {
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } else {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid CuidadorTO cuidador, @PathParam("codigo") Long codigo) {
        cuidador.setIdCuidador(codigo);
        CuidadorTO resultado = cuidadorBO.update(cuidador);

        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        if (cuidadorBO.delete(codigo)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
