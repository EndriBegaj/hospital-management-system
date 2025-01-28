package dev.endribegaj.hospitalmanagementsystem.infrastructure.mapping;

public interface SimpleMapper<TEntity, TDto> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);
}
