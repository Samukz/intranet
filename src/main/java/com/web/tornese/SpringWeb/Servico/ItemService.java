package com.web.tornese.SpringWeb.Servico;

import org.springframework.stereotype.Service;

import com.web.tornese.SpringWeb.models.Item;
import com.web.tornese.SpringWeb.repositorio.ItemsRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ItemService {
    @Autowired
    private ItemsRepo itemRepository;

    public Double getTotalValor() {
        return itemRepository.sumValor();
    }

    public Double getMaiorValor() {
        return itemRepository.sumMaiorValor();
    }

    public Long getTotalItems() {
        return itemRepository.countItems();
    }

    public Long totalItemsObsoletos() {
        return itemRepository.countItemsNaoObsoletos();
    }

    public List<Object[]> getSumValorByData() {
        return itemRepository.sumValorByData();
    }

    public List<Object[]> getsumValorPorLocal() {
        return itemRepository.sumValorPorLocal();
    }
    
    public List<Item> buscarItensFiltrados(String nome, String local, String categoria, String estado) {
        nome = (nome != null && !nome.trim().isEmpty()) ? nome : null;
        local = (local != null && !local.trim().isEmpty()) ? local : null;
        categoria = (categoria != null && !categoria.trim().isEmpty()) ? categoria : null;
        // O estado é tratado como uma correspondência exata; verifique se precisa de
        // lógica semelhante.

        return itemRepository.findItemsByFilters(nome, local, categoria, estado);
    }
    
}
