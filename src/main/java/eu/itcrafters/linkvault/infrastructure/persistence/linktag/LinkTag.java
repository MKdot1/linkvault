package eu.itcrafters.linkvault.infrastructure.persistence.linktag;

import eu.itcrafters.linkvault.infrastructure.persistence.tag.Tag;
import eu.itcrafters.linkvault.infrastructure.persistence.link.Link;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LINK_TAG")
public class LinkTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TAG_ID", nullable = false)
    private Tag tag;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LINK_ID", nullable = false)
    private Link link;

}