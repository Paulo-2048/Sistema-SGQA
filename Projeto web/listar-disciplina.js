const baseUrl = "https://api-sgqa.onrender.com/disciplina"

async function getDisciplinaList() {
  try {
    const response = await axios.get(baseUrl);
    return response["data"];
  } catch (error) {
    alert("Erro ao carregar lista de disciplinas!");
  }
}


const disciplinasDiv  = document.querySelector(".listaDisciplina");

async function renderDisciplinas() {
  const disciplinas = await getDisciplinaList();
  
  disciplinas["data"].forEach(disciplina => {
    const disciplinaInfo = document.createElement("div");
    disciplinaInfo.classList.add("disciplinaInfo");

    const cod = document.createElement("h3");
    cod.classList.add("cod");
    cod.innerText = disciplina.codigoDisciplina;

    const nome = document.createElement("h4");
    nome.classList.add("nome");
    nome.innerText = disciplina.nome;

    const quadroButton = document.createElement("button");
    quadroButton.innerText = "Acessar Diário";
    quadroButton.id = "quadro-button";

    quadroButton.addEventListener("click", () => {
      window.location.href = `diario.html?id=${disciplina.id}`;
    });

    const apagarButton = document.createElement("button");
    apagarButton.innerText = " X ";
    apagarButton.id = "apagar-button";

    apagarButton.addEventListener("click", () => {
      deleteDisciplina(disciplina.id);
    });

    const quadroDiv = document.createElement("div");
    quadroDiv.classList.add("quadro");
    
    disciplinaInfo.appendChild(cod);
    disciplinaInfo.appendChild(nome);
    disciplinaInfo.appendChild(quadroDiv);
    quadroDiv.appendChild(quadroButton);
    quadroDiv.appendChild(apagarButton);
    disciplinasDiv.appendChild(disciplinaInfo);
  });
}

renderDisciplinas();

async function deleteDisciplina(id) {
  try {
    await axios.delete(`${baseUrl}/${id}`);
    alert("Disciplina apagado com sucesso!");
    window.location.reload();
  } catch (error) {
    alert("Erro ao apagar dsciplina!");
  }
}