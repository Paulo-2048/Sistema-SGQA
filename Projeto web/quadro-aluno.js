const baseUrl = "https://api-sgqa.onrender.com/horario"

async function getHorarioByProfessorId(id) {
  try {
    const response = await axios.get(baseUrl + `/estudante/${id}`);
    return response["data"];
  } catch (error) {
    console.log(error);
    alert("Erro ao carregar horário!\nVerifique se o aluno possui matrícula em disciplinas.");
    window.location.href = "listar-aluno.html";
  }
}

const estudantId = new URLSearchParams(window.location.search).get("id");
let horarios = await getHorarioByProfessorId(estudantId);
horarios = horarios["data"]

console.log(horarios)

const table = document.getElementById("quadro-horarios");
async function renderGradeHorarios(data){
  for (let i = 0; i < data.length; i++) {
    const row = table.insertRow(i+1);
    for (let j = 0; j < data[i].length; j++) {
      const cell = row.insertCell(j);
      cell.innerHTML = data[i][j];
    }
  }
}

renderGradeHorarios(horarios);

