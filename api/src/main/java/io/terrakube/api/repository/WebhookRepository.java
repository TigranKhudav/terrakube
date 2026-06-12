package io.terrakube.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.terrakube.api.rs.vcs.VcsType;
import io.terrakube.api.rs.webhook.Webhook;

public interface WebhookRepository extends JpaRepository<Webhook, UUID> {

    // Webhooks whose workspace is backed by one of the given VCS types. The Workspace
    // entity is annotated with @SQLRestriction("deleted = false"), so deleted
    // workspaces are excluded automatically.
    @Query("SELECT wh FROM webhook wh WHERE wh.workspace.vcs.vcsType IN :types")
    List<Webhook> findByWorkspaceVcsTypeIn(@Param("types") List<VcsType> types);
}
