const baseUrl = "https://api-sgqa.onrender.com"




// DOM Elements
const submitButton = document.getElementById("botao");
const form = document.getElementById("form");

// Get Student Data

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  let codigo = document.getElementById("codigo").value;
  let nome = document.getElementById("nome").value;
  let prof = document.getElementById("profList").value;

  let dataDisc = {
    codigoDisciplina: codigo,
    nome: nome,
    professorId: prof,
  };

  let newDisciplina = null;
  if (validarDados(dataDisc)) {
     newDisciplina = await enviarDadosDisciplina(dataDisc);
  }

  let disciplinaId = newDisciplina.id;
  let diaDaSemana = document.getElementById("diaDaSemana").value;

  let horarios = document.getElementById("horarioList").value;

  let horarioInicio
  let horarioFinal

  if (horarios == 1) {
     horarioInicio = "07:00";
     horarioFinal = "09:40";
  }
  else if (horarios == 2) {
     horarioInicio = "09:50";
     horarioFinal = "12:30";
  }

  let dataInicio = "2020-01-01";
  let dataFinal = "2030-12-31";

  let dataHorario = {
    disciplinaId: disciplinaId,
    diaDaSemana: diaDaSemana,
    horarioInicio: horarioInicio,
    horarioFinal: horarioFinal,
    dataInicio: dataInicio,
    dataFinal: dataFinal,
  }

  console.log(dataHorario);

  await enviarDadosHorario(dataHorario);
});




// Validar dados do aluno
function validarDados(dados) {
  let codigo = dados.codigoDisciplina;
  let nome = dados.nome;
  let prof = dados.prof;

  if (codigo == "" || nome == "" || prof == "") {
    alert("Preencha todos os campos!");
    return false;
  }
  else if (codigo.length > 8) {
    alert("Insira um código menor que 8 dígitos!");
    return false;
  }

  return true;
}


// Enviar dados da discipliba
async function enviarDadosDisciplina(dados) {
  try {
    const response = await axios.post(baseUrl + "/disciplina", dados);
    alert(response["data"]["message"]);
    return response["data"]["data"];
  } catch (error) {
    console.log(error);
    alert("Erro ao cadastrar disciplina!");
  }
}

async function enviarDadosHorario(dados){
  try {
    const response = await axios.post(baseUrl + "/horario", dados);
    console.log(response["data"]);
    alert(response["data"]["message"]);
  } catch (error) {
    console.log(error);
    alert("Erro ao cadastrar horário!");
  }
}


const profList = document.getElementById("profList");

async function fillProfDdl(){
  try {
    const response = await axios.get(baseUrl + "/professor");
    
    response["data"]["data"].forEach(prof => {
      let option = document.createElement("option");
      option.value = prof.id;
      option.text = prof.nomeCompleto;
      profList.appendChild(option);
    });
  } catch (error) {
    console.log(error);
    alert("Erro ao carregar lista de professores!");
  }
}

await fillProfDdl();
