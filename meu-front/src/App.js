// src/App.js
import React, { useState } from "react";
import AddTarefa from "./components/AddTarefa";
import TarefaList from "./components/TarefaList";

const App = () => {
  const [tarefas, setTarefas] = useState([]);

  const handleTarefaAdded = (newTarefa) => {
    setTarefas((prevTarefas) => [...prevTarefas, newTarefa]);
  };

  const handleTarefaRemoved = (id) => {
    setTarefas((prevTarefas) => prevTarefas.filter((tarefa) => tarefa.id !== id));
  };

  return (
    <div>
      <h1>Gerenciador de Tarefas</h1>
      <AddTarefa onTarefaAdded={handleTarefaAdded} />
      <TarefaList tarefas={tarefas} onTarefaRemoved={handleTarefaRemoved} />
    </div>
  );
};

export default App;
