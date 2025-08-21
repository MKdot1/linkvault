package eu.itcrafters.linkvault.infrastructure.persistence.link;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {
}