package com.onehoeyman.battle.Repository;

import com.onehoeyman.battle.Entity.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository  extends CrudRepository<Character, Long> {
    List<Character> findByCreatorId(Long creator_id);
}
