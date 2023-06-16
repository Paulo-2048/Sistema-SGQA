const baseUrl = "https://api-sgqa.onrender.com/horario"

async function getHorarioByProfessorId(id) {
  try {
    const response = await axios.get(baseUrl + `/professor/${id}`);
    return response["data"];
  } catch (error) {
    console.log(error);
    alert("Erro ao carregar horário!\nVerifique se o professor possui disciplinas.");
    window.location.href = "listar-professor.html";
  }
}

const estudantId = new URLSearchParams(window.location.search).get("id");
let horarios = await getHorarioByProfessorId(estudantId);
horarios = horarios["data"]

console.log(horarios)

// [
//   [
//       "DIS01",
//       "Matemática",
//       "SEGUNDA",
//       "08:00:00",
//       "10:00:00"
//   ],
//   [
//       "DIS01",
//       "Matemática",
//       "SEGUNDA",
//       "08:00:00",
//       "10:00:00"
//   ]
// ]

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

