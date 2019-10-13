package ru.malifaux.programming.FTBWL.event.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventFormatDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.EventStatusDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.FactionDto;
import ru.malifaux.programming.FTBWL.dictionary.dto.RulepackDto;
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
        compositeDictionaryEntity.eventStatuses = mapListByDefault(eventStatusService.findAll(), EventStatusDto.class);
        compositeDictionaryEntity.eventFormats = mapListByDefault(eventFormatService.findByGameSystem(gameID), EventFormatDto.class);
        compositeDictionaryEntity.rulePacks = mapListByDefault(rulepackService.findByGameSystem(gameID), RulepackDto.class);
        compositeDictionaryEntity.factionEntities = mapListByDefault(factionService.findAllByGameSystem(gameID), FactionDto.class);
        return compositeDictionaryEntity;
    }

    @Getter
    @Setter
    @ToString
    private class CompositeDictionaryEntity {
        List<EventStatusDto> eventStatuses;
        List<EventFormatDto> eventFormats;
        List<RulepackDto> rulePacks;
        List<FactionDto> factionEntities;
    }
}
