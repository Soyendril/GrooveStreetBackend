package com.GrooveSpring.Musicien;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicienRepository extends JpaRepository<Musicien, Long> {

}
