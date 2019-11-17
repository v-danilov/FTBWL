package ru.malifaux.programming.FTBWL.common.gateway.services;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malifaux.programming.FTBWL.common.gateway.entity.RoleEntity;
import ru.malifaux.programming.FTBWL.common.gateway.filters.RoleFilter;
import ru.malifaux.programming.FTBWL.common.gateway.repository.RoleRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public Page<RoleEntity> findAll(RoleFilter filter, Pageable pageable) {
        EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("functions");
        return roleRepository.findAll(filter.toSpecification(), pageable, entityGraph);
    }

    public List<RoleEntity> findAllList(RoleFilter filter) {
        EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("functions");
        return roleRepository.findAll(filter.toSpecification(), entityGraph);
    }

    @Transactional
    public RoleEntity save(RoleEntity roleEntity){
        return roleRepository.save(roleEntity);
    }

    public RoleEntity findOne(String id){
        EntityGraph entityGraph = EntityGraphUtils.fromAttributePaths("functions");

        return roleRepository.findById(UUID.fromString(id), entityGraph).orElse(null);
    }
}
