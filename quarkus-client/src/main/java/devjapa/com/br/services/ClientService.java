package devjapa.com.br.services;


import devjapa.com.br.dto.client.AddClientDTO;
import devjapa.com.br.dto.client.ClientDTO;
import devjapa.com.br.dto.client.UpdateClientDTO;

import java.util.List;

public interface ClientService {

    String health();
    List<ClientDTO> findAll();
    ClientDTO findById(Long id);
    ClientDTO insert(AddClientDTO dto);
    void update(Long id, UpdateClientDTO dto);
    void delete(Long id);

}
