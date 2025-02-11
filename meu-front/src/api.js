const API_URL = "/tarefas"; // Como usamos o proxy, não precisa do localhost:8080

export const fetchTarefas = async () => {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error("Erro ao buscar tarefas");
        }
        return await response.json();
    } catch (error) {
        console.error(error);
        return [];
    }
};