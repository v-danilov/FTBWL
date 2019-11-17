package ru.malifaux.programming.FTBWL.event.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventFormatDTO;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventStatusDTO;
import ru.malifaux.programming.FTBWL.dictionary.dto.FactionDTO;
import ru.malifaux.programming.FTBWL.dictionary.dto.RulepackDTO;
import ru.malifaux.programming.FTBWL.dictionary.services.EventFormatService;
import ru.malifaux.programming.FTBWL.dictionary.services.EventStatusService;
import ru.malifaux.programming.FTBWL.dictionary.services.FactionService;
import ru.malifaux.programming.FTBWL.dictionary.services.RulepackService;

import java.util.List;
import java.util.UUID;

import static ru.malifaux.programming.FTBWL.event.converters.OricaMapper.mapListByDefault;

@RestController
@RequestMapping("${api.version}/dict")
@RequiredArgsConstructor
public class DictionaryController {

    private final EventStatusService eventStatusService;
    private final EventFormatService eventFormatService;
    private final RulepackService rulepackService;
    private final FactionService factionService;

    @GetMapping("/{gameID}")
    public CompositeDictionaryEntity getAllDicts(@PathVariable UUID gameID) {
        CompositeDictionaryEntity compositeDictionaryEntity = new CompositeDictionaryEntity();
        compositeDictionaryEntity.eventStatuses = mapListByDefault(eventStatusService.findAll(), EventStatusDTO.class);
        compositeDictionaryEntity.eventFormats = mapListByDefault(eventFormatService.findByGameSystem(gameID), EventFormatDTO.class);
        compositeDictionaryEntity.rulePacks = mapListByDefault(rulepackService.findByGameSystem(gameID), RulepackDTO.class);
        compositeDictionaryEntity.factionEntities = mapListByDefault(factionService.findAllByGameSystem(gameID), FactionDTO.class);
        return compositeDictionaryEntity;
    }

    @Getter
    @Setter
    @ToString
    private class CompositeDictionaryEntity {
        List<EventStatusDTO> eventStatuses;
        List<EventFormatDTO> eventFormats;
        List<RulepackDTO> rulePacks;
        List<FactionDTO> factionEntities;
    }
}
