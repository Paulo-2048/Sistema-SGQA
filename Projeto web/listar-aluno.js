const baseUrl = "https://api-sgqa.onrender.com/estudante"

async function getUserList() {
  try {
    const response = await axios.get(baseUrl);
    return response["data"];
  } catch (error) {
    alert("Erro ao carregar lista de alunos!");
  }
}


const estudantesDiv  = document.querySelector(".listaEstudantes");

async function renderEstudantes() {
  const estudantes = await getUserList();

  console.log(estudantes);
  
  estudantes["data"].forEach(estudante => {
    const estudanteInfo = document.createElement("div");
    estudanteInfo.classList.add("estudanteInfo");
    
    // const estudanteId = document.createElement("p");
    // estudanteId.classList.add("estudanteId");
    // estudanteId.innerText = estudante.id;
    // estudanteId.style.display = "none";

    const matricula = document.createElement("h3");
    matricula.classList.add("matricula");
    matricula.innerText = estudante.matricula;

    const nome = document.createElement("h4");
    nome.classList.add("nome");
    nome.innerText = estudante.nomeCompleto;

    const quadroButton = document.createElement("button");
    quadroButton.innerText = "Acessar Horários";
    quadroButton.id = "quadro-button";

    quadroButton.addEventListener("click", () => {
      window.location.href = `quadro-aluno.html?id=${estudante.id}`;
    });

    const apagarButton = document.createElement("button");
    apagarButton.innerText = " X ";
    apagarButton.id = "apagar-button";

    apagarButton.addEventListener("click", () => {
      deleteEstudante(estudante.id);
    });

    const quadroDiv = document.createElement("div");
    quadroDiv.classList.add("quadro");
    


    estudanteInfo.appendChild(matricula);
    estudanteInfo.appendChild(nome);
    estudanteInfo.appendChild(quadroDiv);
    quadroDiv.appendChild(quadroButton);
    quadroDiv.appendChild(apagarButton);
    estudantesDiv.appendChild(estudanteInfo);
  });
}

renderEstudantes();

async function deleteEstudante(id) {
  try {
    await axios.delete(`${baseUrl}/${id}`);
    alert("Estudante apagado com sucesso!");
    window.location.reload();
  } catch (error) {
    alert("Erro ao apagar estudante!");
  }
}