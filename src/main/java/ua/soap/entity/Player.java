package ua.soap.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "level", nullable = false)
    private Integer level;

    public Player() {
    }

    public Player(Long id, Integer level) {
        this.id = id;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(level, player.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", level=" + level +
                '}';
    }
}
