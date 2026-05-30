package br.gov.mt.seplag.api.service;

import br.gov.mt.seplag.api.dto.RegionalExternaDTO;
import br.gov.mt.seplag.api.model.Regional;
import br.gov.mt.seplag.api.repository.RegionalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegionalSyncService {

    private final RegionalRepository regionalRepository;
    private final RestTemplate restTemplate;

    public RegionalSyncService(RegionalRepository regionalRepository, RestTemplate restTemplate) {
        this.regionalRepository = regionalRepository;
        this.restTemplate = restTemplate;
    }

    public void sincronizar() {
        RegionalExternaDTO[] externas = restTemplate.getForObject(
                "https://integrador-argus-api.geia.vip/v1/regionais",
                RegionalExternaDTO[].class
        );

        if (externas == null) return;

        Map<Integer, String> mapaExterno = Arrays.stream(externas)
                .collect(Collectors.toMap(RegionalExternaDTO::id, RegionalExternaDTO::nome));

        Map<Integer, Regional> mapaBanco = regionalRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Regional::getId, r -> r));

        // Regra 1 — ID novo na API → Inserir
        for (Map.Entry<Integer, String> entry : mapaExterno.entrySet()) {
            if (!mapaBanco.containsKey(entry.getKey())) {
                Regional nova = new Regional();
                nova.setId(entry.getKey());
                nova.setNome(entry.getValue());
                nova.setAtivo(true);
                regionalRepository.save(nova);
            }
        }

        // Regras 2 e 3 — percorre o banco
        for (Map.Entry<Integer, Regional> entry : mapaBanco.entrySet()) {
            Regional regional = entry.getValue();

            // Regra 2 — ID sumiu da API → Inativar
            if (!mapaExterno.containsKey(entry.getKey())) {
                regional.setAtivo(false);
                regionalRepository.save(regional);

            // Regra 3 — Nome mudou → Atualizar nome
            } else if (!regional.getNome().equals(mapaExterno.get(entry.getKey()))) {
                regional.setNome(mapaExterno.get(entry.getKey()));
                regional.setAtivo(true);
                regionalRepository.save(regional);
            }
        }


    }


}
