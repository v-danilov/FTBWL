package ru.malifaux.programming.FTBWL.event.controllers;

import lombok.*;
import org.springframework.web.bind.annotation.*;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventFormatEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.EventStatusEntity;
import ru.malifaux.programming.FTBWL.dictionary.entity.RulepackEntity;
import ru.malifaux.programming.FTBWL.dictionary.services.EventFormatService;
import ru.malifaux.programming.FTBWL.dictionary.services.EventStatusService;
import ru.malifaux.programming.FTBWL.dictionary.services.RulepackService;

import javax.persistence.Entity;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.version}/dict")
@RequiredArgsConstructor
public class DictionaryController {

    private final EventStatusService eventStatusService;
    private final EventFormatService eventFormatService;
    private final RulepackService rulepackService;

    @GetMapping("/{gameID}")
    public CompositeDictionaryEntity getAllDicts(@PathVariable UUID gameID) {
        CompositeDictionaryEntity compositeDictionaryEntity = new CompositeDictionaryEntity();
        compositeDictionaryEntity.eventStatuses = eventStatusService.findAll();
        compositeDictionaryEntity.eventFormats = eventFormatService.findByGameSystem(gameID);
        compositeDictionaryEntity.rulePacks = rulepackService.findByGameSystem(gameID);
        return compositeDictionaryEntity;
    }

    @Getter
    @Setter
    @ToString
    private class CompositeDictionaryEntity {
        List<EventStatusEntity> eventStatuses;
        List<EventFormatEntity> eventFormats;
        List<RulepackEntity> rulePacks;
    }
}
