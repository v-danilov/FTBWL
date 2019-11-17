package ru.malifaux.programming.FTBWL.event.services.event;

import ru.malifaux.programming.FTBWL.event.entity.PlayerEntity;
import ru.malifaux.programming.FTBWL.event.services.BasicCRUDService;

import java.util.List;

public interface PlayerService extends BasicCRUDService<PlayerEntity> {
    List<PlayerEntity> getPlayersByEventID(String eventID);
}
