package system.gagaduck.systemservice.repository;

import system.gagaduck.systemservice.model.DictItem;

public interface DictRepository extends BaseRepository<DictItem, Integer> {
    DictItem findOneById(Integer id);
}
