package com.herval.food.domain.service;

import com.herval.food.domain.exception.EntidadeEmUsoException;
import com.herval.food.domain.exception.FormaPagamentoNaoEncontradaException;
import com.herval.food.domain.model.FormaPagamento;
import com.herval.food.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Criado Por Herval Mata em 17/12/2019
 */
@Service
public class FormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de Pagamento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long formaPagamentoId) {
        try {
            formaPagamentoRepository.deleteById(formaPagamentoId);
            formaPagamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaPagamentoId));
        }
    }

    public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
        return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }
}
