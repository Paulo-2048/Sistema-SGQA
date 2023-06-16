const baseUrl = "https://api-sgqa.onrender.com/professor"

// DOM Elements
const submitButton = document.getElementById("botao");
const form = document.getElementById("form");

// Get Student Data

form.addEventListener("submit", (event) => {
  event.preventDefault();

  let matricula = document.getElementById("matricula").value;
  let nome = document.getElementById("nome").value;
  let email = document.getElementById("email").value;
  let anoEgresso = document.getElementById("egresso").value;

  let data = {
    matricula: matricula,
    nomeCompleto: nome,
    email: email,
    anoEgresso: anoEgresso,
  };

  if (validarDados(data)) {
    enviarDados(data);
  }
});




// Validar dados do aluno
function validarDados(dados) {
  let matricula = dados.matricula;
  let nome = dados.nome;
  let email = dados.email;
  let anoEgresso = dados.anoEgresso;

  if (matricula == "" || nome == "" || email == "" || anoEgresso == "") {
    alert("Preencha todos os campos!");
    return false;
  }
  else if (matricula.length > 8) {
    alert("Insira uma matrícula menor que 8 dígitos!");
    return false;
  }
  else if (anoEgresso.length != 4 || anoEgresso < 1900 || anoEgresso > 2022) {
    alert("Insira um ano de egresso válido!");
    return false;
  }

  return true;
}


// Enviar dados do aluno
async function enviarDados(dados) {
  try {
    const response = await axios.post(baseUrl, dados);
    alert(response["data"]["message"]);
  } catch (error) {
    alert("Erro ao cadastrar professor!");
  }
}
