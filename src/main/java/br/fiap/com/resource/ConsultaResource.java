package br.fiap.com.resource;

import br.fiap.com.bo.ConsultaBO;
import br.fiap.com.to.ConsultaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/imrea-api/consultas")
public class ConsultaResource {

    private ConsultaBO consultaBO = new ConsultaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ConsultaTO> resultado = consultaBO.findAll();
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
        ConsultaTO resultado = consultaBO.findByCodigo(codigo);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid ConsultaTO consulta) {
        ConsultaTO resultado = consultaBO.save(consulta);
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
    public Response update(@Valid ConsultaTO consulta, @PathParam("codigo") Long codigo) {
        consulta.setIdConsulta(codigo);
        ConsultaTO resultado = consultaBO.update(consulta);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        if (consultaBO.delete(codigo)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
