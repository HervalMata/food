package com.herval.food.domain.service;

import com.herval.food.domain.filter.VendaDiariaFilter;
import com.herval.food.domain.model.dto.VendaDiaria;

import java.util.List;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
public interface VendaQueryService {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
