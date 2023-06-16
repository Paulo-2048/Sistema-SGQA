const baseUrl = "https://api-sgqa.onrender.com/professor"

async function getProfessorList() {
  try {
    const response = await axios.get(baseUrl);
    return response["data"];
  } catch (error) {
    alert("Erro ao carregar lista de professores!");
  }
}


const professoresDiv  = document.querySelector(".listaProfessores");

async function renderProfessores() {
  const professores = await getProfessorList();

  console.log(professores);
  
  professores["data"].forEach(professor => {
    const professorInfo = document.createElement("div");
    professorInfo.classList.add("profInfo");
    
    // const professorId = document.createElement("p");
    // professorId.classList.add("professorId");
    // professorId.innerText = professor.id;
    // professorId.style.display = "none";

    const matricula = document.createElement("h3");
    matricula.classList.add("matricula");
    matricula.innerText = professor.matricula;

    const nome = document.createElement("h4");
    nome.classList.add("nome");
    nome.innerText = professor.nomeCompleto;

    const quadroButton = document.createElement("button");
    quadroButton.innerText = "Acessar Horários";
    quadroButton.id = "quadro-button";

    quadroButton.addEventListener("click", () => {
      window.location.href = `quadro-professor.html?id=${professor.id}`;
    });

    const apagarButton = document.createElement("button");
    apagarButton.innerText = " X ";
    apagarButton.id = "apagar-button";

    apagarButton.addEventListener("click", () => {
      deleteProfessor(professor.id);
    });

    const quadroDiv = document.createElement("div");
    quadroDiv.classList.add("quadro");


    professorInfo.appendChild(matricula);
    professorInfo.appendChild(nome);
    professorInfo.appendChild(quadroDiv);
    quadroDiv.appendChild(quadroButton);
    quadroDiv.appendChild(apagarButton);
    professoresDiv.appendChild(professorInfo);
  });
}

renderProfessores();

async function deleteProfessor(id) {
  try {
    await axios.delete(`${baseUrl}/${id}`);
    alert("Professor apagado com sucesso!");
    window.location.reload();
  } catch (error) {
    alert("Erro ao deletar professor!");
  }
}