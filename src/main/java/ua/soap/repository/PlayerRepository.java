package ua.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.soap.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
