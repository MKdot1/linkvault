package eu.itcrafters.linkvault.infrastructure.persistence.linktag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkTagRepository extends JpaRepository<LinkTag, Integer> {
    void deleteByLink_Id(Integer linkId);
}