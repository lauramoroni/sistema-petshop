const botaoAlterarTema = document.getElementById("darkModeToggle");
const body = document.querySelector("body");
const trocarImagem = document.querySelector(".imagem-botao");

// Função para aplicar o tema salvo
function aplicarTemaSalvo() {
  const temaSalvo = localStorage.getItem("theme");
  if (temaSalvo === "dark") {
    body.classList.add("dark-mode");
    trocarImagem.setAttribute("src", "/images/sun.png"); // Define a imagem do sol
  } else {
    body.classList.remove("dark-mode");
    trocarImagem.setAttribute("src", "/images/moon.png"); // Define a imagem da lua
  }
}

// Chama a função para aplicar o tema salvo ao carregar a página
aplicarTemaSalvo();

botaoAlterarTema.addEventListener("click", () => {
  // Alterna a classe "dark-mode" no body
  body.classList.toggle("dark-mode");

  // Salva a que já estava no localStorage
  if (body.classList.contains("dark-mode")) {
    localStorage.setItem("theme", "dark");
    // Trocar o sol pela lua
    trocarImagem.setAttribute("src", "/images/sun.png");
  } else {
    localStorage.setItem("theme", "light");
    // Trocar a lua pelo sol
    trocarImagem.setAttribute("src", "/images/moon.png");
  }
});