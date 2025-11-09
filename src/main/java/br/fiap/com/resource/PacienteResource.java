package br.fiap.com.resource;

import br.fiap.com.bo.PacienteBO;
import br.fiap.com.to.PacienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/imrea-api/pacientes")
public class PacienteResource {

    private PacienteBO pacienteBO = new PacienteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<PacienteTO> resultado = pacienteBO.findAll();
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
        PacienteTO resultado = pacienteBO.findByCodigo(codigo);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid PacienteTO paciente) {
        PacienteTO resultado = pacienteBO.save(paciente);
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
    public Response update(@Valid PacienteTO paciente, @PathParam("codigo") Long codigo) {
        paciente.setIdPaciente(codigo);
        PacienteTO resultado = pacienteBO.update(paciente);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        if (pacienteBO.delete(codigo)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
