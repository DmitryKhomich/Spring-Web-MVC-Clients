package demo.service;

import demo.entity.Client;
import demo.repo.BaseRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    BaseRepositoryImpl repository;


    @Transactional
    public void save(Client client) {
        repository.save(client);
    }


    @Transactional
    public List<Client> fetchAll() {
        return repository.fetchAll();
    }


    @Transactional
    public Client fetchById(Long id) {
        return repository.fetchById(id);
    }


    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }
}
