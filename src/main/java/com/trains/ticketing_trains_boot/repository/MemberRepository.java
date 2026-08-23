package com.trains.ticketing_trains_boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trains.ticketing_trains_boot.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
    long countByRole(com.trains.ticketing_trains_boot.entity.Role role);

}
