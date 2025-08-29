package eu.itcrafters.linkvault.infrastructure.persistence.link;

import eu.itcrafters.linkvault.infrastructure.persistence.category.Category;
import eu.itcrafters.linkvault.infrastructure.persistence.linktag.LinkTag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "LINK")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Size(max = 200)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    private String description;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @Size(max = 20)
    @ColumnDefault("'waiting'")
    @Column(name = "WORKFLOW_STATUS", length = 20)
    private String workflowStatus;

    @Size(max = 20)
    @ColumnDefault("'private'")
    @Column(name = "VISIBILITY", length = 20)
    private String visibility;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "link", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LinkTag> linkTags;

}