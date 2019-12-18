package com.herval.food.domain.service;

import com.herval.food.domain.filter.VendaDiariaFilter;

/*
 * Criado Por Herval Mata em 18/12/2019
 */
public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
