package org.protege.editor.owl.client.api;

import org.protege.owl.server.api.CommitBundle;
import org.protege.owl.server.api.exception.ServerRequestException;
import org.protege.owl.server.changes.ServerDocument;

import java.util.List;
import java.util.Map;

import edu.stanford.protege.metaproject.api.Operation;
import edu.stanford.protege.metaproject.api.OperationId;
import edu.stanford.protege.metaproject.api.Project;
import edu.stanford.protege.metaproject.api.ProjectId;
import edu.stanford.protege.metaproject.api.Role;
import edu.stanford.protege.metaproject.api.RoleId;
import edu.stanford.protege.metaproject.api.User;
import edu.stanford.protege.metaproject.api.UserId;

/**
 * Represents all operations that the client could request to the server.
 *
 * @author Josef Hardi <johardi@stanford.edu> <br>
 *         Stanford Center for Biomedical Informatics Research
 */
public interface ClientRequests {

    /**
     * Getting all users known by the server.
     *
     * @return A list of {@code User}.
     * @throws ServerRequestException
     */
    List<User> getAllUsers() throws ServerRequestException;

    /**
     * Creating a new user to the server.
     *
     * @param newUser
     *            The new user to add.
     * @throws ServerRequestException
     */
    void createUser(User newUser) throws ServerRequestException;

    /**
     * Deleting an existing user from the server.
     *
     * @param userId
     *            The user to remove identified by the ID
     * @throws ServerRequestException
     */
    void deleteUser(UserId userId) throws ServerRequestException;

    /**
     * Updating information of an exiting user in the server.
     * 
     * @param userId
     *          The target user to modify identified by the ID
     * @param updatedUser
     *          The new updated user to replace with
     * @throws ServerRequestException
     */
    void updateUser(UserId userId, User updatedUser) throws ServerRequestException;

    /**
     * Getting all projects the given the user id.
     *
     * @param userId
     *          The target user identified by the ID
     * @return A list of {@code Project}
     * @throws ServerRequestException
     */
    List<Project> getProjects(UserId userId) throws ServerRequestException;

    /**
     * Getting all project known by the server.
     *
     * @return A list of {@code Project}.
     * @throws ServerRequestException
     */
    List<Project> getAllProjects() throws ServerRequestException;

    /**
     * Creating a new project to the server.
     *
     * @param newProject
     *            The new project to add.
     * @throws ServerRequestException
     */
    void createProject(Project newProject) throws ServerRequestException;

    /**
     * Deleting an existing project from the server.
     *
     * @param projectId
     *            The project to remove identified by its ID.
     * @throws ServerRequestException
     */
    void deleteProject(ProjectId projectId) throws ServerRequestException;

    /**
     * Updating information of an existing project in the server.
     *
     * @param projectId
     *            The target project to modify identified by its ID.
     * @param updatedProject
     *            The new updated project to replace with.
     * @throws ServerRequestException
     */
    void updateProject(ProjectId projectId, Project updatedProject) throws ServerRequestException;

    /**
     * Opening a project from the server. The server will return the {@code ProjectResource} that
     * can be used to construct the project ontology.
     *
     * @param projectId
     *            The project to open identified by its ID
     * @throws ServerRequestException
     */
    ServerDocument openProject(ProjectId projectId) throws ServerRequestException;

    /**
     * Getting all roles given the user id, categorized for each owned project.
     *
     * @param userId
     *          The target user identified by the ID
     * @return A map of {@code ProjectId} with a list of corresponding {@code Role}
     * @throws ServerRequestException
     */
    Map<ProjectId, List<Role>> getRoles(UserId userId) throws ServerRequestException;

    /**
     * Getting all roles given the user id and the project id.
     *
     * @param userId
     *          The target user identified by the ID
     * @param projectId
     *          The target project identified by the ID
     * @return A list of {@code Role}
     * @throws ServerRequestException
     */
    List<Role> getRoles(UserId userId, ProjectId projectId) throws ServerRequestException;

    /**
     * Getting all roles known by the server.
     *
     * @return A list of {@code Role}
     * @throws ServerRequestException
     */
    List<Role> getAllRoles() throws ServerRequestException;

    /**
     * Creating a new role to the server.
     *
     * @param newRole
     *          The new role to add.
     * @throws ServerRequestException
     */
    void createRole(Role newRole) throws ServerRequestException;

    /**
     * Deleting an existing role from the server.
     *
     * @param roleId
     *          The role to remove identified by its ID.
     * @throws ServerRequestException
     */
    void deleteRole(RoleId roleId) throws ServerRequestException;

    /**
     * Updating information of an existing role at the server.
     *
     * @param roleId
     *          The target role to modify identified by its ID.
     * @param updatedRole
     *          The new updated role to replace with.
     * @throws ServerRequestException
     */
    void updateRole(RoleId roleId, Role updatedRole) throws ServerRequestException;

    /**
     * Getting all operations given the user id, categorized for each owned project.
     *
     * @param userId
     *          The target user identified by the ID
     * @return A map of {@code ProjectId} with a list of corresponding {@code Operation}
     * @throws ServerRequestException
     */
    Map<ProjectId, List<Operation>> getOperations(UserId userId) throws ServerRequestException;

    /**
     * Getting all operations given the user id and the project id.
     *
     * @param userId
     *          The target user identified by the ID
     * @param projectId
     *          The target project identified by the ID
     * @return A list of {code Operation}
     * @throws ServerRequestException
     */
    List<Operation> getOperations(UserId userId, ProjectId projectId) throws ServerRequestException;

    /**
     * Getting all operations known by the server.
     *
     * @return A list of {@code Operation}
     * @throws ServerRequestException
     */
    List<Operation> getAllOperations() throws ServerRequestException;

    /**
     * Creating a new operation to the server.
     *
     * @param operation
     *          The new operation to add.
     * @throws ServerRequestException
     */
    void createOperation(Operation operation) throws ServerRequestException;

    /**
     * Deleting an existing operation from the server.
     *
     * @param operationId
     *          The operation to remove identified by its ID.
     * @throws ServerRequestException
     */
    void deleteOperation(OperationId operationId) throws ServerRequestException;

    /**
     * Updating an existing operation at the server.
     *
     * @param operationId
     *          The target operation to modify identified by its ID.
     * @param updatedOperation
     *          The new updated operation to replace with.
     * @throws ServerRequestException
     */
    void updateOperation(OperationId operationId, Operation updatedOperation) throws ServerRequestException;

    /**
     * Assigning a role to a user for a particular project.
     *
     * @param userId
     *          The target user
     * @param projectId
     *          The target project
     * @param roleId
     *          The role to assign
     * @throws ServerRequestException
     */
    void assignRole(UserId userId, ProjectId projectId, RoleId roleId) throws ServerRequestException;

    /**
     * Retracting a role from a user for a particular project.
     *
     * @param userId
     *          The target user
     * @param projectId 
     *          The target project
     * @param roleId
     *          The role to retract
     * @throws ServerRequestException
     */
    void retractRole(UserId userId, ProjectId projectId, RoleId roleId) throws ServerRequestException;

    /**
     * Setting a server property by specifying the property name and the value.
     *
     * @param property
     *          The target property name
     * @param value
     *          The property value
     * @throws ServerRequestException
     */
    void setServerConfiguration(String property, String value) throws ServerRequestException;

    /**
     * Committing the given ontology changes to be applied in the server.
     *
     * @param project
     *            The target project for such changes
     * @param changes
     *            A list of changes coming from the client
     * @throws ServerRequestException
     */
    void commit(Project project, CommitBundle changes) throws ServerRequestException;
}
