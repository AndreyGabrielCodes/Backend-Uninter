package com.uninter.backendrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uninter.backendrest.model.Tarefa;

@Repository
public interface TarefaRepository  extends JpaRepository <Tarefa, Long>{

}
