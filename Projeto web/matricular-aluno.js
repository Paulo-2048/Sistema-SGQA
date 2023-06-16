const baseUrl = "https://api-sgqa.onrender.com"

const form = document.getElementById("form");

form.addEventListener("submit", (event) => {
  event.preventDefault();

  let matricula = document.getElementById("estudanteList").value;

  let disciplina = document.getElementById("disciplinaList").value;

  let data = {
    estudanteId: matricula,
    disciplinaId: disciplina,
  };

  enviarDados(data);

})

async function enviarDados(dados) {
  try {
    const response = await axios.post(`${baseUrl}/matricula`, dados);
    alert("Matrícula realizada com sucesso!");
  } catch (error) {
    console.log(error);
    alert("Erro ao realizar matrícula!");
  }
}



const disciplinaList = document.getElementById("disciplinaList");

const estudanteList = document.getElementById("estudanteList");

async function getEstudanteList() {
  try {
    const response = await axios.get(`${baseUrl}/estudante`);
    return response["data"]["data"];
  } catch (error) {
    alert("Erro ao carregar lista de estudantes!");
  }
}

async function getDisciplinaList() {
  try {
    const response = await axios.get(`${baseUrl}/disciplina`);
    return response["data"]["data"];
  } catch (error) {
    alert("Erro ao carregar lista de disciplinas!");
  }
}

async function fillDDL(){
  
  const estudantes = await getEstudanteList();

  estudantes.forEach(estudante => {
    const option = document.createElement("option");
    option.value = estudante.id;
    option.innerText = estudante.nomeCompleto;
    estudanteList.appendChild(option);
  });

  const disciplinas = await getDisciplinaList();

  disciplinas.forEach(disciplina => {
    const option = document.createElement("option");
    option.value = disciplina.id;
    option.innerText = disciplina.nome;
    disciplinaList.appendChild(option);
  });

}

await fillDDL();