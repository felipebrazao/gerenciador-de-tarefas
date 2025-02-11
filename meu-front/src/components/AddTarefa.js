// src/components/AddTarefa.js
import React, { useState } from "react";
import axios from "axios";

const AddTarefa = ({ onTarefaAdded }) => {
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [dataVencimento, setDataVencimento] = useState("");
  const [status, setStatus] = useState("Pendente");

  const handleSubmit = (e) => {
    e.preventDefault();
    const newTarefa = {
      titulo,
      descricao,
      dataVencimento,
      status,
    };

    console.log("Enviando tarefa:", newTarefa); // Log para verificar o objeto que está sendo enviado

    axios
      .post("http://localhost:8080/tarefas", newTarefa)
      .then((response) => {
        console.log("Resposta da requisição:", response.data); // Verifica a resposta da API
        onTarefaAdded(response.data); // Atualiza a lista de tarefas no componente pai
        setTitulo("");
        setDescricao("");
        setDataVencimento("");
        setStatus("Pendente");
      })
      .catch((error) => {
        console.error("Erro ao adicionar tarefa:", error);
      });
  };

  return (
    <div>
      <h2>Adicionar Tarefa</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Título:</label>
          <input
            type="text"
            value={titulo}
            onChange={(e) => setTitulo(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Descrição:</label>
          <input
            type="text"
            value={descricao}
            onChange={(e) => setDescricao(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Data de Vencimento:</label>
          <input
            type="date"
            value={dataVencimento}
            onChange={(e) => setDataVencimento(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Status:</label>
          <select
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            required
          >
            <option value="Pendente">Pendente</option>
            <option value="Concluída">Concluída</option>
          </select>
        </div>
        <button type="submit">Adicionar</button>
      </form>
    </div>
  );
};

export default AddTarefa;