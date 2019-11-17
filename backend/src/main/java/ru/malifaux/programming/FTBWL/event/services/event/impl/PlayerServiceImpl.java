package ru.malifaux.programming.FTBWL.event.services.event.impl;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malifaux.programming.FTBWL.common.gateway.filters.PlayerFilter;
import ru.malifaux.programming.FTBWL.event.entity.PlayerEntity;
import ru.malifaux.programming.FTBWL.event.repository.PlayerRepository;
import ru.malifaux.programming.FTBWL.event.services.event.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<PlayerEntity> getAll(EntityGraph entityGraph) {
        List<PlayerEntity> playerEntities = new ArrayList<>();
        playerRepository.findAll(entityGraph).forEach(playerEntities::add);
        return playerEntities;
    }

    @Override
    public PlayerEntity getByID(String id, EntityGraph entityGraph) {
        return playerRepository.findById(UUID.fromString(id), entityGraph).orElseThrow(NullPointerException::new);
    }

    @Override
    public void save(PlayerEntity entity) {
        playerRepository.save(entity);
    }

    @Override
    public List<PlayerEntity> getPlayersByEventID(String eventID) {
        PlayerFilter playerFilter = new PlayerFilter();
        playerFilter.setEventID(eventID);
        return playerRepository.findAll(playerFilter);
    }
}
