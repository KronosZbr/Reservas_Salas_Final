package com.usuarioservice.demo.application.service;

import com.usuarioservice.demo.domain.model.User;
import com.usuarioservice.demo.infrastructure.messaging.UserEventPublisher;
import com.usuarioservice.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserEventPublisher eventPublisher;

    public List<User> listar() {
        return repository.findAll();
    }

    public User salvar(User user) {
        User savedUser = repository.save(user);
        eventPublisher.publishUserCreated(savedUser);
        return savedUser;
    }

    public Optional<User> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }
    
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }

    // MÉTODO NOVO PARA ATUALIZAR ADICIONADO AQUI
    public User atualizar(Long id, User userDetails) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado."));

        // Atualiza os campos do usuário existente com os novos detalhes
        user.setNome(userDetails.getNome());
        user.setEmail(userDetails.getEmail());
        user.setSenha(userDetails.getSenha());
        user.setTelefone(userDetails.getTelefone());
        user.setEndereco(userDetails.getEndereco());
        // Adicione outros campos que desejar atualizar

        // Salva o usuário atualizado
        User updatedUser = repository.save(user);

        // Publica o evento de que o usuário foi atualizado
        eventPublisher.publishUserCreated(updatedUser);

        return updatedUser;
    }
}