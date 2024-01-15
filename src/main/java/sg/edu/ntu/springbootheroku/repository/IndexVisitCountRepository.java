package sg.edu.ntu.springbootheroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.ntu.springbootheroku.entity.IndexVisitCount;

@Repository
public interface IndexVisitCountRepository extends JpaRepository<IndexVisitCount, Long> {
    
}
