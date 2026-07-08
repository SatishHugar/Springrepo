package com.satpallcrochet.repository;

import com.satpallcrochet.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findBySessionId(String sessionId);

    Optional<Wishlist> findByProductIdAndSessionId(Long productId, String sessionId);

}
