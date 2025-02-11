// src/components/TarefaList.js
import React, { useEffect, useState } from "react";
import axios from "axios";

const TarefaList = ({ onTarefaRemoved }) => {
  const [tarefas, setTarefas] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/tarefas")
      .then((response) => {
        setTarefas(response.data);
      })
      .catch((error) => {
        console.error("Erro ao buscar tarefas:", error);
      });
  }, []);

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:8080/tarefas/${id}`)
      .then(() => {
        setTarefas(tarefas.filter((tarefa) => tarefa.id !== id));
        onTarefaRemoved(id);
      })
      .catch((error) => {
        console.error("Erro ao remover tarefa:", error);
      });
  };

  return (
    <div>
      <h2>Lista de Tarefas</h2>
      <ul>
        {tarefas.map((tarefa) => (
          <li key={tarefa.id}>
            <strong>{tarefa.titulo}</strong> - {tarefa.status}
            <br />
            {tarefa.descricao}
            <br />
            Vencimento: {tarefa.dataVencimento}
            <button onClick={() => handleDelete(tarefa.id)}>Remover</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TarefaList;