/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme.travels;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import org.jbpm.util.JsonSchemaUtil;
import org.kie.kogito.Application;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessInstanceExecutionException;
import org.kie.kogito.process.ProcessInstanceNotFoundException;
import org.kie.kogito.process.ProcessInstanceReadMode;
import org.kie.kogito.process.WorkItem;
import org.kie.kogito.process.workitem.Attachment;
import org.kie.kogito.process.workitem.AttachmentInfo;
import org.kie.kogito.process.workitem.Comment;
import org.kie.kogito.process.workitem.Policies;
import org.kie.kogito.process.impl.Sig;
import org.kie.kogito.services.uow.UnitOfWorkExecutor;
import org.kie.kogito.auth.IdentityProvider;
import org.jbpm.process.instance.impl.humantask.HumanTaskHelper;
import org.jbpm.process.instance.impl.humantask.HumanTaskTransition;
import org.acme.travels.TravelsModelOutput;

@Path("/travels")
@javax.enterprise.context.ApplicationScoped()
public class TravelsResource {

    @javax.inject.Inject()
    @javax.inject.Named("travels")
    Process<TravelsModel> process;

    @javax.inject.Inject()
    Application application;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createResource_travels(@Context HttpHeaders httpHeaders, @Context UriInfo uriInfo, @QueryParam("businessKey") String businessKey, @javax.validation.Valid() @javax.validation.constraints.NotNull() TravelsModelInput resource) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> {
            TravelsModelInput inputModel = resource != null ? resource : new TravelsModelInput();
            ProcessInstance<TravelsModel> pi = process.createInstance(businessKey, inputModel.toModel());
            String startFromNode = httpHeaders.getHeaderString("X-KOGITO-StartFromNode");
            if (startFromNode != null) {
                pi.startFrom(startFromNode);
            } else {
                pi.start();
            }
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(pi.id());
            return Response.created(uriBuilder.build()).entity(pi.checkError().variables().toOutput()).build();
        });
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelsModelOutput> getResources_travels() {
        return process.instances().values().stream().map(pi -> pi.variables().toOutput()).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput getResource_travels(@PathParam("id") String id) {
        return process.instances().findById(id, ProcessInstanceReadMode.READ_ONLY).map(pi -> pi.variables().toOutput()).orElseThrow(() -> new NotFoundException());
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput deleteResource_travels(@PathParam("id") final String id) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.abort();
            return pi.checkError().variables().toOutput();
        })).orElseThrow(() -> new NotFoundException());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput updateModel_travels(@PathParam("id") String id, TravelsModel resource) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> pi.updateVariables(resource).toOutput())).orElseThrow(() -> new NotFoundException());
    }

    @GET
    @Path("/{id}/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WorkItem> getTasks_travels(@PathParam("id") String id, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return process.instances().findById(id, ProcessInstanceReadMode.READ_ONLY).map(pi -> pi.workItems(Policies.of(user, groups))).orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Path("/{id}/VisaApplication/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput completeTask_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("phase") @DefaultValue("complete") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, final Travels_4_TaskOutput model) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.transitionWorkItem(taskId, HumanTaskTransition.withModel(phase, model, Policies.of(user, groups)));
            return pi.variables().toOutput();
        })).orElseThrow(() -> new NotFoundException());
    }

    @PUT
    @Path("/{id}/VisaApplication/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Travels_4_TaskOutput saveTask_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, final Travels_4_TaskOutput model) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> Travels_4_TaskOutput.fromMap(pi.updateWorkItem(taskId, wi -> HumanTaskHelper.updateContent(wi, model), Policies.of(user, groups))))).orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Path("/{id}/VisaApplication/{taskId}/phases/{phase}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput taskTransition_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("phase") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, final Travels_4_TaskOutput model) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.transitionWorkItem(taskId, HumanTaskTransition.withModel(phase, model, Policies.of(user, groups)));
            return pi.variables().toOutput();
        })).orElseThrow(() -> new NotFoundException());
    }

    @GET
    @Path("/{id}/VisaApplication/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Travels_4_TaskInput getTask_VisaApplication_0(@PathParam("id") String id, @PathParam("taskId") String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return process.instances().findById(id, ProcessInstanceReadMode.READ_ONLY).map(pi -> Travels_4_TaskInput.from(pi.workItem(taskId, Policies.of(user, groups)))).orElseThrow(() -> new NotFoundException());
    }

    @GET
    @Path("VisaApplication/schema")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getSchema_VisaApplication_0() {
        return JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id(), "VisaApplication");
    }

    @GET
    @Path("/{id}/VisaApplication/{taskId}/schema")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getSchemaAndPhases_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return JsonSchemaUtil.addPhases(process, application, id, taskId, Policies.of(user, groups), JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id(), "VisaApplication"));
    }

    @DELETE
    @Path("/{id}/VisaApplication/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput abortTask_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("phase") @DefaultValue("abort") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.transitionWorkItem(taskId, HumanTaskTransition.withoutModel(phase, Policies.of(user, groups)));
            return pi.variables().toOutput();
        })).orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Path("/{id}/VisaApplication/{taskId}/comments")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, String commentInfo, @Context UriInfo uriInfo) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            Comment comment = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.addComment(wi, commentInfo, user), Policies.of(user, groups));
            return Response.created(uriInfo.getAbsolutePathBuilder().path(comment.getId().toString()).build()).entity(comment).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @PUT
    @Path("/{id}/VisaApplication/{taskId}/comments/{commentId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment updateComment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("commentId") final String commentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, String comment) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> pi.updateWorkItem(taskId, wi -> HumanTaskHelper.updateComment(wi, commentId, comment, user), Policies.of(user, groups))).orElseThrow(() -> new NotFoundException()));
    }

    @DELETE
    @Path("/{id}/VisaApplication/{taskId}/comments/{commentId}")
    public Response deleteComment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("commentId") final String commentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            boolean removed = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.deleteComment(wi, commentId, user), Policies.of(user, groups));
            return (removed ? Response.ok() : Response.status(Status.NOT_FOUND)).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @POST
    @Path("/{id}/VisaApplication/{taskId}/attachments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAttachment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, AttachmentInfo attachmentInfo, @Context UriInfo uriInfo) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            Attachment attachment = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.addAttachment(wi, attachmentInfo, user), Policies.of(user, groups));
            return Response.created(uriInfo.getAbsolutePathBuilder().path(attachment.getId().toString()).build()).entity(attachment).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @PUT
    @Path("/{id}/VisaApplication/{taskId}/attachments/{attachmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Attachment updateAttachment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("attachmentId") final String attachmentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, AttachmentInfo attachment) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> pi.updateWorkItem(taskId, wi -> HumanTaskHelper.updateAttachment(wi, attachmentId, attachment, user), Policies.of(user, groups))).orElseThrow(() -> new NotFoundException()));
    }

    @DELETE
    @Path("/{id}/VisaApplication/{taskId}/attachments/{attachmentId}")
    public Response deleteAttachment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("attachmentId") final String attachmentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            boolean removed = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.deleteAttachment(wi, attachmentId, user), Policies.of(user, groups));
            return (removed ? Response.ok() : Response.status(Status.NOT_FOUND)).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @GET
    @Path("/{id}/VisaApplication/{taskId}/attachments/{attachmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Attachment getAttachment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("attachmentId") final String attachmentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        Attachment attachment = HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getAttachments().get(attachmentId);
        if (attachment == null) {
            throw new NotFoundException("Attachment " + attachmentId + " not found");
        }
        return attachment;
    }

    @GET
    @Path("/{id}/VisaApplication/{taskId}/attachments")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Attachment> getAttachments_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getAttachments().values();
    }

    @GET
    @Path("/{id}/VisaApplication/{taskId}/comments/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment getComment_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("commentId") final String commentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        Comment comment = HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getComments().get(commentId);
        if (comment == null) {
            throw new NotFoundException("Comment " + commentId + " not found");
        }
        return comment;
    }

    @GET
    @Path("/{id}/VisaApplication/{taskId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comment> getComments_VisaApplication_0(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getComments().values();
    }

    @POST
    @Path("/{id}/ConfirmTravel/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput completeTask_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("phase") @DefaultValue("complete") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, final Travels_10_TaskOutput model) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.transitionWorkItem(taskId, HumanTaskTransition.withModel(phase, model, Policies.of(user, groups)));
            return pi.variables().toOutput();
        })).orElseThrow(() -> new NotFoundException());
    }

    @PUT
    @Path("/{id}/ConfirmTravel/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Travels_10_TaskOutput saveTask_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, final Travels_10_TaskOutput model) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> Travels_10_TaskOutput.fromMap(pi.updateWorkItem(taskId, wi -> HumanTaskHelper.updateContent(wi, model), Policies.of(user, groups))))).orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Path("/{id}/ConfirmTravel/{taskId}/phases/{phase}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput taskTransition_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("phase") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, final Travels_10_TaskOutput model) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.transitionWorkItem(taskId, HumanTaskTransition.withModel(phase, model, Policies.of(user, groups)));
            return pi.variables().toOutput();
        })).orElseThrow(() -> new NotFoundException());
    }

    @GET
    @Path("/{id}/ConfirmTravel/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Travels_10_TaskInput getTask_ConfirmTravel_1(@PathParam("id") String id, @PathParam("taskId") String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return process.instances().findById(id, ProcessInstanceReadMode.READ_ONLY).map(pi -> Travels_10_TaskInput.from(pi.workItem(taskId, Policies.of(user, groups)))).orElseThrow(() -> new NotFoundException());
    }

    @GET
    @Path("ConfirmTravel/schema")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getSchema_ConfirmTravel_1() {
        return JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id(), "ConfirmTravel");
    }

    @GET
    @Path("/{id}/ConfirmTravel/{taskId}/schema")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getSchemaAndPhases_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return JsonSchemaUtil.addPhases(process, application, id, taskId, Policies.of(user, groups), JsonSchemaUtil.load(this.getClass().getClassLoader(), process.id(), "ConfirmTravel"));
    }

    @DELETE
    @Path("/{id}/ConfirmTravel/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelsModelOutput abortTask_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("phase") @DefaultValue("abort") final String phase, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            pi.transitionWorkItem(taskId, HumanTaskTransition.withoutModel(phase, Policies.of(user, groups)));
            return pi.variables().toOutput();
        })).orElseThrow(() -> new NotFoundException());
    }

    @POST
    @Path("/{id}/ConfirmTravel/{taskId}/comments")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, String commentInfo, @Context UriInfo uriInfo) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            Comment comment = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.addComment(wi, commentInfo, user), Policies.of(user, groups));
            return Response.created(uriInfo.getAbsolutePathBuilder().path(comment.getId().toString()).build()).entity(comment).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @PUT
    @Path("/{id}/ConfirmTravel/{taskId}/comments/{commentId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment updateComment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("commentId") final String commentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, String comment) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> pi.updateWorkItem(taskId, wi -> HumanTaskHelper.updateComment(wi, commentId, comment, user), Policies.of(user, groups))).orElseThrow(() -> new NotFoundException()));
    }

    @DELETE
    @Path("/{id}/ConfirmTravel/{taskId}/comments/{commentId}")
    public Response deleteComment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("commentId") final String commentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            boolean removed = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.deleteComment(wi, commentId, user), Policies.of(user, groups));
            return (removed ? Response.ok() : Response.status(Status.NOT_FOUND)).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @POST
    @Path("/{id}/ConfirmTravel/{taskId}/attachments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAttachment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, AttachmentInfo attachmentInfo, @Context UriInfo uriInfo) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            Attachment attachment = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.addAttachment(wi, attachmentInfo, user), Policies.of(user, groups));
            return Response.created(uriInfo.getAbsolutePathBuilder().path(attachment.getId().toString()).build()).entity(attachment).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @PUT
    @Path("/{id}/ConfirmTravel/{taskId}/attachments/{attachmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Attachment updateAttachment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("attachmentId") final String attachmentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups, AttachmentInfo attachment) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> pi.updateWorkItem(taskId, wi -> HumanTaskHelper.updateAttachment(wi, attachmentId, attachment, user), Policies.of(user, groups))).orElseThrow(() -> new NotFoundException()));
    }

    @DELETE
    @Path("/{id}/ConfirmTravel/{taskId}/attachments/{attachmentId}")
    public Response deleteAttachment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("attachmentId") final String attachmentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return UnitOfWorkExecutor.executeInUnitOfWork(application.unitOfWorkManager(), () -> process.instances().findById(id).map(pi -> {
            boolean removed = pi.updateWorkItem(taskId, wi -> HumanTaskHelper.deleteAttachment(wi, attachmentId, user), Policies.of(user, groups));
            return (removed ? Response.ok() : Response.status(Status.NOT_FOUND)).build();
        }).orElseThrow(() -> new NotFoundException()));
    }

    @GET
    @Path("/{id}/ConfirmTravel/{taskId}/attachments/{attachmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Attachment getAttachment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("attachmentId") final String attachmentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        Attachment attachment = HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getAttachments().get(attachmentId);
        if (attachment == null) {
            throw new NotFoundException("Attachment " + attachmentId + " not found");
        }
        return attachment;
    }

    @GET
    @Path("/{id}/ConfirmTravel/{taskId}/attachments")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Attachment> getAttachments_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getAttachments().values();
    }

    @GET
    @Path("/{id}/ConfirmTravel/{taskId}/comments/{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment getComment_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @PathParam("commentId") final String commentId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        Comment comment = HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getComments().get(commentId);
        if (comment == null) {
            throw new NotFoundException("Comment " + commentId + " not found");
        }
        return comment;
    }

    @GET
    @Path("/{id}/ConfirmTravel/{taskId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comment> getComments_ConfirmTravel_1(@PathParam("id") final String id, @PathParam("taskId") final String taskId, @QueryParam("user") final String user, @QueryParam("group") final List<String> groups) {
        return HumanTaskHelper.findTask(process.instances().findById(id).orElseThrow(() -> new NotFoundException()), taskId, Policies.of(user, groups)).getComments().values();
    }
}
